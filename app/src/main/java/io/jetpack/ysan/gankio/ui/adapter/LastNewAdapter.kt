package io.jetpack.ysan.gankio.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.Banner
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.mvp.model.entity.Data
import io.jetpack.ysan.gankio.ui.activity.WebViewActivity
import io.jetpack.ysan.gankio.utils.Constants
import io.jetpack.ysan.gankio.utils.DateUtils
import io.jetpack.ysan.gankio.utils.GlideImageLoader
import io.jetpack.ysan.gankio.widget.recyclerview.ViewHolder
import io.jetpack.ysan.gankio.widget.recyclerview.adapter.CommonAdapter
import io.reactivex.Observable


/**
 * Created by YSAN on 2019-05-05
 */
class LastNewAdapter(context: Context, data: ArrayList<Any>) : CommonAdapter<Any>(context, data, -1) {

    var bannerItemSize = 0

    companion object {
        private const val ITEM_TYPE_BANNER = 1    // Banner 类型
        private const val ITEM_TYPE_HEADER = 2    // Header
        private const val ITEM_TYPE_CONTENT = 3   // Item
    }

    /**
     * 设置 banner 数量
     */
    fun setBannerSize(count: Int) {
        bannerItemSize = count
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 ->
                ITEM_TYPE_BANNER
            1 ->
                ITEM_TYPE_HEADER
            else ->
                ITEM_TYPE_CONTENT
        }
    }

    override fun getItemCount(): Int {
        return when {
            mData.size > bannerItemSize -> mData.size - bannerItemSize + 1
            mData.isEmpty() -> 0
            else -> 2
        }
    }

    override fun bindData(holder: ViewHolder, data: Any, position: Int) {
        when (getItemViewType(position)) {
            ITEM_TYPE_BANNER -> {
                val bannerItemData: ArrayList<Any> = mData.take(bannerItemSize).toCollection(ArrayList())
                val bannerImgList = ArrayList<String>()

                Observable.fromIterable(bannerItemData)
                    .subscribe {
                        it as Data
                        bannerImgList.add(it.url)
                    }

                with(holder) {
                    getView<Banner>(R.id.banner).run {
                        setDelayTime(3000)
                        setImageLoader(GlideImageLoader())
                        setImages(bannerImgList)
                        start()
                    }
                }
            }

            ITEM_TYPE_HEADER -> {
                holder.setText(R.id.tvHeader, mData[bannerItemSize] as String)
            }

            ITEM_TYPE_CONTENT -> {
                setContentItem(holder, mData[position + bannerItemSize - 1] as Data)
            }
        }
    }

    private fun setContentItem(holder: ViewHolder, data: Data) {
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
        intent.putExtra(Constants.ITEM_DATA, itemData)
        mContext.startActivity(intent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ITEM_TYPE_BANNER ->
                ViewHolder(inflaterView(R.layout.item_banner, parent))
            ITEM_TYPE_HEADER ->
                ViewHolder(inflaterView(R.layout.item_header, parent))
            else ->
                ViewHolder(inflaterView(R.layout.item_content, parent))
        }
    }

    private fun inflaterView(layoutId: Int, parent: ViewGroup): View {
        val view = mInflater?.inflate(layoutId, parent, false)
        return view ?: View(parent.context)
    }

}