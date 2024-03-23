package de.maibornwolff.alabenkhlifa.monolith.product_service.pricing

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class PricingServiceTest {

    private lateinit var mockPricingRepository: PricingRepository

    @BeforeEach
    fun setUp() {
        mockPricingRepository = mockk()
    }

    @Test
    fun `getItemPriceById should return the price after discount`() {
        // given
        val pricingService = createPricingService()
        val productId = 1L
        val pricing = Pricing(productId = productId, basePrice = 100.0, discount = 10.0)
        every { mockPricingRepository.findById(productId) } returns Optional.of(pricing)

        // when
        val price = pricingService.getItemPriceById(productId)

        // then
        assertEquals(90.0, price)
    }

    @Test
    fun `getItemPriceById should throw IllegalArgumentException if item not found`() {
        // given
        val pricingService = createPricingService()
        val productId = 1L
        every { mockPricingRepository.findById(productId) } returns Optional.empty()

        // when
        val exception = assertThrows<IllegalArgumentException> { pricingService.getItemPriceById(productId) }

        // then
        assertEquals("Item not found with id $productId", exception.message)
    }

    @Test
    fun `getItemPriceById should throw IllegalArgumentException if base price is negative`() {
        // given
        val pricingService = createPricingService()
        val productId = 1L
        val pricing = Pricing(productId = productId, basePrice = -100.0, discount = 10.0)
        every { mockPricingRepository.findById(productId) } returns Optional.of(pricing)

        // when
        val exception = assertThrows<IllegalArgumentException> { pricingService.getItemPriceById(productId) }

        // then
        assertEquals("Base price of the item with id $productId is negative", exception.message)
    }

    @Test
    fun `getItemPriceByIds should return the price after discount for each item`() {
        // given
        val pricingService = createPricingService()
        val productIds = listOf(1L, 2L, 3L)
        val pricing1 = Pricing(productId = 1L, basePrice = 100.0, discount = 10.0)
        val pricing2 = Pricing(productId = 2L, basePrice = 200.0, discount = 20.0)
        val pricing3 = Pricing(productId = 3L, basePrice = 300.0, discount = 30.0)
        every { mockPricingRepository.findById(1L) } returns Optional.of(pricing1)
        every { mockPricingRepository.findById(2L) } returns Optional.of(pricing2)
        every { mockPricingRepository.findById(3L) } returns Optional.of(pricing3)

        // when
        val prices = pricingService.getItemPriceByIds(productIds)

        // then
        val expectedResult = mapOf(
            1L to 90.0,
            2L to 160.0,
            3L to 210.0
        )
        assertEquals(expectedResult, prices)
    }

    private fun createPricingService() = PricingService(mockPricingRepository)
}