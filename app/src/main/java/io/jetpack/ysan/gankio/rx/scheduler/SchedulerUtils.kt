package io.jetpack.ysan.gankio.rx.scheduler


/**
 * Created by YSAN on 2019-04-29
 */
object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}