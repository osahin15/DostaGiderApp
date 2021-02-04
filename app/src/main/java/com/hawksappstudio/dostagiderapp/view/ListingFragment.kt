package com.hawksappstudio.dostagiderapp.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hawksappstudio.dostagiderapp.R
import com.hawksappstudio.dostagiderapp.adapter.CarListingAdapter
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*
import kotlinx.android.synthetic.main.fragment_listing.*


class ListingFragment : Fragment() {

    private lateinit var carListingAdapter: CarListingAdapter
    private var carList : ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carList.add("araç bir")
        carList.add("araç bir")
        carList.add("araç bir")
        carList.add("araç bir")
        carList.add("araç bir")
        carList.add("araç bir")
        carList.add("araç bir")
        carList.add("araç bir")
        carList.add("araç bir")
        carList.add("araç bir")
        carList.add("araç bir")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        carListingAdapter = CarListingAdapter(carList,requireContext())

        var recycler = view.findViewById<RecyclerView>(R.id.carRecycler)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = carListingAdapter
        recycler.setHasFixedSize(true)
        recycler.isNestedScrollingEnabled = true

        fab_filter.setOnClickListener {
            openFilter()
        }
       fab_sort.setOnClickListener {
           openSorting()
       }


        }


        fun openFilter(){
            val mFilterDialog = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog,null)

               var mBuilder =  AlertDialog.Builder(context).setView(mFilterDialog).setTitle("Yıla Göre Filtrele")
                    .setPositiveButton("Uygula", DialogInterface.OnClickListener { dialog, which ->

                    }).setNegativeButton("Çık", DialogInterface.OnClickListener { dialog, which ->
                       dialog.dismiss()
                   }).show()
        }

        fun openSorting(){
            val sortingBottomSheet = SortBottomSheet()

                if (!sortingBottomSheet.isVisible){
                    sortingBottomSheet.show(childFragmentManager,"sort")
            }
    }


}