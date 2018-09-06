package cn.woyeshi.entity.beans.manager

import com.google.gson.annotations.SerializedName

data class UserInfo(@SerializedName("birthday")
                    var birthday: String = "",
                    @SerializedName("qq")
                    val qq: String = "",
                    @SerializedName("aliPay")
                    val aliPay: String = "",
                    @SerializedName("gender")
                    var gender: String = "",
                    @SerializedName("city")
                    val city: String = "",
                    @SerializedName("userName")
                    val userName: String = "",
                    @SerializedName("userId")
                    val userId: String = "",
                    @SerializedName("token")
                    val token: String = "",
                    @SerializedName("password")
                    val password: String = "",
                    @SerializedName("regTime")
                    val regTime: String = "",
                    @SerializedName("phone")
                    val phone: String = "",
                    @SerializedName("nickname")
                    var nickname: String = "",
                    @SerializedName("avartar")
                    var avartar: String = "",
                    @SerializedName("userType")
                    val userType: String = "",
                    @SerializedName("weChatId")
                    val weChatId: String = "",
                    @SerializedName("sina")
                    val sina: String = "",
                    @SerializedName("email")
                    val email: String = "")