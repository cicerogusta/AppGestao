package com.ciceropinheiro.appgestao.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ciceropinheiro.appgestao.data.model.SignUpUser
import com.ciceropinheiro.appgestao.data.model.User
import com.ciceropinheiro.appgestao.databinding.FragmentHomeBinding
import com.ciceropinheiro.appgestao.ui.auth.AuthViewModel
import com.example.firebasewithmvvm.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()




    }

    private fun observer() {
        authViewModel.getUserProfile()
        authViewModel.user.observe(viewLifecycleOwner) {

            binding.user = it
        }
    }

}