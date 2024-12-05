package com.example.personagen.Data.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.personagen.Data.Model.User
import retrofit2.http.Query

@Dao
interface UserDao {
    @androidx.room.Query("Select * From table_users")
    fun getUsers(): LiveData<List<User>>

    @Insert
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

}