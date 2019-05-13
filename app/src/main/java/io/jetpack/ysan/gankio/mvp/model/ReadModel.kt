package io.jetpack.ysan.gankio.mvp.model

import io.jetpack.ysan.gankio.mvp.model.entity.BaseEntity
import io.jetpack.ysan.gankio.mvp.model.entity.ReadCategoriesEntity
import io.jetpack.ysan.gankio.mvp.model.entity.ReadEntity
import io.jetpack.ysan.gankio.mvp.model.entity.ReadSubCategoriesEntity
import io.jetpack.ysan.gankio.net.RetrofitManager
import io.jetpack.ysan.gankio.rx.scheduler.SchedulerUtils
import io.reactivex.Maybe


/**
 * Created by YSAN on 2019-05-13
 */
class ReadModel {

    /**
     * 获取闲读分类
     */
    fun requestReadCategories() : Maybe<BaseEntity<ReadCategoriesEntity>> {
        return RetrofitManager.service.getReadCategories().compose(SchedulerUtils.ioToMain())
    }

    /**
     * 获取闲读子分类
     */
    fun requestReadSubCategories(category: String) : Maybe<BaseEntity<ReadSubCategoriesEntity>> {
        return RetrofitManager.service.getReadSubCategories(category).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 获取闲读数据
     */
    fun requestReadData(category: String, page: Int) : Maybe<BaseEntity<ReadEntity>> {
        return RetrofitManager.service.getReadData(category, page).compose(SchedulerUtils.ioToMain())
    }

}