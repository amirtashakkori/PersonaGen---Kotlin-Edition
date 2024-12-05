package com.example.personagen.GenerateUser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personagen.Data.Model.User
import com.example.personagen.Data.UserRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class GenerateViewModel(
    val repo: UserRepository
): ViewModel(){
    val user = MutableLiveData<User>()
    val error = MutableLiveData<String>()

    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        error.postValue(throwable.message)
    }

    public fun generateUser(gender: String, nationality: String){
        viewModelScope.launch(coroutineExceptionHandler) {
            user.postValue(repo.generateRandomUser(gender, nationality))
        }
    }

}
