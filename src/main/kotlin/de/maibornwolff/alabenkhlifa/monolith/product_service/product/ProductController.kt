package de.maibornwolff.alabenkhlifa.monolith.product_service.product

import org.springframework.web.bind.annotation.*

@RestController
class ProductController(private val productService: ProductService) {

    @GetMapping("/products")
    fun getProducts() = productService.getAllProducts()

    @GetMapping("/products/{productId}")
    fun getProductById(@PathVariable productId: Long) = productService.getProductById(productId)

    @PostMapping("/product")
    fun createProduct(product: Product) = productService.createOrUpdateProduct(product)

    @PutMapping("/product")
    fun updateProduct(product: Product) = productService.createOrUpdateProduct(product)

    @DeleteMapping("/product/{productId}")
    fun deleteProduct(@PathVariable productId: Long) = productService.deleteProduct(productId)

}