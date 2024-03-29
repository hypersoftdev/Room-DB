package com.hypersoft.roomdb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hypersoft.roomdb.R
import com.hypersoft.roomdb.databinding.ItemCountryBinding
import com.hypersoft.roomdb.interfaces.OnCountryItemClickListener
import com.hypersoft.roomdb.db.tables.CountryTable

/**
 * @Author: Muhammad Yaqoob
 * @Date: 29,March,2024.
 * @Accounts
 *      -> https://github.com/orbitalsonic
 *      -> https://www.linkedin.com/in/myaqoob7
 */
class AdapterCountry(private val onCountryItemClickListener: OnCountryItemClickListener) : ListAdapter<CountryTable, AdapterCountry.CustomViewHolder>(diffUtilCountryTable) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCountryBinding>(layoutInflater, R.layout.item_country, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            item = currentItem
            itemClick = onCountryItemClickListener
        }
    }

    inner class CustomViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffUtilCountryTable = object : DiffUtil.ItemCallback<CountryTable>() {
            override fun areItemsTheSame(oldItem: CountryTable, newItem: CountryTable): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CountryTable, newItem: CountryTable): Boolean {
                return oldItem == newItem
            }
        }
    }
}