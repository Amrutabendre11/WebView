package com.example.webview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Go =findViewById<Button>(R.id.btnGo)
        val Next =findViewById<Button>(R.id.btnNext)
        val Back =findViewById<Button>(R.id.btnGoBack)
        var webView =findViewById<WebView>(R.id.webview)
        val editUrl = findViewById<EditText>(R.id.url)
        val context = this
        webView.webViewClient = MyWebViewClient()
        Go.setOnClickListener({
            webView.loadUrl("https://www.javatpoint.com/"+editUrl.text.toString())
        })

        Back.setOnClickListener({


            if(webView.canGoBack())
                webView.goBack()
            else
                Toast.makeText(this,"No History Avilabe",Toast.LENGTH_LONG).show()
        })

        Next.setOnClickListener({
            if(webView.canGoForward())
                webView.goForward()
            else
                Toast.makeText(this,"No History Avilabe",Toast.LENGTH_LONG).show()
        })
    }
    class MyWebViewClient : WebViewClient(){
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url: String = request?.url.toString();
            view?.loadUrl(request?.url.toString())
            return true
        }
        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }
    }
}