package com.app.mytea.view.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.app.mytea.R
import com.app.mytea.data.datastore.SharedPref
import com.app.mytea.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    lateinit var localization: Spinner
    private lateinit var viewModel: ProfileViewModel
    lateinit var sharedPref: SharedPref
    private var userId: String? = null // Initialize with a default value or null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        sharedPref = SharedPref(requireContext())

        viewModel.allLiveDataUser().observe(viewLifecycleOwner) { userDataList ->
            userDataList?.let {
                if (it.isNotEmpty()) {
                    userId = it[0].id.toString()
                    var username = it[0].username.toString()
                    var name = it[0].name.toString()
                    var email = it[0].email.toString()

                    binding.email.text = email
                    binding.username.text = username
                    binding.name.text = name
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
            viewModel.getDataUser(tokens)

        }

        localization = binding.languageSpinner
        val language = arrayOf("English", "Indonesia")
        val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, language)
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        localization.adapter = adapter

        localization.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedLanguage = language[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        savedInstanceState?.let {
            val selectedPosition = it.getInt("selected_position", -1)
            if (selectedPosition != -1) {
                localization.setSelection(selectedPosition)
            }
        }

        binding.btnEdit.setOnClickListener{
            if (userId != null) {
                val bundle = Bundle()
                bundle.putString("id", userId)
                findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment, bundle)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("selected_position", localization.selectedItemPosition)
    }
}
