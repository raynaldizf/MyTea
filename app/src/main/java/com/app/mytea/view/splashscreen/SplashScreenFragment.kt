package com.app.mytea.view.splashscreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import com.app.mytea.R
import com.app.mytea.data.datastore.SharedPref
import com.app.mytea.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    private lateinit var sharedPref: SharedPref

    lateinit var binding : FragmentSplashScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = SharedPref(requireContext())

        startSplashScreen()
    }

    private fun startSplashScreen() {
        sharedPref.getToken.asLiveData().observe(viewLifecycleOwner){
            Handler(Looper.getMainLooper()).postDelayed({
                if (it.equals("Undefined")){
                    Log.d("token pas function gettoken", it)
                    Navigation.findNavController(requireView()).navigate(R.id.action_splashScreenFragment_to_loginFragment)
                }else{
                    Navigation.findNavController(requireView()).navigate(R.id.action_splashScreenFragment_to_homeFragment)
                }
            }, 1000)
        }
    }

}