package de.maibornwolff.alabenkhlifa.monolith.service

import de.maibornwolff.alabenkhlifa.monolith.entity.Product
import de.maibornwolff.alabenkhlifa.monolith.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

        fun getProductById(productId: Long) = productRepository.findById(productId)

        fun getAllProducts(): MutableList<Product> = productRepository.findAll()

        fun createOrUpdateProduct(product: Product) = productRepository.save(product)

        fun deleteProduct(productId: Long) = productRepository.deleteById(productId)

}
