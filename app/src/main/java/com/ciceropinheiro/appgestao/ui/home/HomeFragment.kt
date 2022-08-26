package com.ciceropinheiro.appgestao.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ciceropinheiro.appgestao.data.model.User
import com.ciceropinheiro.appgestao.databinding.FragmentHomeBinding
import com.ciceropinheiro.appgestao.ui.auth.AuthViewModel
import com.ciceropinheiro.appgestao.ui.auth.LoginFragmentDirections
import com.example.firebasewithmvvm.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: AuthViewModel by viewModels()

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


        binding.imageButtonDiario.setOnClickListener {
            callFragment()
        }





    }

    private fun observer() {
        viewModel.getUserProfile()
        viewModel.user.observe(viewLifecycleOwner) {

            binding.user = it
            if (it == null) {
                toast("ERRO")
            }
        }
    }

    fun callFragment() {

        val action = HomeFragmentDirections.actionHomeFragmentToDiarioFragment()


        findNavController().navigate(action)
    }

}