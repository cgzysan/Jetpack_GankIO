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
import io.jetpack.ysan.gankio.net.exception.ExceptionHandle
import io.jetpack.ysan.gankio.showToast
import io.jetpack.ysan.gankio.ui.adapter.PicAdapter
import kotlinx.android.synthetic.main.fragment_pic.*
import kotlinx.android.synthetic.main.fragment_rank.*


/**
 * Created by YSAN on 2019-04-30
 */
class PicFragment : BaseFragment(), RankContract.View {

    private val mPresenter by lazy {
        RankPresenter()
    }

    private var mCurrentPage = 0
    private var itemList = ArrayList<Data>()

    private val mAdapter by lazy { activity?.let { PicAdapter(it, itemList, R.layout.item_picture) } }

    private var loadingMore = false

    private val category = "福利"

    companion object {
        fun getInstance(): PicFragment {
            val fragment = PicFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    init {
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_pic

    override fun initView() {
        mLayoutStatusView = pic_multipleStatusView
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mAdapter

        rank_recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //当前屏幕所看到的子项个数
                    val childCount = rank_recyclerView.childCount
                    val itemCount = rank_recyclerView.layoutManager?.itemCount
                    //屏幕中第一个可见子项的position
                    val firstVisibleItem =
                        (rank_recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount) {
                        if (!loadingMore) {
                            loadingMore = true
                            mPresenter.requestCategoryList(category, ++mCurrentPage)
                        }
                    }
                    Logger.i("chidCount = $childCount ;;; itemCount = $itemCount ;;; firstItem = $firstVisibleItem")
                }
            }
        })
    }

    override fun lazyLoad() {
        mCurrentPage = 1
        mPresenter.requestCategoryList(category, mCurrentPage)
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(ExceptionHandle.errorMsg)
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}