package de.maibornwolff.alabenkhlifa.monolith.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class Order(
    @Id
    val orderId: Long = 0,
    val customerId: Long = 0,
    val orderDate: LocalDate = LocalDate.now(),
    val status: OrderStatus = OrderStatus.PENDING,
)

enum class OrderStatus {
    PENDING, SHIPPED, DELIVERED
}