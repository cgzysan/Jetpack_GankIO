package io.jetpack.ysan.gankio.mvp.contract

import io.jetpack.ysan.gankio.base.IBaseView
import io.jetpack.ysan.gankio.base.IPresenter
import io.jetpack.ysan.gankio.mvp.model.entity.ReadSubCategoriesEntity


/**
 * Created by YSAN on 2019-05-16
 */
interface ReadPageContract {

    interface View: IBaseView {

        /**
         * 错误信息
         */
        fun showError(msg: String, errorCode: Int)

        /**
         * 显示子分类
         */
        fun showSubCategories(results: ArrayList<ReadSubCategoriesEntity>)
    }

    interface Presenter: IPresenter<View> {

        /**
         * 获取闲读子分类
         */
        fun requestSubCategories(category: String)
    }

}