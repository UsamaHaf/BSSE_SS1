package com.usama.uos.bssess1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.usama.uos.bssess1.SharedPref.MySharedPreferences

class LoginActivity : AppCompatActivity() {

   lateinit var sharedPreferences: MySharedPreferences
   lateinit var loginEmail: EditText
   lateinit var loginPassword: EditText
   lateinit var btnLoginUser: Button
   lateinit var btnNewAccount: Button
   lateinit var auth: FirebaseAuth

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_login)

      auth = FirebaseAuth.getInstance()
      sharedPreferences = MySharedPreferences(this@LoginActivity)

      loginEmail = findViewById(R.id.loginEmail)
      loginPassword = findViewById(R.id.loginPassword)
      btnLoginUser = findViewById(R.id.btnLoginUser)
      btnNewAccount = findViewById(R.id.btnNewAccount)
      btnNewAccount?.setOnClickListener {
         startActivity(Intent(this@LoginActivity , SignupActivity::class.java))
      }

      btnLoginUser.setOnClickListener {
         val strEmail = loginEmail.text.toString()
         val strPassword = loginPassword.text.toString()

         if (strEmail.isEmpty()) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
         } else if (strPassword.isEmpty()) {
            Toast.makeText(this, "Enter Password Must Be 6 digit", Toast.LENGTH_SHORT).show()
         } else {

            signInUser(strEmail, strPassword)

            /*sharedPreferences.saveEmail("UserEmail" , strEmail)
            sharedPreferences.saveIsLoggedIn("UserLoginStatus" , "True")*/


         }


      }


   }

   private fun signInUser(strEmail: String, strPassword: String) {

      auth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener { task ->
         if (task.isSuccessful) {
            startActivity(Intent(this@LoginActivity, HomePageActivity::class.java))
         } else {
            Toast.makeText(this@LoginActivity, "Login Failed:- ${task.exception}", Toast.LENGTH_SHORT)
               .show()
         }
      }
   }
}