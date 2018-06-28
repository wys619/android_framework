package cn.woyeshi.entity.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object ContextHolder {
    var applicationContext: Context? = null
}