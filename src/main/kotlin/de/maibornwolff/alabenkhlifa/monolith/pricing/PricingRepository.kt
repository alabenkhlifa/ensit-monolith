package de.maibornwolff.alabenkhlifa.monolith.pricing

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PricingRepository: JpaRepository<Pricing, Long>