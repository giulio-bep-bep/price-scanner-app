package com.pricescanner.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert
    suspend fun insertProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM products WHERE barcode = :barcode")
    suspend fun getProductByBarcode(barcode: String): Product?

    @Query("SELECT * FROM products ORDER BY createdAt DESC")
    fun getAllProducts(): Flow<List<Product>>
}

@Dao
interface PriceDao {
    @Insert
    suspend fun insertPrice(price: PriceRecord)

    @Delete
    suspend fun deletePrice(price: PriceRecord)

    @Query("SELECT * FROM prices WHERE barcode = :barcode ORDER BY date DESC")
    fun getPricesByBarcode(barcode: String): Flow<List<PriceRecord>>

    @Query("SELECT AVG(price) FROM prices WHERE barcode = :barcode")
    suspend fun getAveragePriceByBarcode(barcode: String): Double?

    @Query("SELECT * FROM prices WHERE barcode = :barcode ORDER BY price ASC LIMIT 1")
    suspend fun getLowestPriceByBarcode(barcode: String): PriceRecord?

    @Query("SELECT * FROM prices WHERE barcode = :barcode ORDER BY price DESC LIMIT 1")
    suspend fun getHighestPriceByBarcode(barcode: String): PriceRecord?

    @Query("SELECT * FROM prices ORDER BY date DESC LIMIT :limit")
    fun getRecentPrices(limit: Int = 50): Flow<List<PriceRecord>>
}
