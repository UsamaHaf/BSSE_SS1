package com.usama.uos.bssess1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.usama.uos.bssess1.SharedPref.MySharedPreferences

class LoginActivity : AppCompatActivity() {

   lateinit var sharedPreferences:MySharedPreferences
   lateinit var loginEmail:EditText
   lateinit var btnLoginUser:Button

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_login)

      sharedPreferences = MySharedPreferences(this@LoginActivity)

      loginEmail = findViewById(R.id.loginEmail)
      btnLoginUser = findViewById(R.id.btnLoginUser)

      btnLoginUser.setOnClickListener {
         val strEmail = loginEmail.text.toString()

         if(strEmail.isEmpty()){
            Toast.makeText(this , "Enter Email" , Toast.LENGTH_SHORT).show()
         }else{

            sharedPreferences.saveEmail("UserEmail" , strEmail)
            sharedPreferences.saveIsLoggedIn("UserLoginStatus" , "True")

            startActivity(Intent(this@LoginActivity , HomePageActivity::class.java))

         }



      }




   }
}