package io.jetpack.ysan.gankio.mvp.presenter

import io.jetpack.ysan.gankio.base.BasePresenter
import io.jetpack.ysan.gankio.mvp.contract.CategoryContract
import io.jetpack.ysan.gankio.mvp.model.CategoryModel


/**
 * Created by YSAN on 2019-05-09
 */
class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    private val categoryModel: CategoryModel by lazy {
        CategoryModel()
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

    }
}