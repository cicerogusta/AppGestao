package com.ciceropinheiro.appgestao.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ciceropinheiro.appgestao.R
import com.ciceropinheiro.appgestao.databinding.FragmentProfessorBinding
import com.ciceropinheiro.appgestao.ui.auth.AuthViewModel
import com.example.firebasewithmvvm.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfessorFragment : Fragment() {

    lateinit var binding: FragmentProfessorBinding
    val viewModel: AuthViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfessorBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
    }

    private fun observer() {
        viewModel.getUserProfile()
        viewModel.user.observe(viewLifecycleOwner) {

            binding.user = it
        }
    }
}