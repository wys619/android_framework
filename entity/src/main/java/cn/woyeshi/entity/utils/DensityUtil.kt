package cn.woyeshi.entity.utils

import android.content.Context
import android.view.WindowManager

object DensityUtil {

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun getScreenWidth(ctx: Context): Int {
        val wm = ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm.defaultDisplay.width
    }

    fun getScreenHeight(ctx: Context): Int {
        val wm = ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm.defaultDisplay.height
    }

}
