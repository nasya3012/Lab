package com.example.tau.lab.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.tau.lab.R
import android.webkit.WebView
import android.widget.Button
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.webkit.WebResourceRequest
import android.webkit.WebViewClient


class BrowserActivity : AppCompatActivity() {
    private var webView: WebView? = null
    private val IMG_GALLERY = 1

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        webView = findViewById(R.id.webView);

        findViewById<Button>(R.id.gallery_button).setOnClickListener {
            val pickIntent = Intent()
            pickIntent.type = "image/*"
            pickIntent.action = Intent.ACTION_GET_CONTENT
            // мы будем обрабатывать возвращенное значение в onActivityResult
            startActivityForResult(
                    Intent.createChooser(pickIntent, "Выберите картинку"),
                    IMG_GALLERY)
        }

        findViewById<Button>(R.id.web_button).setOnClickListener {
            webView?.loadUrl("https://povar.ru/recipes/dieticheskii_keks_v_mikrovolnovke-29720.html")
            // включаем поддержку JavaScript
            webView?.settings?.javaScriptEnabled = true
            webView?.webViewClient = MyWebViewClient()
        }

        findViewById<Button>(R.id.local_button).setOnClickListener {
            webView?.loadUrl("file:///mipmap/leopard.jpg")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == IMG_GALLERY) {
                val pickedUri = data.data
                val imagePath: String
                val imgData = arrayOf(MediaStore.Images.Media.DATA)
                val imgCursor = applicationContext.contentResolver
                        .query(pickedUri!!, imgData, null, null, null)
                if (imgCursor != null) {
                    val index = imgCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    imgCursor.moveToFirst()
                    imagePath = imgCursor!!.getString(index)
                    imgCursor.close()
                } else
                    imagePath = pickedUri.path
                webView?.loadUrl("file:///$imagePath")
            }
        }
    }


    private class MyWebViewClient : WebViewClient() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }
    }


        companion object {
            private const val LOG_TAG = "BrowserActivity"

            fun newInstance(context: Context) {
                val intent = Intent(context, BrowserActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
