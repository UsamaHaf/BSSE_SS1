package com.usama.uos.bssess1.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.usama.uos.bssess1.Apdater.UserAdapter
import com.usama.uos.bssess1.Interfaces.UserItemClick
import com.usama.uos.bssess1.Models.UserModel
import com.usama.uos.bssess1.R


class UserProfileFragment : Fragment(), UserItemClick {

   lateinit var rvUsers: RecyclerView
   lateinit var pbUserProfile: ProgressBar
   lateinit var noDataExit: TextView
   lateinit var userDatabase: FirebaseDatabase
   lateinit var firebaseRef: DatabaseReference
   lateinit var arrayListUsers: ArrayList<UserModel>
   lateinit var userAdapter: UserAdapter

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      val view = inflater.inflate(R.layout.fragment_user_profile, container, false)

      rvUsers = view.findViewById(R.id.rvUsers)
      pbUserProfile = view.findViewById(R.id.pbUserProfile)
      noDataExit = view.findViewById(R.id.noDataExit)

      getUsers()
      return view

   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
   }


   private fun getUsers() {
      pbUserProfile.visibility = ProgressBar.VISIBLE

      userDatabase = FirebaseDatabase.getInstance()
      firebaseRef = userDatabase.getReference("Users")
      arrayListUsers = ArrayList()

      firebaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
         override fun onDataChange(snapshot: DataSnapshot) {
            try {

               for (eachUser in snapshot.children) {
                  val key = eachUser.key.toString()

                  val userModel: UserModel? = eachUser.getValue(UserModel::class.java)
                  userModel?.userID = key

                  arrayListUsers.add(userModel!!)

               }
               if (arrayListUsers == null || arrayListUsers.size == 0) {
                  noDataExit.visibility = TextView.VISIBLE
                  pbUserProfile.visibility = ProgressBar.GONE

               } else {
                  userAdapter =
                      UserAdapter(arrayListUsers, requireActivity(), this@UserProfileFragment)
                  rvUsers.adapter = userAdapter
                  pbUserProfile.visibility = ProgressBar.GONE

               }

            } catch (e: Exception) {
               Log.e("DB Error", "Failed: ${e}")

            }
         }

         override fun onCancelled(error: DatabaseError) {
            Toast.makeText(requireActivity(), "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
            Log.e("DB Error", "Failed: ${error.message}")
         }

      })

   }

   override fun userItemClick(v: View, model: UserModel, position: Int) {

      val bundle = Bundle()
      bundle.putString("UserDetails", Gson().toJson(model))
      val nextFragment = UpdateDataFragment()
      nextFragment.arguments = bundle
      setFragment(nextFragment)


   }

   private fun setFragment(fragment: Fragment) {
      requireActivity().supportFragmentManager.beginTransaction()
         .replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()
   }


}


/*
      lateinit var getEmail:TextView
      lateinit var sharedPreferences: MySharedPreferences
      sharedPreferences = MySharedPreferences(requireActivity())
      getEmail = view.findViewById(R.id.getEmail)

      getEmail.text = sharedPreferences.getEmail("UserEmail")*/