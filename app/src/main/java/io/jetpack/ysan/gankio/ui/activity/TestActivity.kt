package io.jetpack.ysan.gankio.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import io.jetpack.ysan.gankio.R


/**
 * Created by YSAN on 2019-05-09
 */
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.test_layout)
    }
}