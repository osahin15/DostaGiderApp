package com.hawksappstudio.dostagiderapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hawksappstudio.dostagiderapp.view.PropertiesFragment
import com.hawksappstudio.dostagiderapp.view.TextFragment

class FragmentTabAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> PropertiesFragment()
            else -> TextFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"İlan Bilgileri"
            else-> "Açıklama"
        }
    }
}