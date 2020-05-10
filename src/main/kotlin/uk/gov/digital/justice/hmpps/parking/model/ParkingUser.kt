package uk.gov.digital.justice.hmpps.parking.model

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import uk.gov.digital.justice.hmpps.parking.ParkingUserType
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "PARKINGUSER")
data class ParkingUser(

        @Column(name = "DISPLAY_NAME")
        val displayName: String,

        @Column(name = "EMAIL")
        val email: String,

        @Column(name = "USER_TYPE")
        @Enumerated(value = EnumType.STRING)
        val userType: ParkingUserType,

        @Column(name = "REGISTRATION")
        val registration: String,

        @Column(name = "HAS_ACCESS_NEEDS")
        val accessNeeds: Boolean,

        @Column(name = "PRIORITY")
        var priority: Boolean,

        @Column(name = "ACTIVE")
        val active: Boolean = true
) {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "UUID", updatable = false, nullable = false)
    val uuid: UUID? = null

    @CreatedDate
    @Column(nullable = false)
    val createDateTime: LocalDateTime? = null
}

