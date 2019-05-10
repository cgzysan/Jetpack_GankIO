package io.jetpack.ysan.gankio.mvp.contract

import io.jetpack.ysan.gankio.base.IBaseView
import io.jetpack.ysan.gankio.base.IPresenter
import io.jetpack.ysan.gankio.mvp.model.entity.Data


/**
 * Created by YSAN on 2019-05-09
 */
interface CategoryContract {

    interface View : IBaseView {
        /**
         * 错误信息
         */
        fun showError(msg: String, errorCode: Int)

        /**
         * 加载分类数据
         */
        fun showCateGoryData(categoryDatas: ArrayList<Data>)

        /**
         * 加载更多数据
         */
        fun showMoreData(moreDatas: ArrayList<Data>)
    }

    interface Presenter : IPresenter<View> {
        /**
         * 获取分类数据
         */
        fun requestCategoryList(category: String, page: Int)

        /**
         * 获取更多数据
         */
        fun requestMoreData(category: String, page: Int)
    }
}