package io.jetpack.ysan.gankio.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.ui.adapter.TabPageAdapter
import io.jetpack.ysan.gankio.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_category.*


/**
 * Created by YSAN on 2019-04-30
 */
class CategoryFragment : Fragment() {

    private val mTabTitleList = arrayListOf("Android", "iOS", "休息视频", "拓展资源", "前端")

    private val mFragmentList = ArrayList<Fragment>()

    companion object {
        fun getInstance(): CategoryFragment {
            val fragment = CategoryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }


    fun initView() {
        //状态栏透明和间距处理
        activity?.let {
            StatusBarUtil.darkMode(it)
            StatusBarUtil.setPaddingSmart(it, toolbar)
        }
        toolbar.setBackgroundColor(resources.getColor(R.color.color_title_bg))
        iv_search.setBackgroundResource(R.drawable.icon_search_black)
        tv_header_title.text = "分类列表"
        mTabTitleList.mapTo(mFragmentList) { RankFragment.getInstance(it) }
        mViewPager.adapter = TabPageAdapter(childFragmentManager, mFragmentList, mTabTitleList)
        mTabLayout.setupWithViewPager(mViewPager)
    }

}