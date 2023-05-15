package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(var list:MutableList<Contact>, var context: Context, var activity: Activity, var contInterface: ContactInterface) : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name:TextView = itemView.findViewById(R.id.name)
        var phone:TextView = itemView.findViewById(R.id.phone)
        var contactlayout:ConstraintLayout = itemView.findViewById(R.id.cont_lay)
        var call:ImageView = itemView.findViewById(R.id.imageView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        var holder = ContactHolder(LayoutInflater.from(parent.context).inflate(R.layout.contact_layout,parent,false))
        return holder
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        var item = list[position]
        holder.name.text = item.name
        holder.phone.text = item.phone
        holder.itemView.setOnClickListener {
            contInterface.onClick(item)
        }
        holder.call.setOnClickListener {
            openCall(item.phone)
        }
    }

    private fun openCall(phone: String) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.CALL_PHONE),1)
        } else {
            if (phone.isNotEmpty()){
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:$phone")
                activity.startActivity(callIntent)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ContactInterface{
        fun onClick(contact: Contact){

        }
    }
}