package com.pricescanner.price

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pricescanner.database.PriceRecord
import com.pricescanner.databinding.ItemPriceBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PriceHistoryAdapter(private val prices: List<PriceRecord>) :
    RecyclerView.Adapter<PriceHistoryAdapter.PriceViewHolder>() {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceViewHolder {
        val binding = ItemPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PriceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PriceViewHolder, position: Int) {
        holder.bind(prices[position])
    }

    override fun getItemCount(): Int = prices.size

    inner class PriceViewHolder(private val binding: ItemPriceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(price: PriceRecord) {
            binding.tvPrice.text = "€${"%.2f".format(price.price)}"
            binding.tvStore.text = price.store ?: "Negozio sconosciuto"
            binding.tvDate.text = dateFormat.format(Date(price.date))
        }
    }
}
