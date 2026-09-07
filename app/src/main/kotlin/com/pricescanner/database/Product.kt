package com.pricescanner.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val barcode: String,
    val productName: String? = null,
    val category: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "prices")
data class PriceRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val barcode: String,
    val price: Double,
    val store: String? = null,
    val date: Long = System.currentTimeMillis()
)
