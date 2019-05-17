package io.jetpack.ysan.gankio.mvp.presenter

import io.jetpack.ysan.gankio.base.BasePresenter
import io.jetpack.ysan.gankio.mvp.contract.ReadPageContract
import io.jetpack.ysan.gankio.mvp.model.ReadPageModel


/**
 * Created by YSAN on 2019-05-16
 */
class ReadPagePresenter : BasePresenter<ReadPageContract.View>(), ReadPageContract.Presenter {

    private val readPageModel: ReadPageModel by lazy {
        ReadPageModel()
    }

    override fun requestSubCategories(category: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = readPageModel.requestReadSubCategories(category)
            .subscribe{
                mRootView?.apply {
                    showSubCategories(it.results)
                }
            }
        addSubscription(disposable)
    }
}