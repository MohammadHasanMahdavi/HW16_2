package com.example.hw15_2_2.data.local.db

import android.content.Context
import android.provider.DocumentsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hw15_2_2.data.local.UserDao
import com.example.hw15_2_2.model.UserFromServer


@Database(entities = [UserFromServer::class], version = 1, exportSchema = true)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao():UserDao

    companion object{
        @Volatile
        private var INSTANCE : UserDatabase? = null

        fun getDatabase(context: Context) : UserDatabase {
             return INSTANCE ?: synchronized(this){
                val db = Room.databaseBuilder(context,
                UserDatabase::class.java,
                "user-database").build()
                INSTANCE = db
                 return db as UserDatabase
             }
        }
    }
}