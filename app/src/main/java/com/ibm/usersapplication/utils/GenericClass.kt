package com.ibm.usersapplication.utils

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.ibm.usersapplication.app.UserDetailsInfo
import com.ibm.usersapplication.model.UserDetail
import com.ibm.usersapplication.model.UserDetails
import com.ibm.usersapplication.retrofit.RetrofitApi
import com.ibm.usersapplication.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GenericClass {

    companion object {
        private var genericClass: GenericClass? = null

        fun getInstance(): GenericClass {
            if (genericClass == null)
                genericClass = GenericClass()
            return genericClass!!
        }
    }




    var service: RetrofitApi = RetrofitInstance.builder.create(RetrofitApi::class.java)
//
//    private val userdataRepo = UserdataRepo(service)
//    suspend fun getdetails() : ArrayList<UserDetail>{
//        return userdataRepo.getuserData().users
//    }


    fun getResponse() : LiveData<UserDetails> { //Int {
        val data = MutableLiveData<UserDetails>()
//        service.getUserList().enqueue(object : Callback<ArrayList<UserDetail>> {
        service.getUserList().enqueue(object : Callback<UserDetails> {


//            override fun onResponse(
//                call: Call<ArrayList<UserDetail>>, response: Response<ArrayList<UserDetail>>
//            ) {
    override fun onResponse(
            call: Call<UserDetails>, response: Response<UserDetails>
            ){
                if(response.code()==200){
                    val lucky = response.body()
                    val userlist = ArrayList<UserDetail>()
                    lucky?.users?.forEach {
                        val userdata = UserDetail(it.contact,it.email,it.gender,it.id,it.name)
                        userlist.add(userdata)
               //         Log.d("each user data",userdata.toString())
                    }
                    val jsondata = Gson().toJson(response.body())
                    Log.d("json data",jsondata)
               //     val jsonObject = JsonObject().getAsJsonObject(jsondata)
               //     val jsonarray = jsonObject.getAsJsonArray("users")
                    val details = Gson().fromJson(jsondata,UserDetails::class.java)
                   Log.d("json obj",details.toString())
                    data.value= UserDetails(userlist)
                    Toast.makeText(UserDetailsInfo.mInstance,"Success!!!",Toast.LENGTH_LONG).show()
              }
                else if(response.code()==500){
              //      data.value=null
                  Toast.makeText(UserDetailsInfo.mInstance,"Please check the url",Toast.LENGTH_LONG).show()
              }
               else if(response.isSuccessful && response.body()==null){
            //       data.value=null
                    Toast.makeText(UserDetailsInfo.mInstance,"No data available",Toast.LENGTH_LONG).show()
                }
                else{
          //          data.value=null
                  Toast.makeText(UserDetailsInfo.mInstance,"Sorry! Something went wrong!",Toast.LENGTH_LONG).show()
              }
                val udata = response.body()
                Log.d("data1",udata?.users.toString())

            }
override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                Toast.makeText(UserDetailsInfo.mInstance,t.message,Toast.LENGTH_LONG).show()
                Log.d("onFailure", t.toString())
            }

        })
        return data
    }

}
