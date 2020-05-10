package uk.gov.digital.justice.hmpps.parking.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import uk.gov.digital.justice.hmpps.parking.model.Result
import java.util.*

@Repository
interface ResultRepository : JpaRepository<Result, UUID> {

    fun getAllByDayOfYear(dayOfYear: Int): Collection<Result>
}