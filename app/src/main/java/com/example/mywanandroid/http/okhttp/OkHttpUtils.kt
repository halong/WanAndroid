package com.example.mywanandroid.http.okhttp

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object OkHttpUtils {
    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    fun get(url: String, responseCallback: Callback) {
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        client.newCall(request).enqueue(responseCallback)
    }

    fun get(url: String, queryMap: Map<String, String>, responseCallback: Callback) {
        var requestString = url
        if (queryMap.isNotEmpty()) {
            requestString = "$requestString?"
            queryMap.forEach { (key, value) ->
                requestString = "$requestString$key=$value&"
            }
            requestString.trimEnd('&')
        }

        val request = Request.Builder()
            .url(requestString)
            .get()
            .build()
        client.newCall(request).enqueue(responseCallback)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun post(url: String, fieldMap: Map<String, String>, responseCallback: Callback) {
        val formBodyBuilder = FormBody.Builder()

        if (fieldMap.isNotEmpty()) {
            fieldMap.forEach { (key, value) ->
                formBodyBuilder.add(key, Uri.encode(value))
            }
        }

        val request = Request.Builder()
            .url(url)
            .post(formBodyBuilder.build())
            .build()
        client.newCall(request).enqueue(responseCallback)
    }

    fun postFiles(url: String, multipartMap: Map<String, File>, responseCallback: Callback) {
        val multipartBodyBuilder = MultipartBody.Builder()

        if (multipartMap.isNotEmpty()) {
            multipartMap.forEach { (key, value) ->
                val body = value.asRequestBody("application".toMediaTypeOrNull())
                multipartBodyBuilder.addFormDataPart(key,value.name,body)
            }
        }

        val request = Request.Builder()
            .url(url)
            .post(multipartBodyBuilder.build())
            .build()
        client.newCall(request).enqueue(responseCallback)
    }
}