package com.ciceropinheiro.appgestao.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciceropinheiro.appgestao.adapter.UserAdapter
import com.ciceropinheiro.appgestao.databinding.FragmentDiarioBinding
import com.ciceropinheiro.appgestao.ui.auth.AuthViewModel
import com.example.firebasewithmvvm.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiarioFragment : Fragment() {

    lateinit var binding: FragmentDiarioBinding
    val viewModel: AuthViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiarioBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
    }

    private fun observer() {
        viewModel.getAllUser()
        viewModel.getUserProfile()
        viewModel.users.observe(viewLifecycleOwner) {
            binding.diarioItemPeopleRv.apply {
                this.layoutManager = LinearLayoutManager(requireContext())
                this.adapter = UserAdapter(it)
            }
        }
    }
}