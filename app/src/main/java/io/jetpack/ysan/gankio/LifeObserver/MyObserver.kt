package io.jetpack.ysan.gankio.LifeObserver

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


/**
 * Created by YSAN on 2019/3/30
 */
class MyObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun takeOnCreate() {
        Log.i("ysan", "MyObserver onCreate ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun takeOnResume() {
        Log.i("ysan", "MyObserver onResume ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun takeOnPause() {
        Log.i("ysan", "MyObserver onPause ")
    }
}