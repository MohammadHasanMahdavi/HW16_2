package com.example.hw15_2_2.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hw15_2_2.data.ServiceLocator

class UserViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            val userRepository  = ServiceLocator.getUserRepository()
            return UserViewModel(userRepository)as T
        }
        throw IllegalArgumentException("ViewModel Class Not Fount.")
    }
}