package io.jetpack.ysan.gankio.utils

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by YSAN on 2019-05-06
 */
object DateUtils {
    fun getRelativeTime(date: String) : String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'S'Z'", Locale.CHINESE)
        try {
            val dateTime = dateFormat.parse(date)
            return DateUtils.getRelativeTimeSpanString(dateTime.time, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS) as String
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return "N/A"
    }
}