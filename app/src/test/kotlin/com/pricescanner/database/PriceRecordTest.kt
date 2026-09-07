package com.pricescanner.database

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class PriceRecordTest {

    private lateinit var priceRecord: PriceRecord

    @Before
    fun setup() {
        priceRecord = PriceRecord(barcode = "1234567890", price = 5.99, store = "Supermarket")
    }

    @Test
    fun verifyPriceRecordProperties() {
        assertEquals("1234567890", priceRecord.barcode)
        assertEquals(5.99, priceRecord.price)
        assertEquals("Supermarket", priceRecord.store)
    }

    @Test
    fun verifyPriceIsPositive() {
        assert(priceRecord.price > 0)
    }

    @Test
    fun verifyPriceTimestamp() {
        val beforeTime = System.currentTimeMillis()
        val newPrice = PriceRecord(barcode = "test", price = 10.0)
        val afterTime = System.currentTimeMillis()

        assert(newPrice.date >= beforeTime)
        assert(newPrice.date <= afterTime)
    }
}
