package com.example.personagen.Data.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.personagen.Data.Model.User

@Database(entities = [User::class], exportSchema = false, version = 2)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getDao(): UserDao

    companion object{
        @Volatile
        private var instance: AppDataBase?= null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this){
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "db_users"
                ).build()
                instance = newInstance
                newInstance
            }
        }
    }

}