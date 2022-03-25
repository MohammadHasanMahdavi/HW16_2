package com.example.hw15_2_2.data.network

import com.example.hw15_2_2.model.User
import com.example.hw15_2_2.model.UserFromServer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiInteface {
    @GET("users")
    fun getUserList(@QueryMap map : HashMap<String,String> = hashMapOf()) : Call<List<UserFromServer>>

    @GET("users/{id}")
    fun getUser(@Path("id") id:String) : Call<User>
}