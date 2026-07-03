package com.yazilim.chronolock

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    // 1. Kullanıcı Kayıt İsteği
    @FormUrlEncoded
    @POST("register.php")
    fun registerUser(
        @Field("username") user: String,
        @Field("email") mail: String,
        @Field("password") pass: String
    ): Call<AuthResponse>

    // 2. Kullanıcı Giriş İsteği
    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(
        @Field("username") user: String,
        @Field("password") pass: String
    ): Call<AuthResponse>

    //3. kullanıcı kapsüllerini listeleme isteği
    @FormUrlEncoded
    @POST("get_capsules.php")
    fun getCapsules(
        @Field("user_id") userId: Int
    ): Call<CapsuleResponse>

    //4. yeni kapsül ekleme isteği
    @FormUrlEncoded
    @POST("add_capsule.php")
    fun addCapsule(
        @Field("user_id") userId: Int,
        @Field("title") title: String,
        @Field("message") message: String,
        @Field("unlock_date") unlockDate: String
    ): Call<AuthResponse>
}