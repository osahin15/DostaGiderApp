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
import com.hawksappstudio.dostagiderapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*
import kotlinx.android.synthetic.main.fragment_listing.*


class ListingFragment : Fragment() {

    private lateinit var  carListingAdapter: CarListingAdapter

    lateinit var recycler : RecyclerView
    private lateinit var  listViewModel:ListViewModel

    private lateinit var shimmer : ShimmerFrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("listingFragment", "onCreate: listing")
        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        listViewModel.loadList(1,0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       val view:View = inflater.inflate(R.layout.fragment_listing, container, false)
        Log.d("listingFragment", "onCreateView: Listing Fragment ")
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("listingFragment", "onActivityCreated: listing")

    }

    fun menuVisible(): Boolean{
        if (fab_menu.visibility == View.VISIBLE)
            return true
        return false
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


