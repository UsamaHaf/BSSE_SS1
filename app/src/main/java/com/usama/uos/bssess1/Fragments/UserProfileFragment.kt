package com.usama.uos.bssess1.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.usama.uos.bssess1.R
import com.usama.uos.bssess1.SharedPref.MySharedPreferences


class UserProfileFragment : Fragment() {

   lateinit var getEmail:TextView
   lateinit var sharedPreferences: MySharedPreferences

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      val view = inflater.inflate(R.layout.fragment_user_profile, container, false)

      sharedPreferences = MySharedPreferences(requireActivity())
      getEmail = view.findViewById(R.id.getEmail)

      getEmail.text = sharedPreferences.getEmail("UserEmail")


      return view

   }


}