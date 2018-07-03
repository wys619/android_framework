package cn.woyeshi.entity.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by WYS on 2016/9/11.
 */
object SPHelper {

    private val DELIVER_SP_FILE_NAME = "sp_cache.xml"

    /**
     * 保存数据到缓存
     *
     * @param ctx
     * @param key
     * @param t
     */
    fun <T> saveData(ctx: Context, key: String, t: T) {
        val sp = ctx.getSharedPreferences(DELIVER_SP_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString(key, t.toString())
        editor.apply()
    }

    /**
     * 从缓存取出数据
     *
     * @param ctx
     * @param key
     * @return
     */
    fun getData(ctx: Context, key: String): String {
        val sp = ctx.getSharedPreferences(DELIVER_SP_FILE_NAME, Context.MODE_PRIVATE)
        return sp.getString(key, "")
    }


    fun dellDataString(ctx: Context, key: String) {
        val sp = ctx.getSharedPreferences(DELIVER_SP_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.remove(key)
        editor.apply()
    }


}
