package cn.woyeshi.entity.beans.manager

import com.google.gson.annotations.SerializedName

data class LoginInfo(@SerializedName("msg")
                     val msg: String = "",
                     @SerializedName("code")
                     val code: Int = 0,
                     @SerializedName("data")
                     val data: List<UserInfo>?)