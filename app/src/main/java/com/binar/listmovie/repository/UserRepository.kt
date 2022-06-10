package com.binar.listmovie.repository

import androidx.lifecycle.MutableLiveData
import com.binar.listmovie.data.user.GetAllUserItem
import com.binar.listmovie.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userapi : ApiService) {
    suspend fun getUser(): List<GetAllUserItem>{
        return userapi.getAllUser()
    }

    fun regisUser(username: String, password: String, email : String, liveData: MutableLiveData<GetAllUserItem>) {
        val apiClient : Call<GetAllUserItem> = userapi.registerNew(username, email, password, "","","","")
        apiClient.enqueue(object : Callback<GetAllUserItem> {
            override fun onResponse(
                call: Call<GetAllUserItem>,
                response: Response<GetAllUserItem>
            ) {
                if (response.isSuccessful) {
                    liveData.postValue(response.body())
                } else {
                    liveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<GetAllUserItem>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

}