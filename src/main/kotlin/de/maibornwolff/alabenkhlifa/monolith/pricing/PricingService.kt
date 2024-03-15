package de.maibornwolff.alabenkhlifa.monolith.pricing

import org.springframework.stereotype.Service

@Service
class PricingService(private val pricingRepository: PricingRepository) {

    fun getItemPriceByIds(itemIds: List<Long>): Map<Long, Double> {
        val prices = mutableMapOf<Long, Double>()
        itemIds.forEach { itemId ->
            prices[itemId] = getItemPriceById(itemId)
        }
        return prices
    }

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
