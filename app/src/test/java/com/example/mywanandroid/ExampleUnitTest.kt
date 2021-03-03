package com.example.mywanandroid

import android.net.Uri
import com.example.mywanandroid.entity.Banner
import com.example.mywanandroid.entity.JsonData
import com.example.mywanandroid.http.okhttp.OkHttpUtils
import com.example.mywanandroid.http.retrofit.ApiService
import com.example.mywanandroid.http.retrofit.HttpUtils
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.junit.Test

import org.junit.Assert.*
import java.io.IOException
import java.net.URL

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        println("hello test")
    }

    @Test
    fun testOkHttpUtils(){
        print(URL("https://www.baidu.com").readText())

        OkHttpUtils.get("https://www.baidu.com",object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                print(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                print(response.body)
            }

        })

    }
}