package io.jetpack.ysan.gankio.mvp.presenter

import io.jetpack.ysan.gankio.base.BasePresenter
import io.jetpack.ysan.gankio.mvp.contract.ReadContract
import io.jetpack.ysan.gankio.mvp.model.ReadModel


/**
 * Created by YSAN on 2019-05-13
 */
class ReadPresenter : BasePresenter<ReadContract.View>(), ReadContract.Presenter {

    private val readModel: ReadModel by lazy {
        ReadModel()
    }

    override fun requestCategories() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = readModel.requestReadCategories()
            .subscribe{
                mRootView?.apply {
                    setTabData(it.results)
                }
            }
        addSubscription(disposable)
    }

}