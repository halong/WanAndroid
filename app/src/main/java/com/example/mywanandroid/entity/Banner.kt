package com.example.mywanandroid.entity

data class Banner(
    val desc: String,
    val id: Int,
    var imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)