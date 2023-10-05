package com.app.mytea.view.home.tea.teaview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.mytea.databinding.FragmentControllTeaVpBinding
import com.app.mytea.view.home.tea.TeaViewModel

class ControllTeaVpFragment : Fragment() {
    lateinit var binding : FragmentControllTeaVpBinding
    private lateinit var viewModel : TeaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentControllTeaVpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TeaViewModel::class.java)
        val fertilizerId = arguments?.getString("id")
        val tokensId = arguments?.getString("token")
        viewModel.allLiveDataDetailFertilizer().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.textControl.text = it.howToControll
            }
        }
        viewModel.getDetailFertilizer(tokensId!!, fertilizerId.toString())
    }

}