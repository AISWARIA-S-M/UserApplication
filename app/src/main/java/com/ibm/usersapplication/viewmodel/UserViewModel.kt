package com.ibm.usersapplication.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibm.usersapplication.model.UserDetails
import com.ibm.usersapplication.source.UserdataRepo
import com.ibm.usersapplication.utils.GenericClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {

   private val userdataRepo = UserdataRepo(GenericClass.getInstance().service)
    val data = MutableLiveData<UserDetails>()
    fun fetchuserdata()  {
            viewModelScope.launch {
                val users = withContext(Dispatchers.IO) {
                   try{
                        GenericClass.getInstance().getResponse().value
                        userdataRepo.getuserData()
                    }
                    catch (e: Exception) {
                        Log.d("Http Exception", e.toString())
                        null
                    }
                }

                data.value = users
            }
        }
}


