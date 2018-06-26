package cn.woyeshi.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by wys on 2017/11/8.
 */
class BaseResponse<E> : Serializable {

    @SerializedName("msg")
    val msg: String = ""
    @SerializedName("code")
    val code: Int = 0
    @SerializedName("data")
    val data: E? = null


}