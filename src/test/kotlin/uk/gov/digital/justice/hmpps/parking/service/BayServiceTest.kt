package uk.gov.digital.justice.hmpps.parking.service

import io.mockk.*
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import uk.gov.digital.justice.hmpps.parking.model.Bay
import uk.gov.digital.justice.hmpps.parking.repository.BayRepository

@ExtendWith(MockKExtension::class)
@DisplayName("Bay Service Tests")
internal class BayServiceTest {

    val repository: BayRepository = mockk()

    val service = BayService(repository)

    @BeforeEach
    fun resetAllMocks() {
        clearMocks(repository)
    }

    @Nested
    @DisplayName("getAllBays()")
    inner class GetAllBays {

        @Test
        fun `No Bays`() {
            every { repository.findAll() } returns listOf()
            assertThat(service.getAllBays()).isEmpty()
            verifyAll { repository.findAll() }
        }

        @Test
        fun `Some Bays`() {
            every { repository.findAll() } returns listOf(Bay("Any", true))
            assertThat(service.getAllBays()).hasSize(1)
            verifyAll { repository.findAll() }
        }
    }

}