package io.jetpack.ysan.gankio.mvp.model

import io.jetpack.ysan.gankio.mvp.model.entity.BaseEntity
import io.jetpack.ysan.gankio.mvp.model.entity.Data
import io.jetpack.ysan.gankio.net.RetrofitManager
import io.jetpack.ysan.gankio.rx.scheduler.SchedulerUtils
import io.reactivex.Maybe


/**
 * Created by YSAN on 2019-05-09
 */
class RankModel {

    /**
     * 获取分类数据
     */
    fun requestCategoryData(category: String, page: Int): Maybe<BaseEntity<Data>> {
        return RetrofitManager.service.getCategoryData(category, page).compose(SchedulerUtils.ioToMain())
    }

}