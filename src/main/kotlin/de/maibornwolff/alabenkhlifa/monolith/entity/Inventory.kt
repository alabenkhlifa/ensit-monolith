package de.maibornwolff.alabenkhlifa.monolith.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class Inventory(
    @Id
    val inventoryId: Long = 0,
    val productId: Long = 0,
    val quantityOnHand: Long = 0
)
