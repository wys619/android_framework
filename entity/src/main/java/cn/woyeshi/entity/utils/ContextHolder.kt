package cn.woyeshi.entity.utils

import android.content.Context
import java.lang.ref.WeakReference

object ContextHolder {
    private var contextWeakReference: WeakReference<Context>? = null

    fun setApplicationContext(context: WeakReference<Context>) {
        this.contextWeakReference = context
    }

    fun getApplicationContext(): Context? {
        return contextWeakReference?.get()
    }

    fun releaseApplicationContext() {
        contextWeakReference = null
        System.gc()
    }

}