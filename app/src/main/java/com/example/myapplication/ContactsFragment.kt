package com.example.myapplication

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentContactsBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ContactsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    var contacts = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContactsBinding.inflate(inflater, container, false)
        val mydb = DBHelper(requireContext())


        contacts.add(Contact(name = "Muhammadali Eshonov", phone = "+99897 565 71 73"))
        contacts.add(Contact(name = "Sarvar Abdug’aniyev", phone = "+99897 565 71 73"))
        contacts.add(Contact(name = "Abbos Donaboyev", phone = "+99897 565 71 73"))
        contacts.add(Contact(name = "Asilbek Taksi", phone = "+99897 565 71 73"))
        contacts.add(Contact(name = "Rustam sartarosh", phone = "+99897 565 71 73"))
        contacts.add(Contact(name = "Shovkat qo’shni", phone = "+99897 565 71 73"))
        contacts.add(Contact(name = "Bobur Mavlonov", phone = "+99897 565 71 73"))
        contacts.add(Contact(name = "Abdurahim Qassob", phone = "+99897 565 71 73"))
        contacts.add(Contact(name = "Alisherni akasi", phone = "+99897 565 71 73"))
        contacts.add(Contact(name = "Noma’lum shaxs", phone = "+99897 565 71 73"))



        if (contacts.isEmpty()){
            binding.box.visibility = View.VISIBLE
        }
        else{
            var adapter = ContactAdapter(contacts, object : ContactAdapter.ContactInterface{
                override fun onClick(contact: Contact) {
                    val bundle = bundleOf()
                    bundle.putSerializable("contact",contact)
                    findNavController().navigate(R.id.action_contactsFragment_to_viewFragment,bundle)
                }

            })
            binding.contactRv.adapter = adapter
        }

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_contactsFragment_to_addContactFragment)
        }



        return binding.root
    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}