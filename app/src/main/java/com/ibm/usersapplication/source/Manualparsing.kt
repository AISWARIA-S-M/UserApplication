package com.ibm.usersapplication.source


import android.util.Log
import com.google.gson.Gson
import com.ibm.usersapplication.R
import com.ibm.usersapplication.app.UserDetailsInfo
import com.ibm.usersapplication.model.UserDetails


class Manualparsing{ //} : RetrofitApi {
    //override
    suspend fun getJsonData(): UserDetails {
        //override suspend fun getUserList(): Call<UserDetails> {
   //     val url = URL("https://api.npoint.io/5a79cf501c61ea681548")
        val objectArrayString: String = UserDetailsInfo.mInstance.resources.openRawResource(R.raw.userinfo)
            .bufferedReader().use { it.readText() }
        Log.d("json",objectArrayString)

        val objectArray = Gson().fromJson(objectArrayString, UserDetails::class.java)
        Log.i("data", objectArray.toString())
        return objectArray
    }

   /* override fun getUserList(): Call<UserDetails> {
        TODO("Not yet implemented")
    }*/
}