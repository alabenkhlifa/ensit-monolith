package de.maibornwolff.alabenkhlifa.monolith.service

import de.maibornwolff.alabenkhlifa.monolith.entity.Pricing
import de.maibornwolff.alabenkhlifa.monolith.repository.PricingRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class OrderServiceTest {

    private lateinit var mockPricingRepository: PricingRepository

    @BeforeEach
    fun setUp() {
        mockPricingRepository = mockk()
    }

    @Test
    fun `getItemPriceById should return the price after discount`() {
        // given
        val orderService = createOrderService()
        val itemId = 1L
        val pricing = Pricing(productId = itemId, basePrice = 100.0, discount = 10.0)
        every { mockPricingRepository.findById(itemId) } returns Optional.of(pricing)

        // when
        val price = orderService.getItemPriceById(itemId)

        // then
        assertEquals(90.0, price)
    }

    @Test
    fun `getItemPriceById should throw IllegalArgumentException if item not found`() {
        // given
        val orderService = createOrderService()
        val itemId = 1L
        every { mockPricingRepository.findById(itemId) } returns Optional.empty()

        // when
        val exception = assertThrows<IllegalArgumentException> { orderService.getItemPriceById(itemId) }

        // then
        assertEquals("Item not found with id $itemId", exception.message)
    }

    @Test
    fun `getItemPriceById should throw IllegalArgumentException if base price is negative`() {
        // given
        val orderService = createOrderService()
        val itemId = 1L
        val pricing = Pricing(productId = itemId, basePrice = -100.0, discount = 10.0)
        every { mockPricingRepository.findById(itemId) } returns Optional.of(pricing)

        // when
        val exception = assertThrows<IllegalArgumentException> { orderService.getItemPriceById(itemId) }

        // then
        assertEquals("Base price of the item with id $itemId is negative", exception.message)
    }

    private fun createOrderService(): OrderService {
        val orderService = OrderService(mockPricingRepository)
        return orderService
    }
}