package app

import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.InetSocketAddress
import java.net.Proxy

class TorifiedWebViewClient : WebViewClient() {

    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest,
    ): WebResourceResponse {
        val proxy = Proxy(Proxy.Type.SOCKS, InetSocketAddress("localhost", 9050))
        val client = OkHttpClient.Builder().proxy(proxy).build()
        val call = client.newCall(Request.Builder().url(request.url.toString()).build())
        val response = call.execute()
        val body = response.body!!

        return WebResourceResponse(
            "${body.contentType()!!.type}/${body.contentType()!!.subtype}",
            body.contentType()!!.charset().toString(),
            response.body!!.byteStream(),
        )
    }
}