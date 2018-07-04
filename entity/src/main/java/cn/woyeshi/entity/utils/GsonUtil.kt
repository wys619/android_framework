package cn.woyeshi.entity.utils

import android.text.TextUtils

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

object GsonUtil {

    val gson: Gson
        get() {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.addSerializationExclusionStrategy(IgnoreStrategy())
            return gsonBuilder.create()
        }


    fun <T> fromJson(json: String, clazz: Class<T>): T? {
        if (TextUtils.isEmpty(json)) {
            return null
        }
        val gson = Gson()
        return gson.fromJson(json, clazz)
    }

    fun toJson(obj: Any?): String {
        return if (obj == null) {
            ""
        } else {
            gson.toJson(obj)
        }
    }

    internal class IgnoreStrategy : ExclusionStrategy {
        override fun shouldSkipField(fieldAttributes: FieldAttributes): Boolean {
            val annotations = fieldAttributes.annotations
            for (annotation in annotations) {
                if (annotation.annotationClass == GsonIgnore::class.java) {
                    return true
                }
            }
            return false
        }

        override fun shouldSkipClass(clazz: Class<*>): Boolean {
            return false
        }
    }


    @Target(AnnotationTarget.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    internal annotation class GsonIgnore

}
