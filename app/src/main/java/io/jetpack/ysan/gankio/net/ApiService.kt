package io.jetpack.ysan.gankio.net

import io.jetpack.ysan.gankio.mvp.model.entity.BaseEntity
import io.jetpack.ysan.gankio.mvp.model.entity.Data
import io.jetpack.ysan.gankio.mvp.model.entity.HistoryDayEntity
import io.jetpack.ysan.gankio.mvp.model.entity.LastNewEntity
import io.reactivex.Maybe
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


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

    /**
     * 获取分类数据
     */
    @GET("data/{category}/" + 20 + "/{page}")
    fun getCategoryData(
        @Path("category") category: String, @Path("page") page: Int
    ): Maybe<BaseEntity<Data>>
}