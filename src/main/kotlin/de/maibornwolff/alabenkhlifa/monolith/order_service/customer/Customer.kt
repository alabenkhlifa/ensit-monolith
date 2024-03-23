package de.maibornwolff.alabenkhlifa.monolith.order_service.customer

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Customer(
    @Id
    @GeneratedValue
    val customerId: Long = 0,
    val firstname: String = "",
    val lastname: String = "",
    val email: String = ""
)