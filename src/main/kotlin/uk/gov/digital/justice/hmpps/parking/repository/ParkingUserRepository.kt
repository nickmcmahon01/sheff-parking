package uk.gov.digital.justice.hmpps.parking.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import uk.gov.digital.justice.hmpps.parking.model.ParkingUser
import java.util.*

@Repository
interface ParkingUserRepository : JpaRepository<ParkingUser, UUID> {

    fun findAllByPriorityIsTrue() : Collection<ParkingUser>
}