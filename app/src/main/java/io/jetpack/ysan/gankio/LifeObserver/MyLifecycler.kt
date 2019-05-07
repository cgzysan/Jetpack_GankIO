package io.jetpack.ysan.gankio.LifeObserver

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry


/**
 * Created by YSAN on 2019/3/30
 */
class MyLifecycler : LifecycleOwner {

    private lateinit var lifecycleRegistry: LifecycleRegistry

    fun est() {
        lifecycleRegistry = LifecycleRegistry(this)
        val myObserver = MyObserver()
        lifecycle.addObserver(myObserver)
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
        Log.i("ysan", "MyLifecycler est")
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

}