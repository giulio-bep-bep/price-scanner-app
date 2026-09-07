package com.pricescanner.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var database: AppDatabase
    private lateinit var productDao: ProductDao
    private lateinit var priceDao: PriceDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        productDao = database.productDao()
        priceDao = database.priceDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndRetrieveProduct() = runBlocking {
        val product = Product(barcode = "1234567890", productName = "Test Product")
        productDao.insertProduct(product)

        val retrieved = productDao.getProductByBarcode("1234567890")
        assertNotNull(retrieved)
        assertEquals("Test Product", retrieved.productName)
    }

    @Test
    fun insertAndRetrievePrice() = runBlocking {
        val price = PriceRecord(barcode = "1234567890", price = 5.99, store = "Supermarket")
        priceDao.insertPrice(price)

        val result = priceDao.getAveragePriceByBarcode("1234567890")
        assertNotNull(result)
        assertEquals(5.99, result)
    }

    @Test
    fun calculateAveragePrice() = runBlocking {
        priceDao.insertPrice(PriceRecord(barcode = "9876543210", price = 10.0))
        priceDao.insertPrice(PriceRecord(barcode = "9876543210", price = 20.0))
        priceDao.insertPrice(PriceRecord(barcode = "9876543210", price = 30.0))

        val average = priceDao.getAveragePriceByBarcode("9876543210")
        assertNotNull(average)
        assertEquals(20.0, average)
    }

    @Test
    fun findLowestPrice() = runBlocking {
        priceDao.insertPrice(PriceRecord(barcode = "5555555555", price = 10.0, store = "Store A"))
        priceDao.insertPrice(PriceRecord(barcode = "5555555555", price = 5.0, store = "Store B"))
        priceDao.insertPrice(PriceRecord(barcode = "5555555555", price = 15.0, store = "Store C"))

        val lowest = priceDao.getLowestPriceByBarcode("5555555555")
        assertNotNull(lowest)
        assertEquals(5.0, lowest.price)
        assertEquals("Store B", lowest.store)
    }

    @Test
    fun findHighestPrice() = runBlocking {
        priceDao.insertPrice(PriceRecord(barcode = "4444444444", price = 10.0, store = "Store A"))
        priceDao.insertPrice(PriceRecord(barcode = "4444444444", price = 5.0, store = "Store B"))
        priceDao.insertPrice(PriceRecord(barcode = "4444444444", price = 15.0, store = "Store C"))

        val highest = priceDao.getHighestPriceByBarcode("4444444444")
        assertNotNull(highest)
        assertEquals(15.0, highest.price)
        assertEquals("Store C", highest.store)
    }
}
