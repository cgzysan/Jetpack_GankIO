package io.jetpack.ysan.gankio.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.base.BaseFragment
import io.jetpack.ysan.gankio.mvp.contract.ReadPageContract
import io.jetpack.ysan.gankio.mvp.model.entity.ReadCategoriesEntity
import io.jetpack.ysan.gankio.mvp.model.entity.ReadSubCategoriesEntity
import io.jetpack.ysan.gankio.mvp.presenter.ReadPagePresenter
import io.jetpack.ysan.gankio.net.exception.ErrorStatus
import io.jetpack.ysan.gankio.net.exception.ExceptionHandle
import io.jetpack.ysan.gankio.showToast
import io.jetpack.ysan.gankio.ui.adapter.ReadPageAdapter
import kotlinx.android.synthetic.main.fragment_rank.*


/**
 * Created by YSAN on 2019-05-16
 */
class ReadPageFragment : BaseFragment(), ReadPageContract.View {

    private val mPresenter by lazy {
        ReadPagePresenter()
    }
    private var entity : ReadCategoriesEntity? = null
    private var itemList = ArrayList<ReadSubCategoriesEntity>()

    private val mAdapter by lazy { activity?.let { ReadPageAdapter(it, itemList, R.layout.item_read_page) } }

    companion object {
        fun getInstance(entity: ReadCategoriesEntity): ReadPageFragment {
            val fragment = ReadPageFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.entity = entity
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_rank

    init {
        mPresenter.attachView(this)
    }

    override fun initView() {
        mLayoutStatusView = rank_multipleStatusView
        rank_recyclerView.layoutManager = LinearLayoutManager(activity)
        rank_recyclerView.adapter = mAdapter
    }

    override fun lazyLoad() {
        entity?.let {
            if (it.enName.isNotEmpty()) {
                mPresenter.requestSubCategories(it.enName)
            }
        }
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(ExceptionHandle.errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun showSubCategories(results: ArrayList<ReadSubCategoriesEntity>) {
        mLayoutStatusView?.showContent()
        mAdapter?.addData(results)
    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}