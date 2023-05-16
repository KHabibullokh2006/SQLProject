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

    lateinit var contact: Contact


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditContactBinding.inflate(inflater, container, false)
        val db = DBHelper(requireContext())
        val contact = arguments?.getSerializable("id") as Contact

//        var list:MutableList<Contact> = db.getContacts()
//
//        for (i in list.indices){
//            if (list[i].id == id){
//                contact = list[i]
//            }
//        }

        binding.name.setText(contact.name)
        binding.phone.setText(contact.phone)

        binding.back.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.check.setOnClickListener {
            db.editContact(Contact(
                id = contact.id,
                name = contact.name,
                phone = contact.phone
            ))
            findNavController().popBackStack()
        }

        return binding.root
    }


}