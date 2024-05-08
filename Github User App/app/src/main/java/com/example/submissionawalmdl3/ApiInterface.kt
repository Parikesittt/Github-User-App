package com.example.submissionawalmdl3

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("users")
    suspend fun showUsers(): ArrayList<ItemsItem>
    @GET("search/users")
    fun getUser(
        @Query("q") query: String
    ):Call<DataUser>
    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username:String
    ): Call<DetailDataUser>
    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<ItemsItem>>
    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<ItemsItem>>
}