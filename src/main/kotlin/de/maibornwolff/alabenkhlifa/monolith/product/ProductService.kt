package de.maibornwolff.alabenkhlifa.monolith.product

import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

        fun getProductById(productId: Long) = productRepository.findById(productId)

        fun getAllProducts(): MutableList<Product> = productRepository.findAll()

        fun createOrUpdateProduct(product: Product) = productRepository.save(product)

        fun deleteProduct(productId: Long) = productRepository.deleteById(productId)

}
