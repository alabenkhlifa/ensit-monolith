package de.maibornwolff.alabenkhlifa.monolith.product_service.product

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class ProductServiceTest {

    private lateinit var mockProductRepository: ProductRepository

    @BeforeEach
    fun setUp() {
        mockProductRepository = mockk()
    }

    @Test
    fun `getProductById should return the product with the given id`() {
        // given
        val productService = createProductService()
        val productId = 1L
        val product = Product(productId = productId, name = "Product 1", description = "Description 1")
        every { mockProductRepository.findById(productId) } returns Optional.of(product)

        // when
        val result = productService.getProductById(productId)

        // then
        assertTrue(result.isPresent)
        assertEquals(product, result.get())
    }

    @Test
    fun `getProductById should return null if product not found`() {
        // given
        val productService = createProductService()
        val productId = 1L
        every { mockProductRepository.findById(productId) } returns Optional.empty()

        // when
        val result = productService.getProductById(productId)

        // then
        assertTrue(result.isEmpty)
    }

    @Test
    fun `getAllProducts should return all products`() {
        // given
        val productService = createProductService()
        val products = listOf(
            Product(productId = 1, name = "Product 1", description = "Description 1"),
            Product(productId = 2, name = "Product 2", description = "Description 2")
        )
        every { mockProductRepository.findAll() } returns products

        // when
        val result = productService.getAllProducts()

        // then
        assertEquals(products, result)
    }

    @Test
    fun `createOrUpdateProduct should return the created product`() {
        // given
        val productService = createProductService()
        val product = Product(productId = 1, name = "Product 1", description = "Description 1")
        every { mockProductRepository.save(product) } returns product

        // when
        val result = productService.createOrUpdateProduct(product)

        // then
        assertEquals(product, result)
    }

    @Test
    fun `deleteProduct should delete the product with the given id`() {
        // given
        val productService = createProductService()
        val productId = 1L
        every { mockProductRepository.deleteById(productId) } returns Unit

        // when
        productService.deleteProduct(productId)

        // then
        verify { mockProductRepository.deleteById(productId) }
    }

    private fun createProductService() = ProductService(mockProductRepository)
}