package com.example.hw15_2_2.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.hw15_2_2.R
import com.example.hw15_2_2.data.network.NetworkManager
import com.example.hw15_2_2.databinding.FragmentSearchBinding
import com.example.hw15_2_2.model.User
import com.example.hw15_2_2.ui.users.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder


class SearchFragment : Fragment(R.layout.fragment_search) {
    val navArgs : SearchFragmentArgs by navArgs()
    val model: UserViewModel by activityViewModels()
    lateinit var binding : FragmentSearchBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        val call = NetworkManager.service.getUser(navArgs.id)
        call.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {

                binding.apply {
                    id.text = response.body()?._id
                    firstNameTv.text = response.body()?.firstName
                    lastNameTv.text = response.body()?.lastName
                    nationalCodeTv.text = response.body()?.nationalCode
                    val hobbies = StringBuilder("")
                    for (i in response.body()?.hobbies!!)
                        hobbies.append(i + " , ")
                    hobbiesTv.text = hobbies
                }
                context?.let {
                    Glide.with(it)
                        .load(response.body()?.image)
                        .placeholder(R.drawable.placeholder)
                        .into(binding.profileImage)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
                t.message?.let { Log.d("onFailure", it) }
            }

        })
    }
}