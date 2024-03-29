package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentViewBinding
import java.security.Permission
import java.util.jar.Manifest

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var contact: Contact

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
        val binding = FragmentViewBinding.inflate(inflater, container, false)
        val db = DBHelper(requireContext())
        val id = arguments?.getInt("contact")?.toInt()


        var list:MutableList<Contact> = db.getContacts()


        for (i in list.indices){
            if (list[i].id == id){
                contact = list[i]
                Log.d("AAA", "I found a contact")
            }
        }


        binding.name.text = contact.name
        binding.phone.text = contact.phone

        binding.delete.setOnClickListener {
            val myDialog = Dialog(contact)
            val manager = parentFragmentManager
            myDialog.show(manager,"myDialog")
            findNavController().navigate(R.id.action_viewFragment_to_contactsFragment)
        }

        binding.edit.setOnClickListener {
            val id = bundleOf("id" to contact)
            findNavController().navigate(R.id.action_viewFragment_to_editContactFragment,id)
        }

        binding.call.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CALL_PHONE),1)
            }
            else{
                if (binding.phone.text.toString() != ""){
                    val call = Intent(Intent.ACTION_CALL)
                    call.data = Uri.parse("tel:${binding.phone.text}")
                    requireActivity().startActivity(call)
                }
            }
        }



        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}