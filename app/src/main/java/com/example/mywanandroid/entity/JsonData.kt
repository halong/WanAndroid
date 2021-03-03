package com.example.mywanandroid.entity

data class JsonData<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)