package io.jetpack.ysan.gankio.base


/**
 * Created by YSAN on 2019-04-29
 */
interface IPresenter<in V : IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}