package com.example.hw15_2_2.data.local.db

import com.example.hw15_2_2.data.local.UserDao
import com.example.hw15_2_2.model.User
import com.example.hw15_2_2.model.UserFromServer

class LocalDataSource (private var database:UserDatabase){
    private val userDao = database.userDao()

    fun insertUserList(users: List<UserFromServer>){
        userDao.insertUserList(*users.toTypedArray())
    }

}