package com.example.mywanandroid.prefs

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.mywanandroid.App
import java.io.*

object PrefsUtils {
    private val prefs: SharedPreferences by lazy {
        App.getInstance().getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    /**
     * 根据键名从SharedPreferences取值
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> getValue(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Int -> getInt(name, default)
            is Long -> getLong(name, default)
            is Float -> getFloat(name, default)
            is String -> getString(name, default)!!
            is Boolean -> getBoolean(name, default)
            else -> deSerialization(getString(name, serialize(default)))
        }
        return res as T
    }

    /**
     * 根据键名往SharedPreferences存值
     */
    @SuppressLint("CommitPrefEdits")
    fun <T> putValue(name: String, value: T) = with(prefs.edit()) {
        //with(t:T,block: T() -> R)
        when (value) {
            is Int -> putInt(name, value)
            is Long -> putLong(name, value)
            is Float -> putFloat(name, value)
            is String -> putString(name, value)
            is Boolean -> putBoolean(name, value)
            else -> putString(name, serialize(value))
        }.apply()
    }

    /**
     * 查询某个key是否已经存在
     */
    fun contains(key: String): Boolean {
        return prefs.contains(key)
    }

    /**
     * 返回所有的键值对
     */
    fun getAll(): Map<String, *> {
        return prefs.all
    }

    /**
     * 删除全部数据
     */
    fun clearPreference() {
        prefs.edit().clear().apply()
    }

    /**
     * 根据key删除存储数据
     */
    fun clearPreference(key: String) {
        prefs.edit().remove(key).apply()
    }

    /**
     * 序列化对象
     */
    @Throws(IOException::class)
    fun <A> serialize(obj: A): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(
            byteArrayOutputStream
        )
        objectOutputStream.writeObject(obj)
        var serStr = byteArrayOutputStream.toString("ISO-8859-1")
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8")
        objectOutputStream.close()
        byteArrayOutputStream.close()
        return serStr
    }

    /**
     * 反序列化对象
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IOException::class, ClassNotFoundException::class)
    fun <A> deSerialization(str: String?): A {
        val redStr = java.net.URLDecoder.decode(str, "UTF-8")
        val byteArrayInputStream = ByteArrayInputStream(
            redStr.toByteArray(charset("ISO-8859-1"))
        )
        val objectInputStream = ObjectInputStream(
            byteArrayInputStream
        )
        val obj = objectInputStream.readObject() as A
        objectInputStream.close()
        byteArrayInputStream.close()
        return obj
    }
}