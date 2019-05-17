package io.jetpack.ysan.gankio.ui.adapter

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.mvp.model.entity.Data
import io.jetpack.ysan.gankio.widget.recyclerview.ViewHolder
import io.jetpack.ysan.gankio.widget.recyclerview.adapter.CommonAdapter


/**
 * Created by YSAN on 2019-05-17
 */
class PicAdapter(context: Context, dataList: ArrayList<Data>, layoutId: Int) : CommonAdapter<Data>(context, dataList, layoutId) {

    fun addData(dataList: ArrayList<Data>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: Data, position: Int) {
        with(holder) {
            Glide
                .with(mContext)
                .load(data.url)
                .apply(RequestOptions.fitCenterTransform())
                .into(getView(R.id.picture))
        }
    }
}