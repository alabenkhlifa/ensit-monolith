package de.maibornwolff.alabenkhlifa.monolith.orderitem

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class OrderItem(
    @Id
    val orderItemId: Long = 0,
    val orderId: Long = 0,
    val productId: Long = 0,
    val quantity: Long = 0,
    val price: Double = 0.0
)
