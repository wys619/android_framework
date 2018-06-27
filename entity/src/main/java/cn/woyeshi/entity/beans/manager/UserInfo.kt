package cn.woyeshi.entity.beans.manager

import com.google.gson.annotations.SerializedName

data class UserInfo(@SerializedName("password")
                    val password: String = "",
                    @SerializedName("userName")
                    val userName: String = "",
                    @SerializedName("userId")
                    val userId: String = "",
                    @SerializedName("token")
                    val token: String = "")