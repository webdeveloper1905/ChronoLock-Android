package com.yazilim.chronolock

import com.google.gson.annotations.SerializedName

data class AuthResponse (
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("user_id") val userId: Int?,
    @SerializedName("username") val username: String?
)