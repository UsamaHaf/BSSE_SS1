package com.usama.uos.bssess1.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import com.usama.uos.bssess1.Models.UserModel
import com.usama.uos.bssess1.R


class UpdateDataFragment : Fragment() {

   lateinit var userModel: UserModel
   lateinit var edtUpdateUserName: EditText
   lateinit var btnUpdateData: Button
   lateinit var firebaseAuth: FirebaseAuth

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      val view = inflater.inflate(R.layout.fragment_update_data, container, false)

      firebaseAuth = FirebaseAuth.getInstance()
      edtUpdateUserName = view.findViewById(R.id.edtUpdateUserName)
      btnUpdateData = view.findViewById(R.id.btnUpdateData)

      return view
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      val bundle = arguments
      if (bundle != null) {
         userModel = Gson().fromJson(bundle.getString("UserDetails"), UserModel::class.java)

         edtUpdateUserName.setText("${userModel.firstName + userModel.lastName}")


      } else {
         Toast.makeText(requireActivity(), "No Data In Bundle Found", Toast.LENGTH_SHORT).show()
      }

      btnUpdateData.setOnClickListener {
         val strUserName = edtUpdateUserName.text.toString()

         FirebaseDatabase.getInstance().getReference("Users")
            .child(userModel.userID!!).child("lastName").setValue(strUserName)

         setFragment(UserProfileFragment())


      }

   }

   private fun setFragment(fragment: Fragment) {
      requireActivity().supportFragmentManager.beginTransaction()
         .replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()
   }


}