package com.example.androidexam.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.androidexam.adapters.ListItemsAdapter
import com.example.androidexam.adapters.CarouselAdapter
import com.example.androidexam.data.ListItem
import com.example.androidexam.databinding.ActivityMainBinding
import com.example.androidexam.utils.afterPageScrolled
import com.example.androidexam.utils.afterTextChanged
import com.example.androidexam.utils.makeGoneVisible
import com.example.androidexam.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var listItemsAdapter: ListItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initData()
    }

    fun initData(){
        carouselAdapter = CarouselAdapter()
        listItemsAdapter = ListItemsAdapter()
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setPagerAdapter()
        observeData()
        updateDataOnViewPagerScroll()

        mBinding.apply {
            edtSearch.afterTextChanged { mainViewModel.filterAndUpdate(viewPager.currentItem, it) }
        }
    }

    fun observeData(){
        mainViewModel.apply {

            listItemsData.observe(this@MainActivity){
                it.let {
                    updateLabelListData(it)
                }
                mBinding.tvError.makeGoneVisible(it)
            }
        }
    }

    fun setPagerAdapter(){
        mBinding.apply {

            carouselAdapter.submitList(mainViewModel.initPagerItems())

            viewPager.adapter = carouselAdapter

            TabLayoutMediator(pagerTabLayout, viewPager){ tab, position -> }.attach()

            rvLabelList.adapter = listItemsAdapter

            mainViewModel.initLabelListItems(viewPager.currentItem)
        }
    }

    private fun updateLabelListData(labelList: ArrayList<ListItem>){
        listItemsAdapter.submitList(labelList)
    }

    private fun updateDataOnViewPagerScroll(){
        mBinding.viewPager.afterPageScrolled {
            mainViewModel.initLabelListItems(it)
        }
    }
}