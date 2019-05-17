package io.jetpack.ysan.gankio.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.mvp.model.entity.ReadSubCategoriesEntity
import io.jetpack.ysan.gankio.ui.activity.ReadActivity
import io.jetpack.ysan.gankio.widget.recyclerview.ViewHolder
import io.jetpack.ysan.gankio.widget.recyclerview.adapter.CommonAdapter


/**
 * Created by YSAN on 2019-05-16
 */
class ReadPageAdapter(context: Context, dataList: ArrayList<ReadSubCategoriesEntity>, layoutId: Int): CommonAdapter<ReadSubCategoriesEntity>(context, dataList, layoutId) {

    fun addData(dataList: ArrayList<ReadSubCategoriesEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: ReadSubCategoriesEntity, position: Int) {
        with(holder) {
            Glide.with(itemView.context)
                .load(data.icon)
                .apply(RequestOptions.centerCropTransform())
                .into(getView(R.id.category_image))
            getView<TextView>(R.id.category_name).text = data.title

            setOnItemClickListener(listener = View.OnClickListener {
                toReadDetailActivity(mContext as Activity, data)
            })
        }
    }

    private fun toReadDetailActivity(activity: Activity, data: ReadSubCategoriesEntity) {
        val intent = Intent(activity, ReadActivity::class.java)
        intent.putExtra("CID", data.id)
        intent.putExtra("IMAGE_URL", data.icon)
        intent.putExtra("CATEGORY_NAME", data.title)
        activity.startActivity(intent)
    }
}