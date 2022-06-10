package com.binar.listmovie.repository

import com.binar.listmovie.data.movie.GetAllMovieItem
import com.binar.listmovie.network.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val userapi : ApiService) {
    suspend fun getPopular(): List<GetAllMovieItem>{
        return userapi.getPopularMovie()
    }

    suspend fun getNew(): List<GetAllMovieItem>{
        return userapi.getNewMovie()
    }

    suspend fun getRec(): List<GetAllMovieItem>{
        return userapi.getRecMovie()
    }
}