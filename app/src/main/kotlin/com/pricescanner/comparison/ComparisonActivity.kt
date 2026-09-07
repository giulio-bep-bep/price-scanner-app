package com.pricescanner.comparison

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pricescanner.database.AppDatabase
import com.pricescanner.databinding.ActivityComparisonBinding
import com.pricescanner.price.PriceHistoryAdapter
import kotlinx.coroutines.launch

class ComparisonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComparisonBinding
    private lateinit var database: AppDatabase
    private var barcode: String? = null
    private var averagePrice: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComparisonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getDatabase(this)
        barcode = intent.getStringExtra("barcode")

        binding.tvBarcode.text = "Barcode: $barcode"
        binding.btnBack.setOnClickListener { finish() }

        loadPriceData()
    }

    private fun loadPriceData() {
        barcode?.let { bc ->
            lifecycleScope.launch {
                try {
                    // Calcola il prezzo medio
                    averagePrice = database.priceDao().getAveragePriceByBarcode(bc) ?: 0.0

                    // Ottieni il prezzo più basso
                    val lowestPrice = database.priceDao().getLowestPriceByBarcode(bc)

                    // Ottieni il prezzo più alto
                    val highestPrice = database.priceDao().getHighestPriceByBarcode(bc)

                    // Mostra i dati
                    binding.tvAveragePrice.text = "Media: €${"%.2f".format(averagePrice)}"
                    binding.tvLowestPrice.text = "Minimo: €${"%.2f".format(lowestPrice?.price ?: 0.0)} - ${lowestPrice?.store ?: "N/A"}"
                    binding.tvHighestPrice.text = "Massimo: €${"%.2f".format(highestPrice?.price ?: 0.0)} - ${highestPrice?.store ?: "N/A"}"

                    // Mostra la cronologia dei prezzi
                    database.priceDao().getPricesByBarcode(bc).collect { prices ->
                        val adapter = PriceHistoryAdapter(prices)
                        binding.rvPriceHistory.adapter = adapter
                        binding.rvPriceHistory.layoutManager = LinearLayoutManager(this@ComparisonActivity)
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@ComparisonActivity, "Errore: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
