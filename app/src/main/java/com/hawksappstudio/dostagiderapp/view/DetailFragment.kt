package com.hawksappstudio.dostagiderapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hawksappstudio.dostagiderapp.R
import com.hawksappstudio.dostagiderapp.adapter.FragmentTabAdapter
import com.hawksappstudio.dostagiderapp.adapter.PhotoViewPagerAdapter
import com.hawksappstudio.dostagiderapp.databinding.FragmentDetailBinding
import com.hawksappstudio.dostagiderapp.model.DetailModel
import com.hawksappstudio.dostagiderapp.model.DetailModel.DetailEntity
import com.hawksappstudio.dostagiderapp.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.table_layout.view.*


class DetailFragment : Fragment() {


   private lateinit var binding : FragmentDetailBinding

    private var imagesList = mutableListOf<Int>()


    private lateinit var photoViewPagerAdapter: PhotoViewPagerAdapter
    private lateinit var fragmentTabAdapter: FragmentTabAdapter

    private var carId : Int?= null

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        carId = arguments?.getInt("carId")

    }

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.loadDetail(carId = carId!!)
        photoViewPagerAdapter = PhotoViewPagerAdapter(arrayListOf())
        observeDetail()




        var photoViewPager =binding.photoViewPager
        var dotsIndicator = binding.photoTabLayout

        photoViewPager.adapter = photoViewPagerAdapter



        photoViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        TabLayoutMediator(dotsIndicator,photoViewPager){tab,position->
        }.attach()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fragmentTabAdapter = FragmentTabAdapter(childFragmentManager)
        var viewPager = binding.tableInclude.tableViewpager
        var tabLayout = binding.tableInclude.tableLayout
        viewPager.adapter = fragmentTabAdapter
        tabLayout.setupWithViewPager(viewPager)


        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }


    }

    private fun bindingDetail(item: DetailEntity){
        binding.detailItem = item
    }

    private fun observeDetail(){

        detailViewModel.detailLiveData.observe(viewLifecycleOwner,{
            it.let { detailItem ->
                bindingDetail(detailItem)
                photoViewPagerAdapter.addList(detailItem.photos)
            }
        })
    }


}