package com.ciceropinheiro.appgestao.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.ciceropinheiro.appgestao.databinding.FragmentLoginBinding
import com.ciceropinheiro.appgestao.util.UiState
import com.example.firebasewithmvvm.util.hide
import com.example.firebasewithmvvm.util.isValidEmail
import com.example.firebasewithmvvm.util.show
import com.example.firebasewithmvvm.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    val viewModel: AuthViewModel by viewModels()
    lateinit var navigation : NavDirections

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        setListeners()
//        viewModel.registerUser("teste@gmail.com", "12345678")
//        viewModel.registerUser("creuza@gmail.com", "12345678")
//        viewModel.registerUser("antonio@gmail.com", "12345678")

//        binding.forgotPassLabel.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
//        }
//
//        binding.registerLabel.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
//        }
    }

    private fun setListeners() {
        binding.imageButtonTwitter.setOnClickListener {
            viewModel.startTwitter(requireContext())
        }
        binding.imageButtonInsta.setOnClickListener {
            viewModel.startInstagram(requireContext())
        }
        binding.imageButtonFace.setOnClickListener {
            viewModel.startFacebook(requireContext())
        }
        binding.imageButtonEntrar.setOnClickListener {
            if (validation()) {
                viewModel.login(
                    email = binding.usernameInput.text.toString(),
                    password = binding.pass.text.toString()
                )



            }
        }
    }

    fun observer(){
    viewModel.login.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.loginProgress.show()
                }
                is UiState.Failure -> {
                    binding.loginProgress.hide()
                    toast(state.error)
                }
                is UiState.Success -> {
                    binding.loginProgress.hide()
                    toast(state.data)
                    observeUser()
                }
            }
        }
    }

    private fun observeUser() {
        viewModel.getUserProfile()
        viewModel.user.observe(viewLifecycleOwner) {
            if(it.nivelUsuario == "Coordenador") {
                callFragment(true)
            } else {
                callFragment(false)

            }
        }
    }

    fun callFragment(nivelUsuario: Boolean) {

        navigation = if (nivelUsuario) {
            LoginFragmentDirections.actionLoginFragmentToCoordenadorFragment()
        } else {
            LoginFragmentDirections.actionLoginFragmentToProfessorFragment()
        }
        findNavController().navigate(navigation)
    }

    fun validation(): Boolean {
        var isValid = true

        if (binding.usernameInput.text.isNullOrEmpty()){
            isValid = false
            toast("Email n??o pode estar vazio!")
        }else{
            if (!binding.usernameInput.text.toString().isValidEmail()){
                isValid = false
                toast("Insira um email valido")
            }
        }
        if (binding.pass.text.isNullOrEmpty()){
            isValid = false
            toast("Senha n??o pode estar vazia")
        }else{
            if (binding.pass.text.toString().length < 8){
                isValid = false
                toast("A senha deve conter 8 caracteres")
            }
        }
        return isValid
    }

}