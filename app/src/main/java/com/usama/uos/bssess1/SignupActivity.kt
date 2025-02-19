package com.usama.uos.bssess1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

   private lateinit var emailAddress: EditText
   private lateinit var phoneNumber: EditText
   private lateinit var lastName: EditText
   private lateinit var firstName: EditText
   private lateinit var password: EditText
   private lateinit var signup: Button

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_signup)

      emailAddress = findViewById(R.id.edtEmailAddress)
      phoneNumber = findViewById(R.id.edtPhoneNo)
      lastName = findViewById(R.id.edtLastName)
      firstName = findViewById(R.id.edtFirstName)
      password = findViewById(R.id.edtPassword)
      signup = findViewById(R.id.btnSignUp)

      signup.setOnClickListener {
         val strEmail = emailAddress.text.toString()
         val strPhoneNo = phoneNumber.text.toString()
         val strFName = firstName.text.toString()
         val strLName = lastName.text.toString()
         val strPassword = password.text.toString()

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

         startActivity(Intent(this@SignupActivity , LoginActivity::class.java))

         Toast.makeText(this@SignupActivity, "Signup Successfull BSSE SS1", Toast.LENGTH_SHORT)
            .show()

      }

   }

}