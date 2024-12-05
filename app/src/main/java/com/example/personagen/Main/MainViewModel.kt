package com.example.personagen.Main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personagen.Data.Model.User
import com.example.personagen.Data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val repo: UserRepository): ViewModel() {

    val userList: LiveData<List<User>> = repo.getUsers()

    fun deleteUser(user: User){
        viewModelScope.launch {
            repo.deleteUser(user)
        }
    }


}