package cn.woyeshi.presenter.base

import okhttp3.Dispatcher
import okhttp3.OkHttpClient

class HttpUtils {

    object OkHttpManager {
        val client by lazy { OkHttpClient.Builder().dispatcher(Dispatcher(Threads.ioExecutor)).build() }
    }


}