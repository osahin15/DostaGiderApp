package com.hawksappstudio.dostagiderapp.view.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.hawksappstudio.dostagiderapp.R
import com.hawksappstudio.dostagiderapp.databinding.FragmentTextBinding
import com.hawksappstudio.dostagiderapp.viewmodel.DetailViewModel


class TextFragment : Fragment() {

    lateinit var binding : FragmentTextBinding
    private val detailViewModel : DetailViewModel by viewModels({requireParentFragment()})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_text,container,false)

        detailViewModel.detailLiveData.observe(viewLifecycleOwner,{
            it.let {
                if (it !=null){
                   fromHtmltoString(it.text)
                }
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    fun fromHtmltoString(text:String){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT).toString()
        } else {
            binding.text = Html.fromHtml(text).toString()
        }
    }
}