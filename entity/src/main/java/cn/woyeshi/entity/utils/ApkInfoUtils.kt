package cn.woyeshi.entity.utils

import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils

object ApkInfoUtils {
    private var mVersionName = ""
    private var mVersionCode = 0

    fun getVersionName(context: Context?): String {
        if (context == null) {
            return "1.0.0"
        }
        if (TextUtils.isEmpty(mVersionName)) {
            val versionName = getVersionNameImpl(context)
            mVersionName = if (versionName.contains("-")) {
                versionName.split("-")[0]
            } else {
                versionName
            }
        }

        return mVersionName
    }

    fun getVersionCode(context: Context?): Int {
        if (context == null) {
            return 0
        }
        if (mVersionCode == 0) {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_CONFIGURATIONS)
            mVersionCode = packageInfo.versionCode
        }
        return mVersionCode
    }

    private fun getVersionNameImpl(context: Context): String {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_CONFIGURATIONS)
        return packageInfo.versionName
    }


}