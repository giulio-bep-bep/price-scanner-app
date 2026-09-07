package com.pricescanner.database

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ProductTest {

    private lateinit var product: Product

    @Before
    fun setup() {
        product = Product(barcode = "1234567890", productName = "Test Product")
    }

    @Test
    fun verifyProductProperties() {
        assertEquals("1234567890", product.barcode)
        assertEquals("Test Product", product.productName)
    }

    @Test
    fun verifyProductTimestamp() {
        val beforeTime = System.currentTimeMillis()
        val newProduct = Product(barcode = "test", productName = "Test")
        val afterTime = System.currentTimeMillis()

        assert(newProduct.createdAt >= beforeTime)
        assert(newProduct.createdAt <= afterTime)
    }
}
