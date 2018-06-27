package cn.woyeshi.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by wys on 2017/11/8.
 */
data class BaseResponse<E>(@SerializedName("msg")
                           val msg: String = "",
                           @SerializedName("code")
                           val code: Int = 0,
                           @SerializedName("data")
                           val data: E) : Serializable