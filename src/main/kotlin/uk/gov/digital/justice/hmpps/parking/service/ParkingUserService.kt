package uk.gov.digital.justice.hmpps.parking.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import uk.gov.digital.justice.hmpps.parking.model.ParkingUser
import uk.gov.digital.justice.hmpps.parking.repository.ParkingUserRepository

@Service
class ParkingUserService(val repository: ParkingUserRepository) {

    fun getAllPriorityUsers(): Collection<ParkingUser> {
        log.debug("Retrieving all priority users")
        val reserveListItems = repository.findAllByPriorityIsTrue()
        log.info("Retrieved all priority users, found ${reserveListItems.size}")
        log.debug(reserveListItems.toString())
        return reserveListItems
    }

    fun addUsersToReserveList(users: Collection<ParkingUser>) {
        log.debug("Adding users to reserve list")
        users.forEach { user -> user.priority = true }
        repository.saveAll(users)
        log.info("Added ${users.size} users to reserve list")
        log.debug(users.toString())
    }

    fun removeUsersFromReserveList(users: Collection<ParkingUser>) {
        log.debug("Removing users from reserve list")
        users.forEach { user -> user.priority = false }
        repository.saveAll(users)
        log.info("Removed ${users.size} users from reserve list")
        log.debug(users.toString())
    }


    companion object {
        private val log = LoggerFactory.getLogger(ParkingUserService::class.java)
    }
}