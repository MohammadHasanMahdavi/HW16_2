package com.example.hw15_2_2.ui.users

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw15_2_2.R
import com.example.hw15_2_2.databinding.FragmentUserBinding
import com.example.hw15_2_2.model.UserFromServer


class UserFragment : Fragment(R.layout.fragment_user) {
    lateinit var binding : FragmentUserBinding
    private val factory = UserViewModelFactory()
    private var viewModel : UserViewModel? = null

    private var listUsers = mutableListOf<UserFromServer>()
    private var search= mutableListOf<UserFromServer>()

    private lateinit var recyclerAdaptor: RecyclerAdaptor

    @SuppressLint("NotifyDataSetChanged")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserBinding.bind(view)
        val recyclerView = binding.recyclerView

        viewModel!!.getUsersFromServer()
        viewModel!!._listUsers.observe(viewLifecycleOwner){
            listUsers.clear()
            listUsers.addAll(it)
            recyclerAdaptor.notifyDataSetChanged()
            viewModel!!.saveUserList(it)
        }


        viewModel!!.searchResult.observe(viewLifecycleOwner){
            listUsers.clear()
            listUsers.addAll(it)
            recyclerAdaptor.notifyDataSetChanged()
        }

        binding.buttonSearch.setOnClickListener {
            val query : HashMap<String,String> = hashMapOf()

            if (binding.firstName.text.toString().isNotBlank())
                query.put("firstName",binding.firstName.text.toString() ,)

            if (binding.lastName.text.toString().isNotBlank())
                query.put("lastName",binding.lastName.text.toString())

            if (binding.nationalCode.text.toString().isNotBlank())
                query.put("nationalCode",binding.nationalCode.text.toString(),)

                viewModel!!.getUserFromFirstName(query)
        }

        recyclerAdaptor = RecyclerAdaptor(listUsers,object  : RecyclerAdaptor.OnItemClickListener{
            override fun onItemClickListener(user: UserFromServer) {
                val action = UserFragmentDirections.actionUserFragmentToSearchFragment(user._id)
                findNavController().navigate(action)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerAdaptor
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(requireActivity(),factory).get(UserViewModel::class.java)
    }
}