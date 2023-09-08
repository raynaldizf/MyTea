package com.app.mytea.view.authentication.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.mytea.R
import com.app.mytea.data.model.request.RegisterRequest
import com.app.mytea.databinding.FragmentRegisterBinding
import com.app.mytea.view.authentication.viewmodel.AuthenticationViewModel

class RegisterFragment : Fragment() {
    lateinit var binding : FragmentRegisterBinding
    private lateinit var viewModel: AuthenticationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]

        binding.btnRegister.setOnClickListener{
            postRegister()
        }
        binding.textViewLogin.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    fun postRegister() {
        val email = binding.editTextEmail .text.toString()
        val userName = binding.editTextUserName.text.toString()
        val name = binding.editTextUserName.text.toString()
        val password = binding.editTextPassword.text.toString()
        val passwordConfirm = password
        viewModel.registerUser(RegisterRequest(email, userName, name, password, passwordConfirm))
        if (email.isEmpty() || userName.isEmpty() || name.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            Toast.makeText(context, "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.register().observe(viewLifecycleOwner) {
                if (it != null) {
                    Toast.makeText(context, "Berhasil Register!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Gagal Register!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}