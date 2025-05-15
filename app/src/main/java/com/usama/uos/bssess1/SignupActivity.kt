package com.usama.uos.bssess1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.usama.uos.bssess1.Models.UserModel

class SignupActivity : AppCompatActivity() {

   private lateinit var emailAddress: EditText
   private lateinit var phoneNumber: EditText
   private lateinit var lastName: EditText
   private lateinit var firstName: EditText
   private lateinit var password: EditText
   private lateinit var signup: Button
   private lateinit var btnAlreadyExist: TextView
   private lateinit var firebaseAuth: FirebaseAuth
   private lateinit var pbSignUp: ProgressBar

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_signup)

      firebaseAuth = FirebaseAuth.getInstance()

      btnAlreadyExist = findViewById(R.id.btnAlreadyExist)
      emailAddress = findViewById(R.id.edtEmailAddress)
      phoneNumber = findViewById(R.id.edtPhoneNo)
      lastName = findViewById(R.id.edtLastName)
      firstName = findViewById(R.id.edtFirstName)
      password = findViewById(R.id.edtPassword)
      signup = findViewById(R.id.btnSignUp)
      pbSignUp = findViewById(R.id.pbSignUp)

      btnAlreadyExist.setOnClickListener {
         startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
      }

      signup.setOnClickListener {
         val strEmail = emailAddress.text.toString()
         val strPhoneNo = phoneNumber.text.toString()
         val strFName = firstName.text.toString()
         val strLName = lastName.text.toString()
         val strPassword = password.text.toString()

         pbSignUp.visibility = ProgressBar.VISIBLE
         signUpUser(strEmail, strPhoneNo, strFName, strLName, strPassword)


      }


   }

   private fun signUpUser(strEmail: String, strPhoneNo: String, strFName: String, strLName: String, strPassword: String) {

      if (strEmail.isEmpty()) {
         Toast.makeText(this@SignupActivity, "Enter EmailAddress", Toast.LENGTH_SHORT).show()

      } else if (strPhoneNo.isEmpty()) {
         Toast.makeText(this@SignupActivity, "Enter Phone Number", Toast.LENGTH_SHORT).show()

      } else if (strFName.isEmpty()) {
         Toast.makeText(this@SignupActivity, "Enter First Name", Toast.LENGTH_SHORT).show()

      } else if (strLName.isEmpty()) {
         Toast.makeText(this@SignupActivity, "Enter Last Name", Toast.LENGTH_SHORT).show()

      } else if (strPassword.isEmpty()) {
         Toast.makeText(this@SignupActivity, "Enter Password", Toast.LENGTH_SHORT).show()

      } else {

         signUpFirebaseUser(strEmail, strPassword, strPhoneNo, strFName, strLName)


      }

   }

   private fun signUpFirebaseUser(strEmail: String, strPassword: String, strPhoneNo: String, strFName: String, strLName: String) {

      firebaseAuth.createUserWithEmailAndPassword(strEmail, strPassword)
         .addOnCompleteListener { task ->

            if (task.isSuccessful) {

               try {

                  val userModel = UserModel()
                  userModel.userEmailAddress = strEmail
                  userModel.firstName = strFName
                  userModel.lastName = strLName
                  userModel.userPhoneNo = strPhoneNo
                  userModel.userPassword = strPassword
                  userModel.userID = firebaseAuth.currentUser?.uid

                  FirebaseDatabase.getInstance().getReference("Users")
                     .child(firebaseAuth.currentUser!!.uid).setValue(userModel)
                     .addOnCompleteListener { addTask ->

                        if(addTask.isSuccessful){
                           pbSignUp.visibility = ProgressBar.GONE

                           startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                           Toast.makeText(this@SignupActivity, "Signup Successful BSSE SS1", Toast.LENGTH_SHORT)
                              .show()
                        }else{
                           Toast.makeText(this@SignupActivity, "Error: ${addTask.exception}", Toast.LENGTH_SHORT)
                              .show()
                        }


                     }


               } catch (e: Exception) {
                  pbSignUp.visibility = ProgressBar.GONE
                  Log.e("DBAddException", "Error:- $e")
               }





            } else {
               pbSignUp.visibility = ProgressBar.GONE
               Toast.makeText(this@SignupActivity, "SignInFailed: ${task.exception}", Toast.LENGTH_SHORT)
                  .show()
            }
         }
   }

}