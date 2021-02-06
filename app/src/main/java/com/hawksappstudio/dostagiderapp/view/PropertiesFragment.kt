package com.hawksappstudio.dostagiderapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.hawksappstudio.dostagiderapp.R
import com.hawksappstudio.dostagiderapp.adapter.FragmentTabAdapter
import com.hawksappstudio.dostagiderapp.databinding.FragmentPropertiesBinding
import com.hawksappstudio.dostagiderapp.model.DetailModel
import com.hawksappstudio.dostagiderapp.model.PropertiesEntity
import com.hawksappstudio.dostagiderapp.viewmodel.DetailViewModel
import kotlin.math.log


class PropertiesFragment : Fragment() {

    lateinit var binding : FragmentPropertiesBinding
    private val detailViewModel : DetailViewModel by viewModels({requireParentFragment()})

    private var carId : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_properties,container,false)

        detailViewModel.detailLiveData.observe(viewLifecycleOwner, { detailItem ->
            Log.d("propertiesDetailItem", "onViewCreated: $detailItem")
            binding.propertiesItem = detailItem
            propertiesList(detailItem.properties)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


    fun propertiesList(list : List<PropertiesEntity>){
        val hashMap = HashMap<String,String>()
        for (item in list){
            hashMap.put(item.name,item.value)
        }
        Log.d("tagKM", "onCreateView: ${hashMap.get("km")}")
        binding.propertiesMap = hashMap
    }

}