package io.jetpack.ysan.gankio.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.header.MaterialHeader
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.base.BaseFragment
import io.jetpack.ysan.gankio.mvp.contract.LastNewContract
import io.jetpack.ysan.gankio.mvp.model.entity.Data
import io.jetpack.ysan.gankio.mvp.model.entity.LastNewEntity
import io.jetpack.ysan.gankio.mvp.presenter.LastNewPresenter
import io.jetpack.ysan.gankio.net.exception.ErrorStatus
import io.jetpack.ysan.gankio.showToast
import io.jetpack.ysan.gankio.ui.adapter.LastNewAdapter
import io.jetpack.ysan.gankio.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_lastnew.*


/**
 * Created by YSAN on 2019-04-27
 */
class LastNewFragment : BaseFragment(), LastNewContract.View {

    private val mPresenter by lazy { LastNewPresenter() }

    private var isRefresh = false

    private var mMaterialHeader: MaterialHeader? = null

    private var mLastNewAdapter: LastNewAdapter? = null

    private val linearLayoutManager by lazy {
        LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    companion object {
        fun getInstance() : LastNewFragment {
            val fragment = LastNewFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    /**
     * 初始化 View
     */
    override fun initView() {
        Log.i("ysan", "lastFragment initView ")
        mPresenter.attachView(this)
        mRefreshLayout.setEnableHeaderTranslationContent(true)
        mRefreshLayout.setOnRefreshListener {
            isRefresh = true
            mPresenter.requestLastNewData()
        }
        mMaterialHeader = mRefreshLayout.refreshHeader as MaterialHeader?
        mMaterialHeader?.setShowBezierWave(true)
        // 设置主题颜色
        mRefreshLayout.setPrimaryColorsId(R.color.colorAccent, R.color.colorPrimary)

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                when (val currentVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()) {
                    0 -> {
                        toolbar.setBackgroundColor(getColor(R.color.color_transparent))
                        iv_search.setImageResource(R.drawable.icon_search_white)
                        tv_header_title.text = ""
                    }
                    1 -> {
                        toolbar.setBackgroundColor(getColor(R.color.color_title_bg))
                        iv_search.setBackgroundResource(R.drawable.icon_search_black)
                        mLastNewAdapter?.let {
                            tv_header_title.text = it.mData[it.bannerItemSize] as String
                        }
                    }
                    else -> {
                        toolbar.setBackgroundColor(getColor(R.color.color_title_bg))
                        iv_search.setImageResource(R.drawable.icon_search_black)
                        mLastNewAdapter?.let {
                            val d = it.mData[currentVisibleItemPosition + it.bannerItemSize - 1] as Data
                            tv_header_title.text = d.type
                        }
                    }
                }
            }
        })

        mLayoutStatusView = last_new_multipleStatusView

        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, toolbar) }
    }

    override fun lazyLoad() {
        mPresenter.requestLastNewData()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_lastnew
    }

    override fun showLastNewData(lastNewEntity: LastNewEntity) {
        mLayoutStatusView?.showContent()

        mLastNewAdapter = context?.let { LastNewAdapter(it, lastNewEntity.content) }
        mLastNewAdapter?.setBannerSie(lastNewEntity.count)

        mRecyclerView.adapter = mLastNewAdapter
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.itemAnimator = DefaultItemAnimator()
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

    fun getColor(colorId: Int): Int {
        return resources.getColor(colorId)
    }
}