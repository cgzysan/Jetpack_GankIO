package io.jetpack.ysan.gankio.mvp.model

import io.jetpack.ysan.gankio.mvp.model.entity.BaseEntity
import io.jetpack.ysan.gankio.mvp.model.entity.ReadCategoriesEntity
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

}