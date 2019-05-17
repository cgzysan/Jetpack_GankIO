package io.jetpack.ysan.gankio.ui.activity

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.orhanobut.logger.Logger
import com.scwang.smartrefresh.header.MaterialHeader
import io.jetpack.ysan.gankio.MyApplication.Companion.context
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.base.BaseActivity
import io.jetpack.ysan.gankio.mvp.contract.ReadDetailContract
import io.jetpack.ysan.gankio.mvp.model.entity.ReadEntity
import io.jetpack.ysan.gankio.mvp.presenter.ReadDetailPresenter
import io.jetpack.ysan.gankio.net.exception.ErrorStatus
import io.jetpack.ysan.gankio.showToast
import io.jetpack.ysan.gankio.ui.adapter.ReadAdapter
import io.jetpack.ysan.gankio.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_read.*


/**
 * Created by YSAN on 2019-05-16
 */
class ReadActivity : BaseActivity(), ReadDetailContract.View {

    private val mPresenter by lazy {
        ReadDetailPresenter()
    }

    private var isRefresh = false

    private var mMaterialHeader: MaterialHeader? = null

    private var mReadAdapter: ReadAdapter? = null

    private val linearLayoutManager by lazy {
        LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private var cid: String? = null
    private var icon: String? = null
    private var title: String? = null
    private var mCurrentPage = 0

    private var loadingMore = false

    override fun getLayoutId(): Int = R.layout.activity_read

    override fun initView() {
        toolbar.setNavigationIcon(R.drawable.icon_back)
        toolbar.title = title
        Glide.with(this)
            .load(icon)
            .apply(RequestOptions.centerCropTransform())
            .into(category_image)

        mRefreshLayout.setEnableHeaderTranslationContent(true)
        mRefreshLayout.setOnRefreshListener {
            isRefresh = true
            start()
        }
        mMaterialHeader = mRefreshLayout.refreshHeader as MaterialHeader?
        mMaterialHeader?.setShowBezierWave(true)
        // 设置主题颜色
        mRefreshLayout.setPrimaryColorsId(R.color.colorAccent, R.color.colorPrimary)
        mLayoutStatusView = read_multipleStatusView

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //当前屏幕所看到的子项个数
                    val childCount = recycler_view.childCount
                    val itemCount = recycler_view.layoutManager?.itemCount
                    //屏幕中第一个可见子项的position
                    val firstVisibleItem = (recycler_view.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount) {
                        if (!loadingMore) {
                            if (!cid.isNullOrEmpty()) {
                                loadingMore = true
                                mPresenter.requestReadData(cid!!, ++mCurrentPage)
                            }
                        }
                    }
                    Logger.i("chidCount = $childCount ;;; itemCount = $itemCount ;;; firstItem = $firstVisibleItem" )
                }
            }
        })

        //状态栏透明和间距处理
        StatusBarUtil.lightMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)
    }

    override fun initData() {
        intent.let {
            cid = it.getStringExtra("CID")
            icon = it.getStringExtra("IMAGE_URL")
            title = it.getStringExtra("CATEGORY_NAME")
        }
    }

    override fun start() {
        if (!cid.isNullOrEmpty()) {
            mCurrentPage = 1
            mPresenter.requestReadData(cid!!, mCurrentPage)
        }
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun showReadData(results: ArrayList<ReadEntity>) {
        mLayoutStatusView?.showContent()

        mReadAdapter = ReadAdapter(this, results, R.layout.item_read)

        recycler_view.adapter = mReadAdapter
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
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

    override fun showMoreData(results: ArrayList<ReadEntity>) {
        loadingMore = false
        mReadAdapter?.addData(results)
    }
}