package com.usama.uos.bssess1.Apdater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.usama.uos.bssess1.Interfaces.GmailInterface
import com.usama.uos.bssess1.Models.GmailModel
import com.usama.uos.bssess1.R

class GmailAdapter(private var gmailArrayList: ArrayList<GmailModel>, activity: Context?, private var interfaceGmail: GmailInterface) :
   RecyclerView.Adapter<GmailAdapter.MyGmailViewHolder>() {


   val inflater: LayoutInflater = LayoutInflater.from(activity)

   inner class MyGmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val userDP: ImageView = itemView.findViewById(R.id.userDP)
      val txtUserName: TextView = itemView.findViewById(R.id.txtUserName)

      val mainItemLayout: RelativeLayout = itemView.findViewById(R.id.mainItemLayout)

   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGmailViewHolder {
      val view = inflater.inflate(R.layout.gmail_list_items, null)
      return MyGmailViewHolder(view)
   }

   override fun getItemCount(): Int {
      return gmailArrayList.size
   }

   override fun onBindViewHolder(holder: MyGmailViewHolder, position: Int) {
      val model = gmailArrayList[position]
      holder.userDP.setImageResource(model.userDP)
      holder.txtUserName.text = model.txtUserName

      holder.mainItemLayout.setOnClickListener { v ->
         interfaceGmail.gmailCLickListener(v, model, position)
      }

   }

}