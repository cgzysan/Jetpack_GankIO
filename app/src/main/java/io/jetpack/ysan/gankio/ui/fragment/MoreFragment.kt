package io.jetpack.ysan.gankio.ui.fragment

import android.os.Bundle
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.base.BaseFragment


/**
 * Created by YSAN on 2019-04-30
 */
class MoreFragment : BaseFragment() {

    companion object {
        fun getInstance() : MoreFragment {
            val fragment = MoreFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_more

    override fun initView() {

    }

    override fun lazyLoad() {

    }
}