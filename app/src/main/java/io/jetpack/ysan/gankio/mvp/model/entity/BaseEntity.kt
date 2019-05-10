package io.jetpack.ysan.gankio.mvp.model.entity


/**
 * Created by YSAN on 2019-05-09
 */
data class BaseEntity<T>(val results : ArrayList<T>, val error: Boolean)