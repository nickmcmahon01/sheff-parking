package uk.gov.digital.justice.hmpps.parking.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import uk.gov.digital.justice.hmpps.parking.model.Request
import uk.gov.digital.justice.hmpps.parking.repository.RequestRepository

@Service
class RequestService(val repository: RequestRepository) {

    fun getRequestsForDay(dayOfYear: Int): Collection<Request> {
        log.debug("Retrieving requests for day $dayOfYear")
        val requests = repository.getAllByDayOfYear(dayOfYear)
        log.info("Retrieved requests for day $dayOfYear, found ${requests.size}")
        log.debug(requests.toString())
        return requests
    }

    companion object {
        private val log = LoggerFactory.getLogger(RequestService::class.java)
    }
}