package com.app.mytea.view.authentication.login

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.mytea.R
import com.app.mytea.data.datastore.SharedPref
import com.app.mytea.data.model.request.LoginRequest
import com.app.mytea.databinding.FragmentLoginBinding
import com.app.mytea.view.authentication.viewmodel.AuthenticationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var sharedPref: SharedPref
    private var remember = false
    lateinit var binding : FragmentLoginBinding
    lateinit var viewModel : AuthenticationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireContext())
        viewModel = ViewModelProvider(requireActivity()).get(AuthenticationViewModel::class.java)

        switchCheck()

        binding.textViewRegister.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener{
            login()
        }
    }

    private fun saveSession(token : String) {
        lifecycleScope.launch(Dispatchers.IO) {
            sharedPref.saveLog(token)
        }
    }

    private fun login(){
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val request = LoginRequest(email,password)
        viewModel.loginUser(request)
        viewModel.login().observe(viewLifecycleOwner){
            if (it != null){
                if (it.success == true){
                    Toast.makeText(context,"Login Berhasil!",Toast.LENGTH_SHORT).show()
                    if (remember){
                        saveSession(it.data.toString())
                        //findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        // Log Token & remember
                        Log.d("token", "onViewCreated: ${it.data}")
                        Log.d("remember", "onViewCreated: $remember")
                    }else{
                        Log.d("token", "onViewCreated: ${it.data}")
                        Log.d("remember", "onViewCreated: $remember")
                        //findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                    }
                }else{
                    Toast.makeText(context,"Email atau password salah!",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun switchCheck() : Boolean{
        val mySwitch = binding.jamaluding

        mySwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mySwitch.thumbTintList = ColorStateList.valueOf(Color.WHITE)
                mySwitch.trackTintList = ColorStateList.valueOf(Color.parseColor("#A2AA7B"))
                remember = true
            } else {
                mySwitch.thumbTintList = ColorStateList.valueOf(Color.parseColor("#808080"))
                mySwitch.trackTintList = ColorStateList.valueOf(Color.GRAY)
                remember = false
            }
            Log.d("remember", "onViewCreated: $remember")
        }
        return remember
    }
}