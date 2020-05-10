package uk.gov.digital.justice.hmpps.parking.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uk.gov.digital.justice.hmpps.parking.model.Allocation
import uk.gov.digital.justice.hmpps.parking.repository.AllocationRepository

@Service
class AllocationService(val repository: AllocationRepository, val bayService: BayService, val resultService: ResultService, val parkingUserService: ParkingUserService) {

    @Transactional
    fun generateAllocationsForDay(dayOfYear: Int): Collection<Allocation> {
        log.debug("Generating allocations for day $dayOfYear")

        // We don't want to recalculate allocations - return existing ones if present.
        val existingAllocations = getAllocationsForDay(dayOfYear)
        if (existingAllocations.isNotEmpty()) {
            log.warn("Allocations already generated for day $dayOfYear, returning existing")
            return existingAllocations
        }

        // Get the results
        val results = resultService.getResultsForDay(dayOfYear).sortedBy { result -> result.index }

        // Allocate bays to the results in order
        val allocations = results.zip(bayService.getAllBays()) { result, bay -> Allocation(bay, result, dayOfYear) }
        parkingUserService.removeUsersFromReserveList(allocations.map { a -> a.result.user })

        // Add unsuccessful requests to the priority list
        if (results.size > allocations.size) {
            parkingUserService.addUsersToReserveList(results.drop(allocations.size -1).map { candidate -> candidate.user })
        }

        repository.saveAll(allocations)

        log.info("Generated allocations for day $dayOfYear")
        log.debug(allocations.toString())
        return allocations

    }

    private fun getAllocationsForDay(dayOfYear: Int): Collection<Allocation> {
        log.debug("Retrieving results for day $dayOfYear")
        val results = repository.getAllByDayOfYear(dayOfYear)
        log.info("Retrieved results for day $dayOfYear, found ${results.size}")
        log.debug(results.toString())
        return results
    }

    companion object {
        private val log = LoggerFactory.getLogger(AllocationService::class.java)
    }
}