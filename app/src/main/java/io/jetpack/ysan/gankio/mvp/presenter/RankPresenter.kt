package io.jetpack.ysan.gankio.mvp.presenter

import io.jetpack.ysan.gankio.base.BasePresenter
import io.jetpack.ysan.gankio.mvp.contract.RankContract
import io.jetpack.ysan.gankio.mvp.model.RankModel


/**
 * Created by YSAN on 2019-05-09
 */
class RankPresenter : BasePresenter<RankContract.View>(), RankContract.Presenter {

    private val categoryModel: RankModel by lazy {
        RankModel()
    }

    override fun requestCategoryList(category: String, page: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = categoryModel.requestCategoryData(category, page)
            .subscribe {
                mRootView?.apply {
                    showCateGoryData(it.results)
                }
            }

        addSubscription(disposable)
    }

    override fun requestMoreData(category: String, page: Int) {
        mRootView?.showLoading()
        val disposable = categoryModel.requestCategoryData(category, page)
            .subscribe {
                mRootView?.apply {
                    showMoreData(it.results)
                }
            }

        addSubscription(disposable)
    }
}