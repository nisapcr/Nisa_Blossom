package com.example.nisa_blossom.pertemuan_6

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.nisa_blossom.R

class WebView_Activity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        // 🔷 Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // tombol back (←)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 🔷 WebView
        webView = findViewById(R.id.webView)

        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true

        webView.webViewClient = WebViewClient()

        // 🌐 Load website kamu
        webView.loadUrl("https://siti-sic.alwaysdata.net/")
    }

    // 🔙 tombol ← di toolbar
    override fun onSupportNavigateUp(): Boolean {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            finish()
        }
        return true
    }

    // 🔙 tombol back HP
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}