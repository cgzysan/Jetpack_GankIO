package io.jetpack.ysan.gankio.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.base.BaseFragment
import io.jetpack.ysan.gankio.mvp.contract.RankContract
import io.jetpack.ysan.gankio.mvp.model.entity.Data
import io.jetpack.ysan.gankio.mvp.presenter.RankPresenter
import io.jetpack.ysan.gankio.net.exception.ErrorStatus
import io.jetpack.ysan.gankio.net.exception.ExceptionHandle.Companion.errorMsg
import io.jetpack.ysan.gankio.showToast
import io.jetpack.ysan.gankio.ui.adapter.RankAdapter
import kotlinx.android.synthetic.main.fragment_rank.*


/**
 * Created by YSAN on 2019-05-10
 */
class RankFragment : BaseFragment(), RankContract.View {

    private val mPresenter by lazy {
        RankPresenter()
    }

    private var mCurrentPage = 0
    private var itemList = ArrayList<Data>()

    private val mAdapter by lazy { activity?.let { RankAdapter(it, itemList, R.layout.item_content) } }

    private var category: String? = null

    private var loadingMore = false

    companion object {
        fun getInstance(category: String): RankFragment {
            val fragment = RankFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.category = category
            return fragment
        }
    }

    init {
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_rank

    override fun initView() {
        mLayoutStatusView = rank_multipleStatusView
        rank_recyclerView.layoutManager = LinearLayoutManager(activity)
        rank_recyclerView.adapter = mAdapter

        rank_recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //当前屏幕所看到的子项个数
                    val childCount = rank_recyclerView.childCount
                    val itemCount = rank_recyclerView.layoutManager?.itemCount
                    //屏幕中第一个可见子项的position
                    val firstVisibleItem = (rank_recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount) {
                        if (!loadingMore) {
                            if (!category.isNullOrEmpty()) {
                                loadingMore = true
                                mPresenter.requestCategoryList(category!!, ++mCurrentPage)
                            }
                        }
                    }
                    Logger.i("chidCount = $childCount ;;; itemCount = $itemCount ;;; firstItem = $firstVisibleItem" )
                }
            }
        })
    }

    override fun lazyLoad() {
        if (!category.isNullOrEmpty()) {
            mCurrentPage = 1
            mPresenter.requestCategoryList(category!!, mCurrentPage)
        }
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun showCateGoryData(categoryDatas: ArrayList<Data>) {
        mLayoutStatusView?.showContent()
        mAdapter?.addData(categoryDatas)
    }

    override fun showMoreData(moreDatas: ArrayList<Data>) {
        loadingMore = false
        mAdapter?.addData(moreDatas)
    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun dismissLoading() {

    }
}