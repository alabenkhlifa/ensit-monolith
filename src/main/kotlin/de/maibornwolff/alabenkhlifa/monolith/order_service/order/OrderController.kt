package de.maibornwolff.alabenkhlifa.monolith.order_service.order

import org.springframework.web.bind.annotation.*

@RestController
class OrderController(private val orderService: OrderService) {

    @GetMapping("/orders")
    fun getOrders() = orderService.getOrders()

    @GetMapping("/orders/{orderId}")
    fun getOrderById(@PathVariable orderId: Long) = orderService.getOrderById(orderId)

    @PostMapping("/order")
    fun createOrder(order: Order) = orderService.createOrUpdateOrder(order)

    @PutMapping("/order")
    fun updateOrder(order: Order) = orderService.createOrUpdateOrder(order)

    @DeleteMapping("/order/{orderId}")
    fun deleteOrder(@PathVariable orderId: Long) = orderService.deleteOrder(orderId)

    @GetMapping("/orders/pending")
    fun getPendingOrders() = orderService.getPendingOrders()

    @GetMapping("/orders/shipped")
    fun getShippedOrders() = orderService.getShippedOrders()

    @GetMapping("/orders/delivered")
    fun getDeliveredOrders() = orderService.getDeliveredOrders()
}