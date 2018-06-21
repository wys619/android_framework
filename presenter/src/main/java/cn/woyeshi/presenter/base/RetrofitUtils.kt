package cn.woyeshi.presenter.base

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtils {

    private val BASE_URL = "http://192.168.56.1:8080/"

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
                    .addHeaderParams("paltform", "android")
                    .addHeaderParams("userToken", "1234343434dfdfd3434")
                    .addHeaderParams("userId", "123445")
                    .build()
            builder.addInterceptor(commonInterceptor)

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