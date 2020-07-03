package com.zzq.net.callback

import android.net.ParseException
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonParseException
import com.zzq.net.R
import com.zzq.net.NetResponseMsgBean
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

/**
 * 在此项目中无需对Callback进行额外封装
 */
abstract class NetCallback<T>(liveData: MutableLiveData<NetResponseMsgBean>) : Callback<T> {

    private val UNAUTHORIZED = 401
    private val FORBIDDEN = 403
    private val NOT_FOUND = 404
    private val REQUEST_TIMEOUT = 408
    private val INTERNAL_SERVER_ERROR = 500
    private val BAD_GATEWAY = 502
    private val SERVICE_UNAVAILABLE = 503
    private val GATEWAY_TIMEOUT = 504

    private val RESPONSE_SUCCESS = 200;

    protected val errorLiveData = liveData

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected exception
     * occurred creating the request or processing the response.
     */
    override fun onFailure(call: Call<T>, t: Throwable) {
        //从onFailure设置的code都手动设置为小于0
        errorLiveData.value = NetResponseMsgBean(-100, "onFailure!", handleException(t))
    }

    /**
     * Invoked for a received HTTP response.
     *
     *
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call [Response.isSuccessful] to determine if the response indicates success.
     */
    override fun onResponse(call: Call<T>, response: Response<T>) {
        //默认成功，可直接取出数据
        if (response.code() == 200) {
            onSuccess(call, response)
        } else {
            //从onResponse设置的code都手动设置为大于0
            errorLiveData.value = NetResponseMsgBean(100, "onResponse Other !")
        }

    }

    abstract fun onSuccess(call: Call<T>, response: Response<T>)

    @StringRes
    private fun handleException(t: Throwable): Int {
        if (t is HttpException) {
            val httpException = t as HttpException
            return when (httpException.code()) {
                UNAUTHORIZED,
                FORBIDDEN,
                NOT_FOUND,
                REQUEST_TIMEOUT,
                GATEWAY_TIMEOUT,
                INTERNAL_SERVER_ERROR,
                BAD_GATEWAY,
                SERVICE_UNAVAILABLE ->
                    R.string.network_error
                else -> R.string.network_error
            }
        } else if (t is JsonParseException
            || t is JSONException
            || t is ParseException
        ) {
            return R.string.parse_error
        } else if (t is ConnectException) {
            return R.string.connect_fail_error
        } else if (t is SSLHandshakeException) {
            return R.string.Certificate_validation_failed_error
        } else if (t is ConnectTimeoutException) {
            return R.string.connection_timeout_error
        } else if (t is SocketTimeoutException) {
            return R.string.connection_timeout_error
        } else if (t is UnknownHostException) {
            return R.string.unknown_error
        } else {
            return R.string.unknown_error
        }
    }
}