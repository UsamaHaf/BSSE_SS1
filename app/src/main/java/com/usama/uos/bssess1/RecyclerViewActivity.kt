package com.usama.uos.bssess1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.usama.uos.bssess1.Apdater.GmailAdapter
import com.usama.uos.bssess1.Interfaces.GmailInterface
import com.usama.uos.bssess1.Models.GmailModel

class RecyclerViewActivity : AppCompatActivity(), GmailInterface {

   lateinit var rvGmail:RecyclerView
   lateinit var gmailArrayList:ArrayList<GmailModel>

   var userNameArray = arrayOf("Kashif" , "CR BSSE" , "BSSE SS1","Usman " , "SE Dept" , "Sir Summair")
   var userDP = arrayOf(R.drawable.background, R.drawable.baseline_6_ft_apart_24 , R.drawable.baseline_access_time_filled_24, R.drawable.background, R.drawable.baseline_6_ft_apart_24 , R.drawable.baseline_access_time_filled_24)

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_recycler_view)
      rvGmail = findViewById(R.id.mainRecyclerView)

      gmailArrayList = ArrayList()

      for (i in userDP.indices){
         val modelGmail = GmailModel(userDP[i] , userNameArray[i])
         gmailArrayList.add(modelGmail)
      }

      if(gmailArrayList != null){
         val adapter = GmailAdapter(gmailArrayList , this@RecyclerViewActivity , this)
         rvGmail.adapter = adapter

      }
   }

   override fun gmailCLickListener(view: View?, gmailModel: GmailModel, position: Int) {

      val intent = Intent(this@RecyclerViewActivity , HomePageActivity::class.java)
      intent.putExtra("TestData" , gmailModel.txtUserName)
      startActivity(intent)


   }
}