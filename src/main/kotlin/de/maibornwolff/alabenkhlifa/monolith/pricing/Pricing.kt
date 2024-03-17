package de.maibornwolff.alabenkhlifa.monolith.pricing

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
data class Pricing(
    @Id
    @GeneratedValue
    val pricingId: Long = 0,
    val productId: Long = 0,
    val basePrice: Double = 0.0,
    val discount: Double = 0.0
)
