package de.maibornwolff.alabenkhlifa.monolith.product_service.inventory

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Inventory(
    @Id
    @GeneratedValue
    val inventoryId: Long = 0,
    val productId: Long = 0,
    val quantityOnHand: Long = 0
)
