package com.ciceropinheiro.appgestao.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ciceropinheiro.appgestao.data.model.User
import com.ciceropinheiro.appgestao.databinding.FragmentDiarioItemBinding

class UsersViewHolder(
    private val binding: FragmentDiarioItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User) {
        binding.user = user
    }
}