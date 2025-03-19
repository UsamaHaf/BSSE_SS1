package com.usama.uos.bssess1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomePageActivity : AppCompatActivity() {

   lateinit var txtWelcomeText:TextView

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_home_page)

      txtWelcomeText = findViewById(R.id.txtWelcomeText)

      val receivedData = intent.getStringExtra("TestData")

      if(receivedData != null){
         txtWelcomeText.text = "Welcome Mr. $receivedData"

      }else{
         txtWelcomeText.text = "No data found"

      }



   }
}