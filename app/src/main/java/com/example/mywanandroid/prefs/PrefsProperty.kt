package com.example.mywanandroid.prefs

import android.annotation.SuppressLint
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefsProperty<T>(val name: String, private val default: T) :   // name指的是键名
    ReadWriteProperty<Any?, T> {    //自定义属性委托的关键是继承 ReadWriteProperty

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return PrefsUtils.getValue(name, default)   //参数不要管，给出返值就行
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        PrefsUtils.putValue(name, value)      //这个value是属性赋值时传入的值
    }



}