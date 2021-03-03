package com.example.mywanandroid.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mywanandroid.entity.ArticleList
import com.example.mywanandroid.entity.Banner
import com.example.mywanandroid.entity.JsonData
import com.example.mywanandroid.http.retrofit.HttpUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    init {
        getBanner()
        getArticleList()
    }

    private val _error = MutableLiveData<String>()
    val errorData: LiveData<String> = _error

    private val _article = MutableLiveData<ArticleList>()
    val articleData: LiveData<ArticleList> = _article

    private val _banner = MutableLiveData<List<Banner>>()
    val bannerData: LiveData<List<Banner>> = _banner

    private fun getBanner() {
        HttpUtils.apiService.getBanner().enqueue(object : Callback<JsonData<List<Banner>>> {
            override fun onResponse(
                call: Call<JsonData<List<Banner>>>,
                response: Response<JsonData<List<Banner>>>
            ) {
                _banner.value = response.body()?.data
            }

            override fun onFailure(call: Call<JsonData<List<Banner>>>, t: Throwable) {
                _error.value = t.message
            }

        })
    }

    private fun getArticleList() {
        HttpUtils.apiService.getArticleList(0).enqueue(object : Callback<JsonData<ArticleList>> {
            override fun onResponse(
                call: Call<JsonData<ArticleList>>,
                response: Response<JsonData<ArticleList>>
            ) {
                _article.value = response.body()?.data
            }

            override fun onFailure(call: Call<JsonData<ArticleList>>, t: Throwable) {
                _error.value = t.message
            }

        })
    }


}