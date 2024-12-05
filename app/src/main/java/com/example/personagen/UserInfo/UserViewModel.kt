package com.example.personagen.UserInfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personagen.Data.Model.User
import com.example.personagen.Data.UserRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class UserViewModel(val repo: UserRepository) : ViewModel(){

    fun deleteUser(user: User){
        viewModelScope.launch {
            repo.deleteUser(user)
        }
    }

}