package com.example.userlistapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
) : Parcelable  //Hızlı ve optimize edilmiş veri taşımayı sağlar.