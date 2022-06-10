package com.binar.listmovie.network

import com.binar.listmovie.data.movie.GetAllMovieItem
import com.binar.listmovie.data.user.GetAllUserItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("PopularMovie")
    suspend fun getPopularMovie(): List<GetAllMovieItem>

    @GET("NewMovieAndika")
    suspend fun getNewMovie(): List<GetAllMovieItem>

    @GET("RecMovieAndika")
    suspend fun getRecMovie(): List<GetAllMovieItem>

    @GET("UserAndika")
    suspend fun getAllUser(): List<GetAllUserItem>

    @POST("UserAndika")
    @FormUrlEncoded
    fun registerNew(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("complete_name") completename: String,
        @Field("address") address: String,
        @Field("dateofbirth") dateofbirth: String,
        @Field("image") image: String
    ): Call<GetAllUserItem>

}