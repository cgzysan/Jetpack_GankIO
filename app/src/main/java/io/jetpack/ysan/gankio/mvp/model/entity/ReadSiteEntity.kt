package io.jetpack.ysan.gankio.mvp.model.entity

import com.google.gson.annotations.SerializedName


/**
 * Created by YSAN on 2019-05-13
 */
class ReadSiteEntity(
    @SerializedName("cat_cn") val catCN: String,
    @SerializedName("cat_en") val catEN: String,
    @SerializedName("feed_id") val feedId: String,
    val desc: String,
    val icon: String,
    val id: String,
    val name: String,
    val subscribers: Int,
    val type: String,
    val url: String
)