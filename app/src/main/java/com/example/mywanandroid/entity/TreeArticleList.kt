package com.example.mywanandroid.entity

data class TreeArticleList(
    val curPage: Int,
    val datas: List<TreeArticle>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)