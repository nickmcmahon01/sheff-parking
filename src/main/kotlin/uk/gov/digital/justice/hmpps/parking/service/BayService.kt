package uk.gov.digital.justice.hmpps.parking.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import uk.gov.digital.justice.hmpps.parking.model.Bay
import uk.gov.digital.justice.hmpps.parking.repository.BayRepository

@Service
class BayService(val repository: BayRepository) {

    fun getAllBays(): Collection<Bay> {
        log.debug("Retrieving all bays")
        val bays = repository.findAll()
        log.info("Retrieved all bays, found ${bays.size}")
        log.debug(bays.toString())
        return bays
    }

    companion object {
        private val log = LoggerFactory.getLogger(BayService::class.java)
    }
}