package de.maibornwolff.alabenkhlifa.monolith.repository

import de.maibornwolff.alabenkhlifa.monolith.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long>