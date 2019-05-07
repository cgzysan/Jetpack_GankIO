package io.jetpack.ysan.gankio.mvp.presenter

import android.util.Log
import io.jetpack.ysan.gankio.base.BasePresenter
import io.jetpack.ysan.gankio.mvp.contract.LastNewContract
import io.jetpack.ysan.gankio.mvp.model.LastNewModel
import io.jetpack.ysan.gankio.mvp.model.entity.HistoryDayEntity
import io.jetpack.ysan.gankio.mvp.model.entity.LastNewEntity
import io.jetpack.ysan.gankio.net.exception.ExceptionHandle
import io.jetpack.ysan.gankio.rx.scheduler.SchedulerUtils
import io.reactivex.Observable
import io.reactivex.functions.BiFunction


/**
 * Created by YSAN on 2019-04-29
 */
class LastNewPresenter : BasePresenter<LastNewContract.View>(), LastNewContract.Presenter {

    private val lastNewModel: LastNewModel by lazy {
        LastNewModel()
    }

    /**
     * 获取最新的数据
     */
    override fun requestLastNewData() {
        checkViewAttached()
        mRootView?.showLoading()
        val lastNewDisposable = lastNewModel.requestLastNewData()
        val dayDisposable = lastNewModel.requestDayHistory()
        val zipRequest = Observable.zip(lastNewDisposable, dayDisposable, BiFunction<LastNewEntity, HistoryDayEntity, LastNewEntity>{
            lastNewEntity, HistoryDayEntity ->
            lastNewEntity.dateStr = HistoryDayEntity.results[0]
            lastNewEntity
        })
            .compose(SchedulerUtils.ioToMain())
            .subscribe({ lastNewEntity ->
                mRootView?.apply {
                    dismissLoading()
                    Log.i("ysan", "获取最新数据成功:: ${lastNewEntity.results.webList.size}")
                    lastNewEntity.let {
                        it.count = it.results.picList.size
                        it.content = ArrayList()
                        it.content.addAll(it.results.picList)
                        it.content.add(it.dateStr)
                        it.content.addAll(it.results.androidList)
                        it.content.addAll(it.results.appList)
                        it.content.addAll(it.results.iosList)
                        it.content.addAll(it.results.restVideo)
                        it.content.addAll(it.results.webList)
                        it.content.addAll(it.results.expandList)
                        it.content.addAll(it.results.commendList)
                    }
                    showLastNewData(lastNewEntity)
                }
            }, { t ->
                mRootView?.apply {
                    dismissLoading()
                    Log.i("ysan", "获取最新数据出错:: $t")
                    showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                }
            })
        addSubscription(zipRequest)
    }
}