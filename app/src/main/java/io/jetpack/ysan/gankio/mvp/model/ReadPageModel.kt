package io.jetpack.ysan.gankio.mvp.model

import io.jetpack.ysan.gankio.mvp.model.entity.BaseEntity
import io.jetpack.ysan.gankio.mvp.model.entity.ReadSubCategoriesEntity
import io.jetpack.ysan.gankio.net.RetrofitManager
import io.jetpack.ysan.gankio.rx.scheduler.SchedulerUtils
import io.reactivex.Maybe


/**
 * Created by YSAN on 2019-05-16
 */
class ReadPageModel {

    /**
     * 获取闲读子分类
     */
    fun requestReadSubCategories(category: String) : Maybe<BaseEntity<ReadSubCategoriesEntity>> {
        return RetrofitManager.service.getReadSubCategories(category).compose(SchedulerUtils.ioToMain())
    }
}