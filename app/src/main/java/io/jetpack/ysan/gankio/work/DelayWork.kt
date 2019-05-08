package io.jetpack.ysan.gankio.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


/**
 * Created by YSAN on 2019/3/28
 */
class DelayWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        Thread.sleep(1000)
        return Result.success()
    }
}