package com.example.mywanandroid.entity

data class ProjectList(
    val curPage: Int,
    val datas: List<Project>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)