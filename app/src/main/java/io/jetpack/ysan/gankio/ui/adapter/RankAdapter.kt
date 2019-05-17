package io.jetpack.ysan.gankio.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.mvp.model.entity.Data
import io.jetpack.ysan.gankio.ui.activity.WebViewActivity
import io.jetpack.ysan.gankio.utils.Constants
import io.jetpack.ysan.gankio.utils.DateUtils
import io.jetpack.ysan.gankio.widget.recyclerview.ViewHolder
import io.jetpack.ysan.gankio.widget.recyclerview.adapter.CommonAdapter


/**
 * Created by YSAN on 2019-05-10
 */
class RankAdapter(context: Context, dataList: ArrayList<Data>, layoutId: Int) : CommonAdapter<Data>(context, dataList, layoutId) {

    fun addData(dataList: ArrayList<Data>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: Data, position: Int) {
        with(holder) {
            getView<TextView>(R.id.item_title).text = data.desc.trim()
            getView<TextView>(R.id.item_who).text = data.who.trim()
            getView<TextView>(R.id.pub_date).text = DateUtils.getRelativeTime(data.publishedAt)

            getView<ImageView>(R.id.item_image).run {
                if (data.images != null) {
                    if (data.images.isNotEmpty()) {
                        this.visibility = View.VISIBLE
                        Glide
                            .with(context)
                            .load(data.images[0])
                            .apply(RequestOptions.centerCropTransform())
                            .into(this)
                    }
                } else {
                    this.visibility = View.GONE
                }
            }
            setOnItemClickListener(listener = View.OnClickListener {
                toDetailWebActivity(mContext as Activity, data)
            })
        }
    }

    private fun toDetailWebActivity(activity : Activity, itemData: Data) {
        val intent = Intent(activity, WebViewActivity::class.java)
        intent.putExtra(Constants.URL, itemData.url)
        intent.putExtra(Constants.DESC, itemData.desc)
        mContext.startActivity(intent)
    }
}