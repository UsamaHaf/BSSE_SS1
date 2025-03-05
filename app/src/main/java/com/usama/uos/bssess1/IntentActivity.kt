package com.usama.uos.bssess1

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.net.URLEncoder

class IntentActivity : AppCompatActivity() {

   lateinit var btnMakePhoneCall: Button
   lateinit var btnSearchWeb: Button
   lateinit var btnSearchGoogle: Button
   lateinit var btnSendMessage: Button
   lateinit var edtPhoneNumber: EditText
   lateinit var edtWebSearch: EditText
   lateinit var edtGoogleSearch: EditText
   lateinit var edtMsgPhoneNo: EditText
   lateinit var edtMsgText: EditText

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_intent)

      btnMakePhoneCall = findViewById(R.id.btnMakePhoneCall)
      btnSearchWeb = findViewById(R.id.btnSearchWeb)
      btnSendMessage = findViewById(R.id.btnSendMessage)
      btnSearchGoogle = findViewById(R.id.btnSearchGoogle)
      edtPhoneNumber = findViewById(R.id.edtCallPhoneNo)
      edtWebSearch = findViewById(R.id.edtWebSearch)
      edtGoogleSearch = findViewById(R.id.edtGoogleSearch)
      edtMsgPhoneNo = findViewById(R.id.edtMsgPhoneNo)
      edtMsgText = findViewById(R.id.edtMsgText)

      btnSendMessage.setOnClickListener {
         val strPhoneNo = edtMsgPhoneNo.text.toString()
         val strMessageText = edtMsgText.text.toString()

         sendSMS(strPhoneNo , strMessageText)


      }

      btnSearchWeb.setOnClickListener {
         val strWebSearch = edtWebSearch.text.toString()
         val intent = Intent(Intent.ACTION_VIEW)
         intent.setData(Uri.parse("https://www.${strWebSearch}.com/"))
         startActivity(intent)
      }

      btnSearchGoogle.setOnClickListener {
         val strGoogleSearch = edtGoogleSearch.text.toString()

         val query = URLEncoder.encode(strGoogleSearch, "UTF-8")
         val uri = Uri.parse("https://www.google.com/search?q=${query}")
         val intent = Intent(Intent.ACTION_VIEW, uri)
         startActivity(intent)
      }

      btnMakePhoneCall.setOnClickListener {
         if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), 10)
         } else {
            val strPhoneNumber = edtPhoneNumber.text.toString()

            if (strPhoneNumber.isEmpty()) {
               Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show()
            } else {
               val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + strPhoneNumber))
               startActivity(intent)
            }


         }

      }


   }

   private fun sendSMS(strPhoneNo: String, strMessageText: String) {
      val sentPI: PendingIntent =PendingIntent.getBroadcast(this, 0, Intent("SMS_SENT"), PendingIntent.FLAG_IMMUTABLE)
      SmsManager.getDefault().sendTextMessage(strPhoneNo, null, strMessageText, sentPI, null)

      edtMsgText.text.equals("")

   }
}