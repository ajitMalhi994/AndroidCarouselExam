package com.example.androidexam.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidexam.R
import com.example.androidexam.data.Carousel
import androidx.recyclerview.widget.ListAdapter
import com.example.androidexam.databinding.ItemPagerImageBinding

class CarouselAdapter : ListAdapter<Carousel, CarouselAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binderObj = DataBindingUtil.inflate<ItemPagerImageBinding>(LayoutInflater.from(parent.context), R.layout.item_pager_image, parent, false)
        return ItemViewHolder(binderObj)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindingObj.image = getItem(position)
    }

    class ItemViewHolder(var bindingObj: ItemPagerImageBinding) : RecyclerView.ViewHolder(bindingObj.root) {
    }
}

class DiffCallback : DiffUtil.ItemCallback<Carousel>() {

    override fun areItemsTheSame(oldItem: Carousel, newItem: Carousel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Carousel, newItem: Carousel): Boolean {
        return oldItem == newItem
    }
}