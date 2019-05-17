package io.jetpack.ysan.gankio.utils

import org.jsoup.Jsoup


/**
 * Created by YSAN on 2019-05-17
 */
object StringUtils {

    fun stripHtml(html: String): String {
        return Jsoup.parse(html).text()
    }
}