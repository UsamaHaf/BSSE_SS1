package com.usama.uos.bssess1.SharedPref

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context:Context) {

   private val sharedPreferences:SharedPreferences = context.getSharedPreferences("SS1Pref" , Context.MODE_PRIVATE)

   fun saveEmail(key:String , value:String){
      val editor = sharedPreferences.edit()
      editor.putString(key,value)
      editor.apply()
   }

   fun getEmail(key:String):String? {
      return  sharedPreferences.getString(key , "")
   }

   fun saveIsLoggedIn(key:String , value: String){
      val editor = sharedPreferences.edit()
      editor.putString(key , value)
      editor.apply()
   }


   fun getIsLoggedIn(key:String):String? {
      return  sharedPreferences.getString(key , "")
   }





}