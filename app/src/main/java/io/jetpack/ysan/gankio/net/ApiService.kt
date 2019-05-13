package io.jetpack.ysan.gankio.net

import io.jetpack.ysan.gankio.mvp.model.entity.*
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

    /**
     * 获取闲读分类
     */
    @GET("xiandu/categories")
    fun getReadCategories(): Maybe<BaseEntity<ReadCategoriesEntity>>

    /**
     * 获取闲读的子分类
     */
    @GET("xiandu/category/{en_name}")
    fun getReadSubCategories(@Path("en_name") enName: String): Maybe<BaseEntity<ReadSubCategoriesEntity>>

    /**
     * 获取闲读数据
     */
    @GET("xiandu/data/id/{cid}/count/" + 20 + "/page/{page}")
    fun getReadData(@Path("cid") categoryId: String, @Path("page") page: Int): Maybe<BaseEntity<ReadEntity>>

}