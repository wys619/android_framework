package cn.woyeshi.entity.utils

import android.widget.Toast

object ToastUtils {

    fun toast(msg: String) {
        if (ContextHolder.applicationContext == null) {
            return
        }
        Toast.makeText(ContextHolder.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

}