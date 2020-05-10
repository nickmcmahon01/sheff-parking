package uk.gov.digital.justice.hmpps.parking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ParkingApplication

fun main(args: Array<String>) {
    runApplication<ParkingApplication>(*args)
}
