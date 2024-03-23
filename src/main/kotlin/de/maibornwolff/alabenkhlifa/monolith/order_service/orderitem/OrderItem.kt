package de.maibornwolff.alabenkhlifa.monolith.order_service.orderitem

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
data class OrderItem(
    @Id
    @GeneratedValue
    val orderItemId: Long = 0,
    val orderId: Long = 0,
    val productId: Long = 0,
    val quantity: Long = 0,
    val price: Double = 0.0
)
