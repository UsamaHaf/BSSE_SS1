package com.usama.uos.bssess1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.usama.uos.bssess1.SharedPref.MySharedPreferences

class SplashScreenActivity : AppCompatActivity() {

   lateinit var sharedPreferences: MySharedPreferences
   lateinit var firebaseAuth: FirebaseAuth

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_splash_screen)

      firebaseAuth = FirebaseAuth.getInstance()
      FirebaseApp.initializeApp(this@SplashScreenActivity)

      sharedPreferences = MySharedPreferences(this)

      Handler().postDelayed(Runnable {

         if(firebaseAuth.currentUser != null){
            startActivity(Intent(this@SplashScreenActivity, HomePageActivity::class.java))

         }else{
            startActivity(Intent(this@SplashScreenActivity, SignupActivity::class.java))
         }
      }, 3000)

      /*Handler().postDelayed(Runnable {

         if(sharedPreferences.getIsLoggedIn("UserLoginStatus") == "True"){
            startActivity(Intent(this@SplashScreenActivity , HomePageActivity::class.java))
         }else{
            startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
         }
      }, 3000)*/
   }
}