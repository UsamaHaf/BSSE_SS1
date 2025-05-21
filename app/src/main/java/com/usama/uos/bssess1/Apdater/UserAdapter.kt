package com.usama.uos.bssess1.Apdater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.usama.uos.bssess1.Interfaces.UserItemClick
import com.usama.uos.bssess1.Models.UserModel
import com.usama.uos.bssess1.R

class UserAdapter(private var userArrayList: ArrayList<UserModel>, context: Context, var userItemClick: UserItemClick) :
   RecyclerView.Adapter<UserAdapter.MyUserViewHolder>() {
   private var inflator: LayoutInflater = LayoutInflater.from(context)


   inner class MyUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val txtUserName: TextView = itemView.findViewById(R.id.txtUserName)
      val txtUserEmail: TextView = itemView.findViewById(R.id.txtUserEmail)
      val mainLayout: RelativeLayout = itemView.findViewById(R.id.mainLayout)
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyUserViewHolder {
      val view = inflator.inflate(R.layout.list_items_users, null)
      return MyUserViewHolder(view)
   }

   override fun getItemCount(): Int {
      return userArrayList.size
   }

   override fun onBindViewHolder(holder: MyUserViewHolder, position: Int) {
      val model = userArrayList[position]
      holder.txtUserEmail.text = model.userEmailAddress
      holder.txtUserName.text = model.firstName + model.lastName

      holder.mainLayout.setOnClickListener { v->
         userItemClick.userItemClick(v ,  model , position)
      }

   }

}