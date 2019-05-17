package io.jetpack.ysan.gankio.mvp.contract

import io.jetpack.ysan.gankio.base.IBaseView
import io.jetpack.ysan.gankio.base.IPresenter
import io.jetpack.ysan.gankio.mvp.model.entity.ReadEntity


/**
 * Created by YSAN on 2019-05-16
 */
interface ReadDetailContract {

    interface View: IBaseView{
        /**
         * 错误信息
         */
        fun showError(msg: String, errorCode: Int)
        /**
         * 显示闲读数据
         */
        fun showReadData(results: ArrayList<ReadEntity>)
    }

    interface Presenter: IPresenter<View> {
        /**
         * 根据分类获取闲读数据
         */
        fun requestReadData(category: String, page: Int)
    }
}