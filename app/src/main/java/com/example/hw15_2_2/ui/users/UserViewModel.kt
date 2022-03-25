package com.example.hw15_2_2.ui.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw15_2_2.data.UserRepository
import com.example.hw15_2_2.data.network.NetworkManager
import com.example.hw15_2_2.model.User
import retrofit2.Callback
import com.example.hw15_2_2.model.UserFromServer
import retrofit2.Call
import retrofit2.Response
import kotlin.concurrent.thread

class UserViewModel(var userRepository: UserRepository) : ViewModel() {
    var _listUsers = MutableLiveData<List<UserFromServer>>()
    private val _searchResult = MutableLiveData<List<UserFromServer>>()
    val searchResult: LiveData<List<UserFromServer>> = _searchResult
    var currentUserId : String = ""
    fun getUsersFromServer(){

        NetworkManager.service.getUserList().enqueue(object :Callback<List<UserFromServer>>{
            override fun onResponse(call: Call<List<UserFromServer>>, response: Response<List<UserFromServer>>) {
                _listUsers.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UserFromServer>>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }

        })
    }

    fun searchFromUsers(query:HashMap<String,String>){
        NetworkManager.service.getUserList(query).enqueue(object : Callback<List<UserFromServer>?> {
            override fun onResponse(
                call: Call<List<UserFromServer>?>,
                response: Response<List<UserFromServer>?>
            ) {
                _searchResult.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UserFromServer>?>, t: Throwable) {
                Log.d("TAG","searchResult:" + t.message.toString())
            }
        })

    }
    fun getUserFromFirstName(query: HashMap<String, String>) {
            searchFromUsers(query)
    }
    fun saveUserList(users:List<UserFromServer>){
        thread {
            userRepository.insertUserList(users)
        }
    }
}