package de.maibornwolff.alabenkhlifa.monolith.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class Customer(
    @Id
    val customerId: Long = 0,
    val firstname: String = "",
    val lastname: String = "",
    val email: String = ""
)
