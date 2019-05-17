package io.jetpack.ysan.gankio.ui.activity

import com.scwang.smartrefresh.header.MaterialHeader
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.base.BaseActivity
import io.jetpack.ysan.gankio.mvp.contract.ReadDetailContract
import io.jetpack.ysan.gankio.mvp.model.entity.ReadEntity
import io.jetpack.ysan.gankio.mvp.presenter.ReadDetailPresenter
import io.jetpack.ysan.gankio.net.exception.ErrorStatus
import io.jetpack.ysan.gankio.showToast
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

    private var cid: String? = null
    private var icon: String? = null
    private var title: String? = null
    private var mCurrentPage = 0

    override fun getLayoutId(): Int = R.layout.activity_read

    override fun initView() {
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

    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}