package com.app.mytea.view.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.app.mytea.R
import com.app.mytea.data.datastore.SharedPref
import com.app.mytea.data.model.request.Profile
import com.app.mytea.databinding.FragmentEditProfileBinding
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class EditProfileFragment : Fragment() {
    lateinit var binding : FragmentEditProfileBinding
    private lateinit var viewModel: ProfileViewModel
    lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        var token = "20|XZFCCFWyVVNwPowyzK6eduU5q95YcCTQkHcrdawr"
        val id = arguments?.getString("id")
        sharedPref = SharedPref(requireContext())


        viewModel.allLiveDataDetailUser().observe(viewLifecycleOwner) { userDataList ->
            userDataList?.let {
                if (it.data != null) {
                    var username = it.data!!.username.toString()
                    var name = it.data!!.name.toString()
                    var email = it.data!!.email.toString()

                    binding.editEmail.setText(email)
                    binding.editUsername.setText(username)
                    binding.editNamaLengkap.setText(name)
                }
            }
        }

        var tokens = ""
        sharedPref.getToken.asLiveData().observe(viewLifecycleOwner) { token ->
            when (token) {
                "Undefined" -> Log.d("Token Gagal", "Undefined token $token")
                else -> {
                    Log.d("Token Berhasil", "Get token $token")
                    tokens = token

                }
            }
            viewModel.getDetailDataUser(tokens, id.toString())

        }


        binding.btnBack.setOnClickListener{
            findNavController().navigateUp()
        }

        binding.btnUbahData.setOnClickListener{
            val username = binding.editUsername.text.toString()
            val name = binding.editNamaLengkap.text.toString()
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            val passwordConfirm = binding.editConfirmPassword.text.toString()

            Log.d("TOKEN", tokens)
            Log.d("username", username)
            Log.d("name", name)
            Log.d("email", email)
            Log.d("id", id.toString())
            Log.d("password", password)
            Log.d("password_confirm", passwordConfirm)
            if (password != passwordConfirm){
                Toast.makeText(requireContext(), "Password tidak sama", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.updateUserProfile(token,id!!, Profile(email,name,username,password,passwordConfirm))
                viewModel.allLiveDataUpdateDataUser().observe(viewLifecycleOwner) { userDataList ->
                    userDataList?.let {
                        if (it != null) {
                            Toast.makeText(requireContext(), "Berhasil Update Data", Toast.LENGTH_SHORT).show()
                            findNavController().navigateUp()
                        }else{
                            Toast.makeText(requireContext(), "Gagal Update Data", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

}