package com.hawksappstudio.dostagiderapp.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.scaleMatrix
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.hawksappstudio.dostagiderapp.R
import com.hawksappstudio.dostagiderapp.adapter.CarListingAdapter
import com.hawksappstudio.dostagiderapp.utils.ASCENDING
import com.hawksappstudio.dostagiderapp.utils.DATE
import com.hawksappstudio.dostagiderapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*
import kotlinx.android.synthetic.main.fragment_listing.*
import kotlin.math.max


class ListingFragment : Fragment() {

    private lateinit var  carListingAdapter: CarListingAdapter

    lateinit var recycler : RecyclerView

    private lateinit var  listViewModel:ListViewModel

    private lateinit var shimmer : ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        listViewModel.loadList(DATE, ASCENDING)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       val view:View = inflater.inflate(R.layout.fragment_listing, container, false)

        carListingAdapter = CarListingAdapter()
        shimmer = view.findViewById(R.id.shimmer_layout)
        shimmer.startShimmer()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById<RecyclerView>(R.id.carRecycler)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.setHasFixedSize(true)


        fab_filter.setOnClickListener {
            fab_menu.close(true)
            openFilter()
        }
       fab_sort.setOnClickListener {
           fab_menu.close(true)
           openSorting()
       }
        subscribeToList()

        }



    private fun subscribeToList(){


        listViewModel.carList
            .observe(viewLifecycleOwner, { list ->
                progressList.visibility = View.GONE
               shimmer.stopShimmer()
                shimmer.visibility = View.GONE
                carRecycler.visibility = View.VISIBLE
                carListingAdapter.submitList(list)
                recycler.adapter = carListingAdapter
            })
        listViewModel.loadingList.observe(viewLifecycleOwner,{
            it.let {
                if (it){
                    shimmer.startShimmer()
                    progressList.visibility = View.VISIBLE
                    carRecycler.visibility = View.GONE
                }else{
                    shimmer.stopShimmer()
                    progressList.visibility = View.GONE
                }

            }
        })

    }

    override fun onStop() {
        super.onStop()
        shimmer_layout.stopShimmer()
    }

    fun filterYear(minYear:Int,maxYear:Int){

    }
        fun openFilter(){
            val mFilterDialog = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog,null)

           var mBuilder =     AlertDialog.Builder(context).setView(mFilterDialog).setTitle("Yıla Göre Filtrele")
                    .setPositiveButton("Uygula", DialogInterface.OnClickListener { dialog, which ->
                        var minYear = mFilterDialog.filterMinYear.text.toString().toInt()
                        var maxYear = mFilterDialog.filterMaxYear.text.toString().toInt()
                        filterYear(minYear,maxYear)

                    }).setNegativeButton("Çık", DialogInterface.OnClickListener { dialog, which ->
                       dialog.dismiss()
                   }).show()

            mBuilder.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = !(mFilterDialog.filterMinYear.text.isEmpty() && mFilterDialog.filterMaxYear.text.isEmpty())
        }

        fun openSorting(){
            val sortingBottomSheet = SortBottomSheet()

                if (!sortingBottomSheet.isVisible){
                    sortingBottomSheet.show(childFragmentManager,"sort")
            }
    }


}


