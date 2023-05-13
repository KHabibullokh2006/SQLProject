package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentEditContactBinding


class EditContactFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditContactBinding.inflate(inflater, container, false)
//        val contact = arguments?.getSerializable("contact") as Contact
//
//        Log.d("TAG", contact.name)
//
//        binding.name.setText(contact.name)
//        binding.phone.setText(contact.name)

        binding.back.setOnClickListener{
            findNavController().navigate(R.id.action_editContactFragment_to_viewFragment)
        }

        return binding.root
    }


}