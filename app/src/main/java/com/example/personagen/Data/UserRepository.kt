package com.example.personagen.Data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.personagen.Data.DataBase.UserDao
import com.example.personagen.Data.Model.ApiService
import com.example.personagen.Data.Model.User

class UserRepository(
    private val apiService: ApiService,
    private val userDao: UserDao
) {

    suspend fun generateRandomUser(gender: String, nationality: String): User{
        val apiResponse = apiService.getRandomUser(gender, nationality)
        val user = apiResponse.results.first();
        Log.i("user", user.toString())
        userDao.addUser(user)
        return user
    }

    fun getUsers(): LiveData<List<User>> = userDao.getUsers()

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

}