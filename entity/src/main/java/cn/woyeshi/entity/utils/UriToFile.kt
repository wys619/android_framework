package cn.woyeshi.entity.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.text.TextUtils
import java.io.File

object UriToFile {

    fun getFileByUri(context: Context, uri: Uri): File? {
        var path: String? = null
        try {
            if ("file" == uri.scheme) {
                path = uri.encodedPath
                if (path != null) {
                    path = Uri.decode(path)
                    val cr = context.contentResolver
                    val buff = StringBuffer()
                    buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'$path'").append(")")
                    val cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, arrayOf(MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA), buff.toString(), null, null)
                            ?: return null
                    var index = 0
                    var dataIdx: Int
                    cur.moveToFirst()
                    while (!cur.isAfterLast) {
                        index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID)
                        index = cur.getInt(index)
                        dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                        path = cur.getString(dataIdx)
                        cur.moveToNext()
                    }
                    cur.close()
                    if (index != 0) {
                        val u = Uri.parse("content://media/external/images/media/$index")
                        println("temp uri is :$u")
                    }
                }
                if (path != null) {
                    return File(path)
                }
            } else if ("content" == uri.scheme) {
                val proj = arrayOf(MediaStore.Images.Media.DATA)
                val cursor = context.contentResolver.query(uri, proj, null, null, null)
                        ?: return null
                if (cursor.moveToFirst()) {
                    val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    path = cursor.getString(columnIndex)
                }
                cursor.close()
                return if (TextUtils.isEmpty(path)) {
                    null
                } else
                    File(path!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}