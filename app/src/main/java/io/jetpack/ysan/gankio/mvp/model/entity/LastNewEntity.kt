package io.jetpack.ysan.gankio.mvp.model.entity

import com.google.gson.annotations.SerializedName


/**
 * Created by YSAN on 2019-04-27
 */
data class LastNewEntity(
    var dateStr: String,
    var count: Int,
    var content: ArrayList<Any>,
    val category: ArrayList<String>,
    val error: Boolean,
    val results: Result
) {
    data class Result(
        @SerializedName("Android") val androidList: ArrayList<Data>,
        @SerializedName("App") val appList: ArrayList<Data>,
        @SerializedName("iOS") val iosList: ArrayList<Data>,
        @SerializedName("休息视频") val restVideo: ArrayList<Data>,
        @SerializedName("前端") val webList: ArrayList<Data>,
        @SerializedName("拓展资源") val expandList: ArrayList<Data>,
        @SerializedName("瞎推荐") val commendList: ArrayList<Data>,
        @SerializedName("福利") val picList: ArrayList<Data>
    )
}