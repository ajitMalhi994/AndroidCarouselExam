package com.example.androidexam.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.androidexam.R
import com.example.androidexam.data.ListItem

@BindingAdapter("loadImageTo")
fun ImageView.loadImageTo(url: String?) {
    if (url.isNullOrEmpty()) return
    load(url){
        placeholder(R.drawable.pager_1)
    }
}

fun View.makeGoneVisible(list: ArrayList<ListItem>){
    if (list.isEmpty()) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}