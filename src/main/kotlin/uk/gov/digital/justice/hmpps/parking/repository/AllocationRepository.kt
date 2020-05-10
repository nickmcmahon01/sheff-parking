package uk.gov.digital.justice.hmpps.parking.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import uk.gov.digital.justice.hmpps.parking.model.Allocation
import java.util.*

@Repository
interface AllocationRepository : JpaRepository<Allocation, UUID> {

    fun getAllByDayOfYear(dayofYear: Int): Collection<Allocation>

}