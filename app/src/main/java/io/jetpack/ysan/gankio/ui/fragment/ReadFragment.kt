package io.jetpack.ysan.gankio.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.base.BaseFragment
import io.jetpack.ysan.gankio.mvp.contract.ReadContract
import io.jetpack.ysan.gankio.mvp.model.entity.ReadCategoriesEntity
import io.jetpack.ysan.gankio.mvp.presenter.ReadPresenter
import io.jetpack.ysan.gankio.net.exception.ErrorStatus
import io.jetpack.ysan.gankio.showToast
import io.jetpack.ysan.gankio.ui.adapter.TabPageAdapter
import io.jetpack.ysan.gankio.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_read.*


/**
 * Created by YSAN on 2019-04-30
 */
class ReadFragment : BaseFragment(), ReadContract.View {

    private val mPresenter by lazy {
        ReadPresenter()
    }

    private val mFragmentList = ArrayList<Fragment>()
    private val mTabTitleList = ArrayList<String>()


    companion object {
        fun getInstance() : ReadFragment {
            var fragment = ReadFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_read

    init {
        mPresenter.attachView(this)
    }

    override fun initView() {
        //状态栏透明和间距处理
        activity?.let {
            StatusBarUtil.darkMode(it)
            StatusBarUtil.setPaddingSmart(it, toolbar)
        }
        toolbar.setBackgroundColor(resources.getColor(R.color.color_title_bg))
        iv_search.setBackgroundResource(R.drawable.icon_search_black)
        tv_header_title.text = "闲读列表"

        mLayoutStatusView = read_multipleStatusView
    }

    override fun lazyLoad() {
        mPresenter.requestCategories()
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun setTabData(results: ArrayList<ReadCategoriesEntity>) {
        mLayoutStatusView?.showContent()

        results.mapTo(mFragmentList) { ReadPageFragment.getInstance(it) }
        results.mapTo(mTabTitleList) { it.name }
        mViewPager.adapter = TabPageAdapter(childFragmentManager, mFragmentList, mTabTitleList)
        mTabLayout.setupWithViewPager(mViewPager)
    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun dismissLoading() {

    }
}