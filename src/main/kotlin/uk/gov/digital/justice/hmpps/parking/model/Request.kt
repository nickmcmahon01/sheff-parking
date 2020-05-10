package uk.gov.digital.justice.hmpps.parking.model

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "REQUEST")
data class Request(

        @OneToOne
        @JoinColumn(name = "USER_ID", referencedColumnName = "UUID")
        val user: ParkingUser,

        @Column(name = "DAYOFYEAR", nullable = false)
        val dayOfYear: Int,

        @Column(name = "PRIORITY", nullable = false)
        val priority: Int,

        @Column(name = "ACTIVE")
        var active: Boolean = true

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

