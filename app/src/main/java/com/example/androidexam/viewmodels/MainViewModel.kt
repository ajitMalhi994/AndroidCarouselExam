package com.example.androidexam.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidexam.data.Carousel
import com.example.androidexam.data.ListItem
import com.example.androidexam.utils.Constants.Img_Base_Url
import com.example.androidexam.utils.randomString

class MainViewModel: ViewModel() {

    private val carouselItems = ArrayList<Carousel>()

    val listItemsData = MutableLiveData<ArrayList<ListItem>>()
    private val listDataForCarouselMap = HashMap<Int, ArrayList<ListItem>>()

    fun initPagerItems(): ArrayList<Carousel> {
        for(i in 1..3){
            carouselItems.add(Carousel(i, Img_Base_Url+(500+i)))
        }
        return carouselItems
    }

    fun initLabelListItems(pagerItem: Int) {
        if (listDataForCarouselMap.containsKey(pagerItem)){
            listItemsData.postValue(listDataForCarouselMap[pagerItem])
        } else {
            val labelItems = ArrayList<ListItem>()
            for (i in 1..14){
                labelItems.add(ListItem(i, "${randomString()} Pager $pagerItem", Img_Base_Url+(200+i)))
            }
            listDataForCarouselMap[pagerItem] = labelItems
            listItemsData.postValue(labelItems)
        }
    }

    fun filterAndUpdate(pagerItem: Int, searchText: String){
        if (searchText.isEmpty()){
            listItemsData.postValue(listDataForCarouselMap[pagerItem])
        } else {
            listItemsData.postValue(listDataForCarouselMap[pagerItem]?.filter { item ->
                item.description.lowercase().contains(searchText.lowercase())
            } as ArrayList<ListItem>?)
        }
    }
}