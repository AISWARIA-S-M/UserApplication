package com.ibm.usersapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class UserDetails(
    val users : ArrayList<UserDetail>
)
@Parcelize
class Contact(
    val home:String,
    val mobile: String,
    val office:String
) : Parcelable
@Parcelize
class UserDetail(
    val contact: Contact,
    val email: String,
    val gender: String,
    val id : String,
    val name: String
) : Parcelable

