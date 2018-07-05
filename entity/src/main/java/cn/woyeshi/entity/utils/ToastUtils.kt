package cn.woyeshi.entity.utils

import android.widget.Toast

object ToastUtils {

    fun toast(msg: String) {
        if (ContextHolder.getApplicationContext() == null) {
            return
        }
        Toast.makeText(ContextHolder.getApplicationContext(), msg, Toast.LENGTH_SHORT).show()
    }

}