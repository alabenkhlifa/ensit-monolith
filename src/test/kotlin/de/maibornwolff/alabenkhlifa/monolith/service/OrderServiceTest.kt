package de.maibornwolff.alabenkhlifa.monolith.service

import de.maibornwolff.alabenkhlifa.monolith.order.Order
import de.maibornwolff.alabenkhlifa.monolith.order.OrderStatus
import de.maibornwolff.alabenkhlifa.monolith.order.OrderService
import de.maibornwolff.alabenkhlifa.monolith.order.OrderRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class OrderServiceTest {

    private lateinit var mockOrderRepository: OrderRepository

    @BeforeEach
    fun setUp() {
        mockOrderRepository = mockk()
    }

    @Test
    fun `getOrders should return all orders`() {
        // given
        val orderService = createOrderService()
        val orders = listOf(
            Order(orderId = 1, status = OrderStatus.PENDING),
            Order(orderId = 2, status = OrderStatus.SHIPPED),
            Order(orderId = 3, status = OrderStatus.DELIVERED)
        )
        every { mockOrderRepository.findAll() } returns orders

        // when
        val result = orderService.getOrders()

        // then
        assertEquals(3, result.size)
    }

    @Test
    fun `getPendingOrders should return all pending orders`() {
        // given
        val orderService = createOrderService()
        val orders = listOf(
            Order(orderId = 1, status = OrderStatus.PENDING),
            Order(orderId = 2, status = OrderStatus.PENDING)
        )
        every { mockOrderRepository.findByStatus(OrderStatus.PENDING) } returns orders

        // when
        val result = orderService.getPendingOrders()

        // then
        assertEquals(2, result.size)
    }

    @Test
    fun `getShippedOrders should return all shipped orders`() {
        // given
        val orderService = createOrderService()
        val orders = listOf(
            Order(orderId = 1, status = OrderStatus.SHIPPED),
            Order(orderId = 2, status = OrderStatus.SHIPPED)
        )
        every { mockOrderRepository.findByStatus(OrderStatus.SHIPPED) } returns orders

        // when
        val result = orderService.getShippedOrders()

        // then
        assertEquals(2, result.size)
    }

    @Test
    fun `getDeliveredOrders should return all delivered orders`() {
        // given
        val orderService = createOrderService()
        val orders = listOf(
            Order(orderId = 1, status = OrderStatus.DELIVERED),
            Order(orderId = 2, status = OrderStatus.DELIVERED)
        )
        every { mockOrderRepository.findByStatus(OrderStatus.DELIVERED) } returns orders

        // when
        val result = orderService.getDeliveredOrders()

        // then
        assertEquals(2, result.size)
    }

    @Test
    fun `getOrderById should return the order`() {
        // given
        val orderService = createOrderService()
        val orderId = 1L
        val order = Order(orderId = orderId, status = OrderStatus.PENDING)
        every { mockOrderRepository.findById(orderId) } returns Optional.of(order)

        // when
        val result = orderService.getOrderById(orderId)

        // then
        assertTrue(result.isPresent)
        assertEquals(order, result.get())
    }

    @Test
    fun `getOrderById should return null if order not found`() {
        // given
        val orderService = createOrderService()
        val orderId = 1L
        every { mockOrderRepository.findById(orderId) } returns Optional.empty()

        // when
        val result = orderService.getOrderById(orderId)

        // then
        assertTrue(result.isEmpty)
    }

    @Test
    fun `createOrUpdateOrder should return the created order`() {
        // given
        val orderService = createOrderService()
        val order = Order(orderId = 1, status = OrderStatus.PENDING)
        every { mockOrderRepository.save(order) } returns order

        // when
        val result = orderService.createOrUpdateOrder(order)

        // then
        assertEquals(order, result)
    }

    @Test
    fun `deleteOrder should delete the order`() {
        // given
        val orderService = createOrderService()
        val orderId = 1L
        every { mockOrderRepository.deleteById(orderId) } returns Unit

        // when
        orderService.deleteOrder(orderId)

        // then
        verify { mockOrderRepository.deleteById(orderId) }
    }

    private fun createOrderService() = OrderService(mockOrderRepository)
}