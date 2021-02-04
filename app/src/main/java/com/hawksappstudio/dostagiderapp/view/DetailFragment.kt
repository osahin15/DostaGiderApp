package com.hawksappstudio.dostagiderapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hawksappstudio.dostagiderapp.R
import com.hawksappstudio.dostagiderapp.adapter.FragmentTabAdapter
import com.hawksappstudio.dostagiderapp.adapter.PhotoViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private var imagesList = mutableListOf<Int>()
    private lateinit var photoViewPagerAdapter: PhotoViewPagerAdapter
    private lateinit var fragmentTabAdapter: FragmentTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_detail, container, false)


        fragmentTabAdapter = FragmentTabAdapter(childFragmentManager)
        var viewPager = view.findViewById<ViewPager>(R.id.table_viewpager)
        var tabLayout = view.findViewById<TabLayout>(R.id.table_layout)
        viewPager.adapter = fragmentTabAdapter
        tabLayout.setupWithViewPager(viewPager)

        var photoViewPager = view.findViewById<ViewPager2>(R.id.photoViewPager)
        var dotsIndicator = view.findViewById<TabLayout>(R.id.photoTabLayout)
        postToList()
        photoViewPagerAdapter = PhotoViewPagerAdapter(imagesList)
        photoViewPager.adapter = photoViewPagerAdapter
        photoViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        TabLayoutMediator(dotsIndicator,photoViewPager){tab,position->
        }.attach()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        backButton.setOnClickListener {
            activity?.onBackPressed()
        }

    }

    private fun addToList(image:Int){
        imagesList.add(image)
    }

    private fun postToList(){
        for (i in 1..25){
            addToList(R.drawable.ic_launcher_background)
        }
    }

}