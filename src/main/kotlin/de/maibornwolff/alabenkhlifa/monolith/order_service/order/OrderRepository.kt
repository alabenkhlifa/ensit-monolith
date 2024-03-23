package de.maibornwolff.alabenkhlifa.monolith.order_service.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<Order, Long> {
    fun findByStatus(orderStatus: OrderStatus): List<Order>
}