package io.jetpack.ysan.gankio.ui.fragment

import android.os.Bundle
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.base.BaseFragment
import io.jetpack.ysan.gankio.mvp.contract.CategoryContract
import io.jetpack.ysan.gankio.mvp.model.entity.Data
import io.jetpack.ysan.gankio.mvp.presenter.CategoryPresenter
import io.jetpack.ysan.gankio.net.exception.ErrorStatus
import io.jetpack.ysan.gankio.showToast
import kotlinx.android.synthetic.main.fragment_lastnew.*


/**
 * Created by YSAN on 2019-04-30
 */
class CategoryFragment : BaseFragment(), CategoryContract.View {

    private var isRefresh = false

    private val mPresenter by lazy {
        CategoryPresenter()
    }

    companion object {
        fun getInstance() : CategoryFragment {
            var fragment = CategoryFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_category
    }

    override fun initView() {

    }

    override fun lazyLoad() {

    }

    override fun showCateGoryData(categoryDatas: ArrayList<Data>) {

    }

    override fun showMoreData(moreDatas: ArrayList<Data>) {

    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun showLoading() {
        if (!isRefresh) {
            isRefresh = false
            mLayoutStatusView?.showLoading()
        }
    }

    override fun dismissLoading() {
        mRefreshLayout?.finishRefresh()
    }
}