package io.jetpack.ysan.gankio.mvp.model

import io.jetpack.ysan.gankio.mvp.model.entity.BaseEntity
import io.jetpack.ysan.gankio.mvp.model.entity.ReadEntity
import io.jetpack.ysan.gankio.net.RetrofitManager
import io.jetpack.ysan.gankio.rx.scheduler.SchedulerUtils
import io.reactivex.Maybe


/**
 * Created by YSAN on 2019-05-16
 */
class ReadDetailModel {


    /**
     * 获取闲读数据
     */
    fun requestReadData(category: String, page: Int) : Maybe<BaseEntity<ReadEntity>> {
        return RetrofitManager.service.getReadData(category, page).compose(SchedulerUtils.ioToMain())
    }
}