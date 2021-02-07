package com.hawksappstudio.dostagiderapp.view

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hawksappstudio.dostagiderapp.R
import com.hawksappstudio.dostagiderapp.databinding.FragmentUserInfoBottomBinding
import com.hawksappstudio.dostagiderapp.viewmodel.DetailViewModel
import java.util.jar.Manifest


class UserInfoBottomFragment : BottomSheetDialogFragment() {

    lateinit var binding : FragmentUserInfoBottomBinding
    private val detailViewModel : DetailViewModel by viewModels({requireParentFragment()})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_info_bottom,container,false)
        detailViewModel.detailLiveData.observe(viewLifecycleOwner,{
            binding.userInfo = it.userInfo
        })

        binding.callButton.setOnClickListener {
            var telNo = binding.cepNoText.text.toString()
            if (telNo.trim().isEmpty()){
                Toast.makeText(requireContext(),"Tel No bulunamadı",Toast.LENGTH_LONG).show()
            }else{
                val callNo = "tel:$telNo"
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(callNo)))
            }
        }

        return binding.root
    }




}