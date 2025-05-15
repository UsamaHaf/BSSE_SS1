package com.usama.uos.bssess1.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.usama.uos.bssess1.R


class UserProfileFragment : Fragment() {


   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      val view = inflater.inflate(R.layout.fragment_user_profile, container, false)




      return view

   }


}


/*
      lateinit var getEmail:TextView
      lateinit var sharedPreferences: MySharedPreferences
      sharedPreferences = MySharedPreferences(requireActivity())
      getEmail = view.findViewById(R.id.getEmail)

      getEmail.text = sharedPreferences.getEmail("UserEmail")*/