package io.jetpack.ysan.gankio.mvp.model.entity

import com.google.gson.annotations.SerializedName


/**
 * Created by YSAN on 2019-05-13
 */
data class ReadEntity(
    @SerializedName("created_at") val createAt: String,
    @SerializedName("published_at") val publishedAt: String,
    val content: String,
    val cover: String,
    val raw: String,
    val readSite: ReadSiteEntity,
    val title: String,
    val uid: String,
    val url: String
)