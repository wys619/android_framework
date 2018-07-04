package cn.woyeshi.entity.utils

import android.content.Context
import android.text.TextUtils
import kotlin.reflect.KClass

/**
 * Created by WYS on 2016/9/11.
 */
object SPHelper {

    private val CURRENT_SP_FILE_NAME = "sp_cache.xml"

    /**
     * 保存数据到缓存
     *
     * @param ctx
     * @param key
     * @param value
     */
    fun <T> saveData(ctx: Context, key: String, value: T) {
        if (TextUtils.isEmpty(key)) {
            return
        }
        val sp = ctx.getSharedPreferences(CURRENT_SP_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        when (value) {
            is Int -> {
                editor.putInt(key, value)
            }
            is Long -> {
                editor.putLong(key, value)
            }
            is String -> {
                editor.putString(key, value)
            }
            is Boolean -> {
                editor.putBoolean(key, value)
            }
            is Float -> {
                editor.putFloat(key, value)
            }
            else -> {
                editor.putString(key, GsonUtil.toJson(value))
            }
        }
        editor.apply()
    }

    /**
     * 从缓存取出数据
     *
     * @param ctx
     * @param key
     * @return
     */
    fun <T> getData(ctx: Context, key: String, c: Class<T>): T? {
        if (TextUtils.isEmpty(key)) {
            return null
        }
        val sp = ctx.getSharedPreferences(CURRENT_SP_FILE_NAME, Context.MODE_PRIVATE)
        when (c) {
            Int::class.java -> {
                return sp.getInt(key, 0) as T
            }
            Long::class.java -> {
                return sp.getLong(key, 0L) as T
            }
            String::class.java -> {
                return sp.getString(key, "") as T
            }
            Boolean::class.java -> {
                return sp.getBoolean(key, false) as T
            }
            Float::class.java -> {
                return sp.getFloat(key, 0f) as T
            }
            else -> {
                val str = sp.getString(key, "")
                return GsonUtil.fromJson(str, c)
            }
        }
    }

    fun dellDataString(ctx: Context, key: String) {
        if (TextUtils.isEmpty(key)) {
            return
        }
        val sp = ctx.getSharedPreferences(CURRENT_SP_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.remove(key)
        editor.apply()
    }


}
