package cn.woyeshi.base.utils

import android.content.ComponentCallbacks2
import com.facebook.common.memory.MemoryTrimType
import com.facebook.common.memory.MemoryTrimmable
import com.facebook.common.memory.MemoryTrimmableRegistry

/**
 * Created by charles on 16/01/2018.
 */


object MemoryTrimmable {

    private val memoryTrimmables = mutableListOf<MemoryTrimmable>()


    fun onLowMemory() {
        memoryTrimmables.forEach {
            it.trim(MemoryTrimType.OnSystemLowMemoryWhileAppInForeground)
        }
    }


    fun onTrimMemory(level: Int) {

        memoryTrimmables.forEach {

            when (level) {

                ComponentCallbacks2.TRIM_MEMORY_BACKGROUND -> {
                    it.trim(MemoryTrimType.OnAppBackgrounded)
                }

                ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW -> {
                    it.trim(MemoryTrimType.OnSystemLowMemoryWhileAppInForeground)
                }

                ComponentCallbacks2.TRIM_MEMORY_MODERATE -> {
                    it.trim(MemoryTrimType.OnSystemLowMemoryWhileAppInBackground)
                }

                ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE -> {
                    it.trim(MemoryTrimType.OnCloseToDalvikHeapLimit)
                }

            }


        }
    }

    object MyMemoryTrimmableRegistry : MemoryTrimmableRegistry {
        override fun unregisterMemoryTrimmable(trimmable: MemoryTrimmable?) {
            memoryTrimmables.remove(trimmable)
        }

        override fun registerMemoryTrimmable(trimmable: MemoryTrimmable?) {
            trimmable.let {
                memoryTrimmables.add(trimmable!!)
            }
        }

    }
}


