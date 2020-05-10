package uk.gov.digital.justice.hmpps.parking.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import uk.gov.digital.justice.hmpps.parking.model.Request
import java.util.*

@Repository
interface RequestRepository : JpaRepository<Request, UUID> {

    fun getAllByDayOfYear(dayofYear: Int): Collection<Request>
}