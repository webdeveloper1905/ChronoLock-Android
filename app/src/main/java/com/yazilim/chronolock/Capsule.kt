package com.yazilim.chronolock

import com.google.gson.annotations.SerializedName

data class Capsule (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("message") val message: String,
    @SerializedName("unlock_date") val unlockDate: String,
    @SerializedName("is_opened") val isOpened: Int
)