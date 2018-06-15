package cn.woyeshi.base.utils

import android.content.Context
import android.graphics.Bitmap
import cn.woyeshi.presenter.base.HttpUtils
import cn.woyeshi.presenter.base.Threads
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.common.util.ByteConstants
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.facebook.imagepipeline.core.ExecutorSupplier
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig
import java.io.File
import java.util.concurrent.Executor

object FrescoUtils {

    fun initFresco(context: Context) {
        //init Fresco
        val mainDiskCacheConfig = DiskCacheConfig.newBuilder(context)
                .setMaxCacheSize((500 * ByteConstants.MB).toLong())//最大缓存
                .setMaxCacheSizeOnLowDiskSpace((150 * ByteConstants.MB).toLong())
                .setMaxCacheSizeOnVeryLowDiskSpace((50 * ByteConstants.MB).toLong())
                .setBaseDirectoryName("main_image")
                .setBaseDirectoryPathSupplier {
                    File(StorageUtils.getExternalCacheDir(context))
                }.build()

        val smallDiskCacheConfig = DiskCacheConfig.newBuilder(context)
                .setMaxCacheSize((300 * ByteConstants.MB).toLong())//最大缓存
                .setMaxCacheSizeOnLowDiskSpace((150 * ByteConstants.MB).toLong())
                .setMaxCacheSizeOnVeryLowDiskSpace((50 * ByteConstants.MB).toLong())
                .setBaseDirectoryName("small_image")
                .setBaseDirectoryPathSupplier {
                    File(StorageUtils.getExternalCacheDir(context))
                }.build()

        val config = OkHttpImagePipelineConfigFactory.newBuilder(context, HttpUtils.OkHttpManager.client)
                .setMemoryTrimmableRegistry(MemoryTrimmable.MyMemoryTrimmableRegistry)
                .setMainDiskCacheConfig(mainDiskCacheConfig)
                .setSmallImageDiskCacheConfig(smallDiskCacheConfig)
                .setProgressiveJpegConfig(SimpleProgressiveJpegConfig())
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .setExecutorSupplier(object : ExecutorSupplier {
                    override fun forLocalStorageRead(): Executor {
                        return Threads.ioExecutor
                    }

                    override fun forDecode(): Executor {
                        return Threads.ioExecutor
                    }

                    override fun forLocalStorageWrite(): Executor {
                        return Threads.ioExecutor
                    }

                    override fun forLightweightBackgroundTasks(): Executor {
                        return Threads.ioExecutor
                    }

                    override fun forBackgroundTasks(): Executor {
                        return Threads.ioExecutor
                    }

                })
                .build()
        Fresco.initialize(context, config)
    }

}

