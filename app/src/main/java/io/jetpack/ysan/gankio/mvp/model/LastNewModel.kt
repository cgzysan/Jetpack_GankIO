package io.jetpack.ysan.gankio.mvp.model

import io.jetpack.ysan.gankio.mvp.model.entity.HistoryDayEntity
import io.jetpack.ysan.gankio.mvp.model.entity.LastNewEntity
import io.jetpack.ysan.gankio.net.RetrofitManager
import io.jetpack.ysan.gankio.rx.scheduler.SchedulerUtils
import io.reactivex.Observable


/**
 * Created by YSAN on 2019-04-29
 */
class LastNewModel {

    /**
     * 获取最新数据
     */
    fun requestLastNewData(): Observable<LastNewEntity> {
        return RetrofitManager.service.getLastNewData().compose(SchedulerUtils.ioToMain())
    }

    /**
     * 获取发过干货的日期
     */
    fun requestDayHistory(): Observable<HistoryDayEntity> {
        return RetrofitManager.service.getHistoryDay().compose(SchedulerUtils.ioToMain())
    }
}