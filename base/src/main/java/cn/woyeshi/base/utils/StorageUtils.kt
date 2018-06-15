package cn.woyeshi.base.utils

import android.content.Context

object StorageUtils {
    /**
     * /sdcard/Android/data/your packageName/cache
     */
    fun getExternalCacheDir(context: Context): String {
        return context.externalCacheDir.absolutePath
    }
}