package io.jetpack.ysan.gankio.mvp.presenter

import io.jetpack.ysan.gankio.base.BasePresenter
import io.jetpack.ysan.gankio.mvp.contract.ReadDetailContract
import io.jetpack.ysan.gankio.mvp.model.ReadDetailModel


/**
 * Created by YSAN on 2019-05-16
 */
class ReadDetailPresenter : BasePresenter<ReadDetailContract.View>(), ReadDetailContract.Presenter {

    private val mModel by lazy {
        ReadDetailModel()
    }

    override fun requestReadData(category: String, page: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = mModel.requestReadData(category, page)
            .subscribe {
                mRootView?.apply {
                    showReadData(it.results)
                }
            }
        addSubscription(disposable)
    }
}