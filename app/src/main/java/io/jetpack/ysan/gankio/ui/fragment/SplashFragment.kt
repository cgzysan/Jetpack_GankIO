package io.jetpack.ysan.gankio.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.work.DelayWork


/**
 * Created by YSAN on 2019/3/28
 */
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val delayRequest = OneTimeWorkRequestBuilder<DelayWork>().build()
        WorkManager.getInstance().enqueue(delayRequest)
        WorkManager.getInstance().getWorkInfoByIdLiveData(delayRequest.id).observe(this, Observer {
            Log.i("ysan",  it?.state?.name)
            if (it?.state!!.isFinished) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
        })
    }
}