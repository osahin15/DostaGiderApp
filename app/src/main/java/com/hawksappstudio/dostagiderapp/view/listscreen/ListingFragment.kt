package com.hawksappstudio.dostagiderapp.view.listscreen

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
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
import java.util.*


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
                    shimmer.visibility = View.VISIBLE
                    progressList.visibility = View.VISIBLE
                    carRecycler.visibility = View.GONE
                }else{
                    shimmer.stopShimmer()
                    shimmer.visibility = View.GONE
                    progressList.visibility = View.GONE
                }

            }
        })
        listViewModel.errorList.observe(viewLifecycleOwner,{
            it.let {
                if (it){
                    shimmer.stopShimmer()
                    shimmer.visibility = View.GONE
                    progressList.visibility = View.GONE
                    carRecycler.visibility = View.GONE
                    errorListing.visibility = View.VISIBLE
                }else{
                    errorListing.visibility = View.GONE
                }
            }
        })

    }

    override fun onStop() {
        super.onStop()
        shimmer.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        shimmer.startShimmer()
    }


   private fun filterYear(minYear:Int,maxYear:Int){
            shimmer.startShimmer()
            listViewModel.clearCar()
            listViewModel.filterList(minYear,maxYear)
    }



        fun openFilter(){
            val mFilterDialog = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog,null)

           val filterMaxEdit = mFilterDialog.filterMaxYear
           val filterMinEdit = mFilterDialog.filterMinYear

            var minYear = 0
            var maxYear = Calendar.getInstance().get(Calendar.YEAR) + 1


            AlertDialog.Builder(context).setView(mFilterDialog).setTitle("Yıla Göre Filtrele")
                    .setPositiveButton("Uygula", DialogInterface.OnClickListener { dialog, which ->


                        Log.d("denemeTag", "openFilter: $minYear $maxYear")
                        filterYear(minYear, maxYear)
                    }).setNegativeButton("Çık", DialogInterface.OnClickListener { dialog, which ->
                       dialog.dismiss()
                   }).show()

            filterMinEdit.addTextChangedListener(object  : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    if (filterMinEdit.text.isEmpty()){
                        minYear = 0
                    }else{
                        minYear = filterMinEdit.text.toString().toInt()
                    }
                }

            })
            filterMaxEdit.addTextChangedListener(object  : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    if(filterMaxEdit.text.isEmpty()){
                        //yanlış girilirse en çok mevcut yıl +1
                        maxYear = Calendar.getInstance().get(Calendar.YEAR) + 1
                    }else{
                        maxYear = filterMaxEdit.text.toString().toInt()
                    }
                }

            })




        }


        fun openSorting(){
            val sortingBottomSheet = SortBottomSheet()

                if (!sortingBottomSheet.isVisible){
                    sortingBottomSheet.show(childFragmentManager,"sort")
            }
    }


}


