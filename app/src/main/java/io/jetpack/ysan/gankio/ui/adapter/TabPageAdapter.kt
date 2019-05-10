package io.jetpack.ysan.gankio.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


/**
 * Created by YSAN on 2019-05-10
 */
class TabPageAdapter(fm: FragmentManager, var fragmentList: ArrayList<Fragment>, var titleList: ArrayList<String>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? = titleList[position]
}