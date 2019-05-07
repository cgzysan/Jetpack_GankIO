package io.jetpack.ysan.gankio.net

import io.jetpack.ysan.gankio.mvp.model.entity.HistoryDayEntity
import io.jetpack.ysan.gankio.mvp.model.entity.LastNewEntity
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * Created by YSAN on 2019-04-28
 */
interface ApiService {

    /**
     * 最新数据
     */
    @GET("today")
    fun getLastNewData(): Observable<LastNewEntity>

    /**
     * 获取历史日期
     */
    @GET("day/history")
    fun getHistoryDay(): Observable<HistoryDayEntity>
}