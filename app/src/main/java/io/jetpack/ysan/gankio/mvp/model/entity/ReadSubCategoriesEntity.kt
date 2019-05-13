package io.jetpack.ysan.gankio.mvp.model.entity

import com.google.gson.annotations.SerializedName


/**
 * Created by YSAN on 2019-05-13
 */
data class ReadSubCategoriesEntity(
    @SerializedName("created_at") val createdId: String,
    val icon: String,
    val id: String,
    val title: String
)