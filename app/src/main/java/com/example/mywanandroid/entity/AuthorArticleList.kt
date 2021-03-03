package com.example.mywanandroid.entity

data class AuthorArticleList(
    val curPage: Int,
    val datas: List<AuthorArticle>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)