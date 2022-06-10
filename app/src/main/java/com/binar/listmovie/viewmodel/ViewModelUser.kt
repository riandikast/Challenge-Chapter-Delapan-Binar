package com.binar.listmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.listmovie.data.user.GetAllUserItem
import com.binar.listmovie.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelUser @Inject constructor(private val api: UserRepository): ViewModel() {
    private val userState = MutableStateFlow(emptyList<GetAllUserItem>())
    val dataUserState : StateFlow<List<GetAllUserItem>>
        get() = userState

    private lateinit var registerLiveData : MutableLiveData<GetAllUserItem>

    init {
        viewModelScope.launch {
            val news = api.getUser()
            userState.value = news
        }

        registerLiveData = MutableLiveData()
    }

    fun registerLiveData(username : String, email: String, password: String){
        viewModelScope.launch {
            api.regisUser(username,password, email,registerLiveData)
        }
    }
}