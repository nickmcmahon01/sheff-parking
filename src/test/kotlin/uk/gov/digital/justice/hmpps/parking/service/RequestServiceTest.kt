package uk.gov.digital.justice.hmpps.parking.service

import io.mockk.clearMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verifyAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import uk.gov.digital.justice.hmpps.parking.ParkingUserType
import uk.gov.digital.justice.hmpps.parking.model.Bay
import uk.gov.digital.justice.hmpps.parking.model.ParkingUser
import uk.gov.digital.justice.hmpps.parking.model.Request
import uk.gov.digital.justice.hmpps.parking.repository.BayRepository
import uk.gov.digital.justice.hmpps.parking.repository.RequestRepository

@ExtendWith(MockKExtension::class)
@DisplayName("Request Service Tests")
internal class RequestServiceTest {

    val repository: RequestRepository = mockk()

    val service = RequestService(repository)

    @BeforeEach
    fun resetAllMocks() {
        clearMocks(repository)
    }

    @Nested
    @DisplayName("getRequestsForDay()")
    inner class GetRequestsForDay {

        @Test
        fun `No Requests`() {
            every { repository.getAllByDayOfYear(1) } returns listOf()
            assertThat(service.getRequestsForDay(1)).isEmpty()
            verifyAll { repository.getAllByDayOfYear(1) }
        }

        @Test
        fun `Some Requests`() {
            every { repository.getAllByDayOfYear(1) } returns listOf(Request(ParkingUser("Any","Email", ParkingUserType.EMPLOYEE, "1234", false, true), 1, 1, true))
            assertThat(service.getRequestsForDay(1)).hasSize(1)
            verifyAll { repository.getAllByDayOfYear(1) }
        }
    }

}