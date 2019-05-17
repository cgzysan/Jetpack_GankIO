package io.jetpack.ysan.gankio.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.mvp.model.entity.ReadEntity
import io.jetpack.ysan.gankio.ui.activity.WebViewActivity
import io.jetpack.ysan.gankio.utils.Constants
import io.jetpack.ysan.gankio.utils.DateUtils
import io.jetpack.ysan.gankio.utils.StringUtils
import io.jetpack.ysan.gankio.widget.recyclerview.ViewHolder
import io.jetpack.ysan.gankio.widget.recyclerview.adapter.CommonAdapter
import java.util.regex.Pattern


/**
 * Created by YSAN on 2019-05-17
 */
class ReadAdapter(context: Context, dataList: ArrayList<ReadEntity>, layoutId: Int) : CommonAdapter<ReadEntity>(context, dataList, layoutId) {

    fun addData(dataList: ArrayList<ReadEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: ReadEntity, position: Int) {
        with(holder) {
            getView<TextView>(R.id.title).text = data.title
            val content: String = if (data.content.isNotEmpty()) {
                StringUtils.stripHtml(data.content)
            } else {
                val matcher = Pattern.compile(".+summary.+content.+'(.+)'.+direction.+").matcher(data.raw)
                val html = if (matcher.find()) matcher.group(1) else ""
                if (html != null) StringUtils.stripHtml(html) else ""
            }
            getView<TextView>(R.id.content).text = content
            getView<TextView>(R.id.pub_date).text = DateUtils.getRelativeTime(data.publishedAt)
            setOnItemClickListener(listener = View.OnClickListener {
                toWebViewActivity(mContext as Activity, data)
            })
        }
    }

    private fun toWebViewActivity(activity: Activity, data: ReadEntity) {
        val intent = Intent(activity, WebViewActivity::class.java)
        intent.putExtra(Constants.URL, data.url)
        intent.putExtra(Constants.DESC, data.title)
        mContext.startActivity(intent)
    }
}