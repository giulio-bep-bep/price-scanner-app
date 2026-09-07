package com.pricescanner.price

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pricescanner.comparison.ComparisonActivity
import com.pricescanner.database.AppDatabase
import com.pricescanner.database.PriceRecord
import com.pricescanner.database.Product
import com.pricescanner.databinding.ActivityPriceInputBinding
import kotlinx.coroutines.launch

class PriceInputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPriceInputBinding
    private lateinit var database: AppDatabase
    private var barcode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPriceInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getDatabase(this)
        barcode = intent.getStringExtra("barcode")

        binding.tvBarcode.text = "Barcode: $barcode"

        binding.btnSavePrice.setOnClickListener {
            savePriceData()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun savePriceData() {
        val priceText = binding.etPrice.text.toString().trim()
        val storeName = binding.etStoreName.text.toString().trim()
        val productName = binding.etProductName.text.toString().trim()

        if (priceText.isEmpty()) {
            Toast.makeText(this, "Inserisci il prezzo", Toast.LENGTH_SHORT).show()
            return
        }

        val price = priceText.toDoubleOrNull()
        if (price == null || price <= 0) {
            Toast.makeText(this, "Prezzo non valido", Toast.LENGTH_SHORT).show()
            return
        }

        barcode?.let {
            lifecycleScope.launch {
                try {
                    // Salva il prodotto
                    val product = Product(
                        barcode = it,
                        productName = productName.ifEmpty { null },
                        category = null
                    )
                    database.productDao().insertProduct(product)

                    // Salva il prezzo
                    val priceRecord = PriceRecord(
                        barcode = it,
                        price = price,
                        store = storeName.ifEmpty { null }
                    )
                    database.priceDao().insertPrice(priceRecord)

                    Toast.makeText(
                        this@PriceInputActivity,
                        "Prezzo salvato con successo!",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Vai alla schermata di confronto
                    val intent = Intent(this@PriceInputActivity, ComparisonActivity::class.java)
                    intent.putExtra("barcode", it)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(
                        this@PriceInputActivity,
                        "Errore nel salvataggio: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
