package com.hawksappstudio.dostagiderapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hawksappstudio.dostagiderapp.R
import com.hawksappstudio.dostagiderapp.databinding.FragmentSortBottomSheetBinding
import com.hawksappstudio.dostagiderapp.utils.SortClickListener
import com.hawksappstudio.dostagiderapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_listing.*


class SortBottomSheet : BottomSheetDialogFragment(),SortClickListener{

    private lateinit var binding : FragmentSortBottomSheetBinding
    private val listViewModel: ListViewModel by viewModels({requireParentFragment()})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_sort_bottom_sheet,container,false)

        binding.linearMain.setBackgroundDrawable(resources.getDrawable(R.drawable.bottom_sheet_bg))

        binding.listener = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onSortClick(view: View) {

        listViewModel.clearCar()
        when(view){
            binding.yearAscending->listViewModel.loadList(2,0)
            binding.yearDescending->listViewModel.loadList(2,1)
            binding.dateAscending -> listViewModel.loadList(1,0)
            binding.dateDescending->listViewModel.loadList(1,1)
            binding.priceAscending->listViewModel.loadList(0,0)
            binding.priceDescending->listViewModel.loadList(0,1)
            else -> Log.d("sortClick", "onSortClick: Fail")
        }
        dismiss()
    }


}