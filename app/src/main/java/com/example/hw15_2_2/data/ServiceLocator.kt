package com.example.hw15_2_2.data

import com.example.hw15_2_2.data.local.db.LocalDataSource
import com.example.hw15_2_2.data.local.db.UserDatabase
import com.example.hw15_2_2.ui.GlobalApplication

class ServiceLocator {
    companion object{

        private val database  = UserDatabase.getDatabase(GlobalApplication.getAppContext())
        private val localDatasource = LocalDataSource(database as UserDatabase)
        private val userRepository = UserRepository(localDatasource)

        fun getUserRepository(): UserRepository {
            return userRepository
        }

    }
}