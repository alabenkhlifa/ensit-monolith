package de.maibornwolff.alabenkhlifa.monolith.repository

import de.maibornwolff.alabenkhlifa.monolith.entity.Pricing
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PricingRepository: JpaRepository<Pricing, Long>