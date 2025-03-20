package com.usama.uos.bssess1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

   lateinit var txtNum1: EditText
   lateinit var txtNum2: EditText
   lateinit var textResults: TextView

   lateinit var btnPlus: Button

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_calculator)

      txtNum1 = findViewById(R.id.txtNum1)
      txtNum2 = findViewById(R.id.txtNum2)
      textResults = findViewById(R.id.textResults)
      btnPlus = findViewById(R.id.btnPlus)


      btnPlus.setOnClickListener {

         val strNum1 = txtNum1.text.toString().toDouble()
         val strNum2 = txtNum2.text.toString().toDouble()

         val result = strNum1 + strNum2
         textResults.text = result.toString()


      }


   }
}