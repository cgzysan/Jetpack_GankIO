package io.jetpack.ysan.gankio.ui.fragment

import android.os.Bundle
import io.jetpack.ysan.gankio.base.BaseFragment


/**
 * Created by YSAN on 2019-04-30
 */
class MoreFragment : BaseFragment() {

    companion object {
        fun getInstance() : MoreFragment {
            var fragment = MoreFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun lazyLoad() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}