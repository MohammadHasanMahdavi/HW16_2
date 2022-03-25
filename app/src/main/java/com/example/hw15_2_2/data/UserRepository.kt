package com.example.hw15_2_2.data

import com.example.hw15_2_2.data.local.db.LocalDataSource
import com.example.hw15_2_2.model.User
import com.example.hw15_2_2.model.UserFromServer

class UserRepository(private val localDataSource: LocalDataSource) {
    fun insertUserList(users : List<UserFromServer>){
        localDataSource.insertUserList(users)
    }
}