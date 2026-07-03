package com.yazilim.chronolock

import com.google.gson.annotations.SerializedName

data class CapsuleResponse(
    @SerializedName("status") val status: String,
    @SerializedName("capsules") val capsules: List<Capsule>// PHP'den dönen kapsül dizisini List olarak yakalıyoruz
)