package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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
        val db = DBHelper(requireContext())
        contacts = db.getContacts()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContactsBinding.inflate(inflater, container, false)




        if (contacts.isEmpty()){
            binding.box.visibility = View.VISIBLE
        }
        else{
            var adapter = ContactAdapter(contacts, requireContext(), requireActivity(), object : ContactAdapter.ContactInterface{
                override fun onClick(contact: Contact) {
                    val bundle = bundleOf("contact" to contact.id)
                    findNavController().navigate(R.id.action_contactsFragment_to_viewFragment,bundle)
                }

            })
            binding.contactRv.adapter = adapter
        }

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_contactsFragment_to_addContactFragment)
        }

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.search ->{
                    object  : SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            var filter = mutableListOf<Contact>()
                            for (item in contacts){
                                if (newText != null) {
                                    if (item.name.toLowerCase().contains(newText.toLowerCase())){
                                        filter.add(item)
                                    }
                                }
                            }
                            if (filter.isEmpty()){
                                Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
                            }else{
                                var adapter = ContactAdapter(filter, requireContext(), requireActivity(), object : ContactAdapter.ContactInterface{
                                    override fun onClick(contact: Contact) {
                                        val bundle = bundleOf("contact" to contact.id)
                                        findNavController().navigate(R.id.action_contactsFragment_to_viewFragment,bundle)
                                    }

                                })
                                binding.contactRv.adapter = adapter
                            }
                            return true
                        }

                    }

                }
            }
            true
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