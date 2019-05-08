package io.jetpack.ysan.gankio.mvp.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by YSAN on 2019-04-28
 */
data class Data(
    @SerializedName("_id") val id: String,
    val createAt: String,
    val desc: String,
    val publishedAt: String,
    val source: String,
    val type: String,
    val url: String,
    val used: Boolean,
    val who: String,
    val images: List<String>
) : Serializable