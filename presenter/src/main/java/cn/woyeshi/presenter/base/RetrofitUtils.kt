package cn.woyeshi.presenter.base

import cn.woyeshi.entity.Constants
import cn.woyeshi.entity.beans.manager.UserInfo
import cn.woyeshi.entity.utils.ApkInfoUtils
import cn.woyeshi.entity.utils.ContextHolder
import cn.woyeshi.entity.utils.SPHelper
import cn.woyeshi.presenter.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtils {

        private val BASE_URL = "https://api.woyeshi.cn/"
//    private val BASE_URL = "http://192.168.1.117:8081/"

    private var retrofit: Retrofit? = null

    private val DEFAULT_TIME_OUT = 5                //超时时间 5s
    private val DEFAULT_READ_TIME_OUT = 10

    private var accessToken: String = ""

    private fun initRetrofit(): Retrofit {
        if (retrofit == null) {
            accessToken = SPHelper.getData(ContextHolder.getApplicationContext(), Constants.SPKeys.KEY_LOGIN_USER_INFO, UserInfo::class.java)?.token ?: ""
            // 创建 OKHttpClient
            ContextHolder.token = accessToken
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)//连接超时时间
            builder.writeTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)//写操作 超时时间
            builder.readTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)//读操作超时时间

            // 添加公共参数拦截器
            val commonInterceptor = HttpCommonInterceptor.Builder()
                    .addHeaderParams("paltform", "android")     //平台
                    .addHeaderParams("token", accessToken)      //令牌
                    .addHeaderParams("version", ApkInfoUtils.getVersionCode(ContextHolder.getApplicationContext()))            //客户端版本
                    .build()
            builder.addInterceptor(commonInterceptor)

            val logInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
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
        if (accessToken != ContextHolder.token) {           //token发生改变，就重新弄构造请求实例
            retrofit = null
        }
        if (retrofit == null) {
            initRetrofit()
        }
        return retrofit!!.create(service)!!
    }

}