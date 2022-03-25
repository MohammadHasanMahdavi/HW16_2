package com.example.hw15_2_2.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw15_2_2.databinding.CustomViewBinding
import com.example.hw15_2_2.model.UserFromServer

class RecyclerAdaptor(val items:List<UserFromServer>,val itemClickListener:OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener{
        fun onItemClickListener(user : UserFromServer)
    }

    inner class MyViewHolder(private var binding: CustomViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pos:Int) {
            binding.userName.text = items[pos].firstName +  " " + items[pos].lastName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdaptor.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CustomViewBinding.inflate(inflater, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder)
        {
            is MyViewHolder -> {
                holder.bind(position)
                holder.itemView.setOnClickListener{
                    itemClickListener.onItemClickListener(items[position])
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}