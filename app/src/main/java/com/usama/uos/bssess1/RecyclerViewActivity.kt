package com.usama.uos.bssess1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.usama.uos.bssess1.Apdater.GmailAdapter
import com.usama.uos.bssess1.Models.GmailModel

class RecyclerViewActivity : AppCompatActivity() {

   lateinit var rvGmail:RecyclerView
   lateinit var gmailArrayList:ArrayList<GmailModel>

   var userNameArray = arrayOf("Kashif" , "CR BSSE" , "BSSE SS1")
   var userDP = arrayOf(R.drawable.background, R.drawable.baseline_6_ft_apart_24 , R.drawable.baseline_access_time_filled_24)

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

         val adapter = GmailAdapter(gmailArrayList , this@RecyclerViewActivity)
         rvGmail.adapter = adapter

      }



   }
}