package io.jetpack.ysan.gankio.mvp.contract

import io.jetpack.ysan.gankio.base.IBaseView
import io.jetpack.ysan.gankio.base.IPresenter


/**
 * Created by YSAN on 2019-05-13
 */
interface ReadContract {

    interface View: IBaseView {

        /**
         * 错误信息
         */
        fun showError(msg: String, errorCode: Int)
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