package io.jetpack.ysan.gankio.mvp.contract

import io.jetpack.ysan.gankio.base.IBaseView
import io.jetpack.ysan.gankio.base.IPresenter
import io.jetpack.ysan.gankio.mvp.model.entity.LastNewEntity


/**
 * Created by YSAN on 2019-04-27
 */
interface LastNewContract {

    interface View : IBaseView {

        /**
         *
         */
        fun showLastNewData(lastNewEntity: LastNewEntity)

        /**
         * 错误信息
         */
        fun showError(msg: String, errorCode: Int)

    }

    interface Presenter : IPresenter<View> {

        /**
         *  获取最新数据
         */
        fun requestLastNewData()
    }
}