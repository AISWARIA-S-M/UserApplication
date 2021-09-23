package com.ibm.usersapplication.source

import com.ibm.usersapplication.model.UserDetails
import com.ibm.usersapplication.retrofit.RetrofitApi
import com.ibm.usersapplication.utils.GenericClass
import retrofit2.await


class UserdataRepo(private val userApi: RetrofitApi){
   suspend fun getuserData() : UserDetails{
       //return GenericClass.getInstance().getResponse()
       return userApi.getUserList().await()
    }
}



