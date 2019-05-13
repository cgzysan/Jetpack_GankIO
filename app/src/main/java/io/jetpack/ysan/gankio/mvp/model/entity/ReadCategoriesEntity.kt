package io.jetpack.ysan.gankio.mvp.model.entity

import com.google.gson.annotations.SerializedName


/**
 * Created by YSAN on 2019-05-13
 */
data class ReadCategoriesEntity(
    @SerializedName("_id") val id: String,
    @SerializedName("en_name") val enName: String,
    val name: String,
    val rank: Int
)