package io.jetpack.ysan.gankio.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader


/**
 * Created by YSAN on 2019-05-06
 */
class GlideImageLoader : ImageLoader() {

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        Glide
            .with(context!!)
            .load(path)
            .into(imageView!!)
    }
}