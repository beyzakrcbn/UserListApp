package com.example.userlistapp.network

import retrofit2.http.GET
import com.example.userlistapp.model.User

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}
