package de.maibornwolff.alabenkhlifa.monolith.service

import de.maibornwolff.alabenkhlifa.monolith.entity.Pricing
import de.maibornwolff.alabenkhlifa.monolith.repository.PricingRepository
import org.springframework.stereotype.Service

@Service
class OrderService(private val pricingRepository: PricingRepository) {

    fun getItemPriceById(itemId: Long): Double {
        val pricing = pricingRepository.findById(itemId)
        if(pricing.isEmpty) {
            throw IllegalArgumentException("Item not found with id $itemId")
        }
        if(pricing.get().basePrice < 0) {
            throw IllegalArgumentException("Base price of the item with id $itemId is negative")
        }
        return calculatePriceAfterDiscount(pricing.get())
    }

    private fun calculatePriceAfterDiscount(pricing: Pricing): Double {
        val basePrice = pricing.basePrice
        val discount = pricing.discount
        return basePrice - (basePrice * discount / 100)
    }
}