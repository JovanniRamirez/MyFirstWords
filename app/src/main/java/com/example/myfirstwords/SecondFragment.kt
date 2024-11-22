package com.example.myfirstwords

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.myfirstwords.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.title = "Home Page"

        binding.btnMainToAbc.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_secondFragment_to_thirdFragment)
        }
        binding.btnMainToWords.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_secondFragment_to_fourthFragment)
        }
        binding.btnMainToCustom.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_secondFragment_to_fifthFragment)
        }
        binding.btnMainToSettings.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_secondFragment_to_sixthFragment)
        }
        binding.btnMainToExit.setOnClickListener {
            requireActivity().finish()
        }
    }
}