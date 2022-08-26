package com.ciceropinheiro.appgestao.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ciceropinheiro.appgestao.data.model.User
import com.ciceropinheiro.appgestao.databinding.FragmentDiarioItemBinding

class UserAdapter(
    private val UsersList: List<User>
) : RecyclerView.Adapter<UsersViewHolder>() {

    private lateinit var binding: FragmentDiarioItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        binding = FragmentDiarioItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val largeNews = UsersList[position]
        holder.bind(largeNews)
    }

    override fun getItemCount(): Int = UsersList.size

}