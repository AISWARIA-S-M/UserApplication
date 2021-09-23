package com.ibm.usersapplication.retrofit

import com.ibm.usersapplication.model.UserDetails
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
    @GET("5a79cf501c61ea68154812")
    fun getUserList(): Call<UserDetails>

}