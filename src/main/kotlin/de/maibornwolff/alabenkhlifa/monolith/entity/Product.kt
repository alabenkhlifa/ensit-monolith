package de.maibornwolff.alabenkhlifa.monolith.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Product(
    @Id
    val productId: Long = 0,
    val name: String = "",
    val description: String? = null,
)
