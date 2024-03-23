package de.maibornwolff.alabenkhlifa.monolith.order_service.order

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
data class Order(
    @Id
    @GeneratedValue
    val orderId: Long = 0,
    val customerId: Long = 0,
    val orderDate: LocalDate = LocalDate.now(),
    val status: OrderStatus = OrderStatus.PENDING,
)

enum class OrderStatus {
    PENDING, SHIPPED, DELIVERED
}