package com.zzq.lifeutil.net

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.nio.charset.Charset

/**
 * 打印日志
 */
class LogPrintInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val responseBody = response.body
        val source = responseBody!!.source()
        source.request(Long.MAX_VALUE)
        val buffer = source.buffer
        val charset = Charset.forName("UTF-8")
        val bodyString = buffer.clone().readString(charset)
        Log.e("LifeUtil", request.url.toUrl().toString())
        Log.e("LifeUtil", bodyString)
        return response
    }
}