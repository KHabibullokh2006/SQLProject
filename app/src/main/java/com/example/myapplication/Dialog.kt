package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.core.graphics.toColorInt
import androidx.fragment.app.DialogFragment

class Dialog(var contact: Contact) : DialogFragment() {


    @SuppressLint("ResourceType")
    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        var db = DBHelper(requireContext())
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Delete contact")
                .setMessage("Are you sure you want to remove this contact from your contacts?")
                .setCancelable(true)
                .setPositiveButton("Yes") { _, _ ->
                    db.deleteContact(contact)
                }
                .setNegativeButton(
                    "No"
                ) { _, _ ->

                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}