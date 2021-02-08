package com.hawksappstudio.dostagiderapp.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hawksappstudio.dostagiderapp.R
import com.hawksappstudio.dostagiderapp.adapter.DetailPhotoPagerAdapter
import com.hawksappstudio.dostagiderapp.viewmodel.DetailViewModel


class PhotoDetailFragment : Fragment() {


    lateinit var detailPhotoPagerAdapter: DetailPhotoPagerAdapter
    private lateinit var detailViewModel : DetailViewModel

    private var images : ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailPhotoPagerAdapter = DetailPhotoPagerAdapter(arrayListOf())
        images = arguments?.getStringArrayList("images")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_photo_detail, container, false)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)


       detailPhotoPagerAdapter.addList(images!!)
        var detailViewPager = view.findViewById<ViewPager2>(R.id.detailViewPager)
        val detailTabLayout = view.findViewById<TabLayout>(R.id.detailTabLayout)

        detailViewPager.adapter = detailPhotoPagerAdapter
        TabLayoutMediator(detailTabLayout,detailViewPager){tab,position->}.attach()

        return view
    }

}