package io.jetpack.ysan.gankio.ui.activity

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.classic.common.MultipleStatusView
import io.jetpack.ysan.gankio.MyApplication.Companion.context
import io.jetpack.ysan.gankio.R
import io.jetpack.ysan.gankio.mvp.model.entity.Data
import io.jetpack.ysan.gankio.showToast
import io.jetpack.ysan.gankio.utils.Constants
import io.jetpack.ysan.gankio.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_web.*


/**
 * Created by YSAN on 2019-05-10
 */
class WebViewActivity : AppCompatActivity() {

    private lateinit var mLayoutStatusView: MultipleStatusView
    private lateinit var itemData: Data

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_web)

        itemData = intent.getSerializableExtra(Constants.ITEM_DATA) as Data

        toolbar.setNavigationIcon(R.drawable.icon_back)
        toolbar.inflateMenu(R.menu.web_toolbar)
        toolbar.title = itemData.desc
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        toolbar.setOnMenuItemClickListener { item ->
            when (item?.itemId) {
                R.id.web_share ->
                    showToast("分享")
                R.id.web_refresh ->
                    web_view.reload()
                R.id.web_copy_link ->
                    copyTextToClipboard()
                R.id.web_open_in_browser -> {
                    var url = itemData.url
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "http://$url"
                    }
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
            }
            true
        }

        mLayoutStatusView = web_multipleStatusView
        //状态栏透明和间距处理
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)

        web_view.let {
            it.settings.javaScriptEnabled = true
            it.webViewClient = object : WebViewClient() {
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    view?.loadUrl(request?.url.toString())
                    return false
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    mLayoutStatusView.showLoading()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    mLayoutStatusView.showContent()
                }
            }
            it.loadUrl(itemData.url)
        }
    }

    private fun copyTextToClipboard() {
        val clipboardManager: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager.primaryClip = ClipData.newPlainText("link", itemData.url)
        showToast("已复制到剪切板")
    }
}