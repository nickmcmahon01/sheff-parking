package uk.gov.digital.justice.hmpps.parking.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uk.gov.digital.justice.hmpps.parking.model.Result
import uk.gov.digital.justice.hmpps.parking.repository.ResultRepository

@Service
class ResultService(val repository: ResultRepository, val requestService: RequestService, val parkingUserService: ParkingUserService) {

    @Transactional
    fun generateResultsForDay(dayOfYear: Int): Collection<Result> {
        log.debug("Generating results for day $dayOfYear")

        // We don't want to recalculate results - return existing ones if present.
        val existingResults = getResultsForDay(dayOfYear)
        if (existingResults.isNotEmpty()) {
            log.warn("Results already generated for day $dayOfYear, returning existing")
            return existingResults
        }

        // Get the requests and priority list.
        val requests = requestService.getRequestsForDay(dayOfYear)
        val reserveList = parkingUserService.getAllPriorityUsers().map { r -> r.uuid }

        // Order the requests by business rules.
        requests.sortedWith(compareBy(
                { r -> reserveList.contains(r.user.uuid) },
                { r -> r.user.userType.value },
                { r -> r.user.accessNeeds },
                { r -> r.priority },
                { r -> r.createDateTime }))

        // Map the ordered requests into Results. The Index is the preference order for being allocated a space.
        val results = requests.mapIndexed { index, request -> Result(request.user, request.dayOfYear, index) }
        repository.saveAll(results)

        log.info("Generated results for day $dayOfYear")
        log.debug(results.toString())
        return results

    }

    fun getResultsForDay(dayOfYear: Int): Collection<Result> {
        log.debug("Retrieving results for day $dayOfYear")
        val results = repository.getAllByDayOfYear(dayOfYear)
        log.info("Retrieved results for day $dayOfYear, found ${results.size}")
        log.debug(results.toString())
        return results
    }

    companion object {
        private val log = LoggerFactory.getLogger(ResultService::class.java)
    }
}