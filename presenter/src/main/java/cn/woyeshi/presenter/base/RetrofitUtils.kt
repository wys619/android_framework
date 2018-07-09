package cn.woyeshi.presenter.base

import cn.woyeshi.presenter.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtils {

        private val BASE_URL = "http://192.168.248.55:8081/"
//    private val BASE_URL = "http://192.168.1.101:8081/"

    private var retrofit: Retrofit? = null

    private val DEFAULT_TIME_OUT = 5                //超时时间 5s
    private val DEFAULT_READ_TIME_OUT = 10

    private fun initRetrofit(): Retrofit {
        if (retrofit == null) {

            // 创建 OKHttpClient
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)//连接超时时间
            builder.writeTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)//写操作 超时时间
            builder.readTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)//读操作超时时间

            // 添加公共参数拦截器
            val commonInterceptor = HttpCommonInterceptor.Builder()
                    .addHeaderParams("paltform", "android")     //平台
                    .addHeaderParams("token", "")               //令牌
                    .addHeaderParams("version", "1")            //客户端版本
                    .build()
            builder.addInterceptor(commonInterceptor)


            val logInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                //显示日志
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            builder.addInterceptor(logInterceptor)

            retrofit = Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return </T>
     */
    fun <T> create(service: Class<T>): T {
        if (retrofit == null) {
            initRetrofit()
        }
        return retrofit!!.create(service)!!
    }

}