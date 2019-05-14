package io.jetpack.ysan.gankio.mvp.contract

import io.jetpack.ysan.gankio.base.IBaseView
import io.jetpack.ysan.gankio.base.IPresenter
import io.jetpack.ysan.gankio.mvp.model.entity.ReadCategoriesEntity
import io.jetpack.ysan.gankio.mvp.model.entity.ReadEntity
import io.jetpack.ysan.gankio.mvp.model.entity.ReadSubCategoriesEntity


/**
 * Created by YSAN on 2019-05-13
 */
interface ReadContract {

    interface View: IBaseView {

        /**
         * 错误信息
         */
        fun showError(msg: String, errorCode: Int)

        /**
         *  设置 tab 数据
         */
        fun setTabData(results: ArrayList<ReadCategoriesEntity>)

        /**
         * 显示子分类
         */
        fun showSubCategories(results: ArrayList<ReadSubCategoriesEntity>)

        /**
         * 显示闲读数据
         */
        fun showReadData(results: ArrayList<ReadEntity>)
    }

    interface Presenter: IPresenter<View> {

        /**
         * 获取闲读分类
         */
        fun requestCategories()

        /**
         * 获取闲读子分类
         */
        fun requestSubCategories(category: String)

        /**
         * 根据分类获取闲读数据
         */
        fun requestReadData(category: String, page: Int)
    }
}