package de.maibornwolff.alabenkhlifa.monolith.repository

import de.maibornwolff.alabenkhlifa.monolith.entity.Order
import de.maibornwolff.alabenkhlifa.monolith.entity.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<Order, Long> {
    fun findByStatus(orderStatus: OrderStatus): List<Order>
}