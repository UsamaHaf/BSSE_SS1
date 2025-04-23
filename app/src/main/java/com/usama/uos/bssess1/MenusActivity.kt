package com.usama.uos.bssess1

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MenusActivity : AppCompatActivity() {

   lateinit var btnContextMenu: CardView
   lateinit var btnOptionMenu: CardView
   lateinit var datePicker: DatePicker
   lateinit var timePicker: TimePicker
   lateinit var txtSelectedDate: TextView

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_menus)

      btnContextMenu = findViewById(R.id.btnContextMenu)
      btnOptionMenu = findViewById(R.id.btnOptionMenu)
      datePicker = findViewById(R.id.datePicker)
      timePicker = findViewById(R.id.timePicker)
      txtSelectedDate = findViewById(R.id.txtSelectedDate)

      txtSelectedDate.setOnClickListener {
         val todays = Calendar.getInstance()
         val year = todays.get(Calendar.YEAR)
         val month = todays.get(Calendar.MONTH)
         val day = todays.get(Calendar.DAY_OF_MONTH)

         val datePickerDialog = DatePickerDialog(this, { _, sYear, sMonth, sDay ->

            val currentDate = "New Type Date is: $sDay/${sMonth + 1}/ $sYear"
            txtSelectedDate.text = currentDate

         }, year, month, day)

         datePickerDialog.show()
      }

      timePicker.setOnTimeChangedListener { timePicker, hour, minute ->
         var hour1 = hour
         var amPM = ""

         //Determine AM or PM
         when {
            hour1 == 0 -> {
               hour1 += 12
               amPM = "AM"
            }

            hour1 == 12 -> amPM = "PM"
            hour1 > 12 -> {
               hour1 -= 12 // 13-12 = 1PM
               amPM = "PM"
            }

            else -> amPM = "AM"
         }

         // Format time if time is 1:0 AM then
         val hour2 = if (hour1 < 10) "0$hour1" else hour1 // 01:0 AM
         val min = if (minute < 10) "0$minute" else minute // 01:15 AM

         val currentTime = "Time is: $hour2 : $min $amPM"

         txtSelectedDate.text = currentTime


      }

      val today = Calendar.getInstance()
      datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)) { view, year, month, day ->

         val currentDate = "Date is: $day/${month + 1}/$year"

         txtSelectedDate.text = currentDate

      }

      btnOptionMenu.setOnClickListener {
         popMenu(btnOptionMenu)
      }
      registerForContextMenu(btnContextMenu)

   }

   private fun popMenu(view: View) {
      val popMenu = PopupMenu(this@MenusActivity, view)
      val inflater: MenuInflater = popMenu.menuInflater
      inflater.inflate(R.menu.pop_up_menu, popMenu.menu)

      popMenu.setOnMenuItemClickListener { menuItems ->
         when (menuItems.itemId) {
            R.id.newGrpPopMenu -> {
               Toast.makeText(this@MenusActivity, "New PopUp Group", Toast.LENGTH_SHORT).show()
            }
            R.id.broadPopMenu -> {
               Toast.makeText(this@MenusActivity, "Broadcast PopUp Menu", Toast.LENGTH_SHORT).show()
            }
         }
         true
      }
      popMenu.show()
   }


   override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
      super.onCreateContextMenu(menu, v, menuInfo)
      menuInflater.inflate(R.menu.context_menu, menu)
   }

   override fun onCreateOptionsMenu(menu: Menu?): Boolean {
      menuInflater.inflate(R.menu.option_menu, menu)
      return true

   }

   override fun onOptionsItemSelected(item: MenuItem): Boolean {
      return when (item.itemId) {
         R.id.broadOpMenu -> {
            Toast.makeText(this@MenusActivity, "Broadcast Clicked", Toast.LENGTH_SHORT).show()
            true
         }

         R.id.newGrpOpMenu -> {
            Toast.makeText(this@MenusActivity, "New Group Clicked", Toast.LENGTH_SHORT).show()
            true
         }

         R.id.devicesOpMenu -> {
            Toast.makeText(this@MenusActivity, "Linked Devices Clicked", Toast.LENGTH_SHORT).show()
            true
         }

         else -> super.onContextItemSelected(item)
      }
   }

   override fun onContextItemSelected(item: MenuItem): Boolean {
      return when (item.itemId) {
         R.id.broadMenu -> {
            Toast.makeText(this@MenusActivity, "Broadcast Clicked", Toast.LENGTH_SHORT).show()
            true
         }

         R.id.newgrpMenu -> {
            Toast.makeText(this@MenusActivity, "New Group Clicked", Toast.LENGTH_SHORT).show()
            true
         }

         R.id.devicesMenu -> {
            Toast.makeText(this@MenusActivity, "Linked Devices Clicked", Toast.LENGTH_SHORT).show()
            true
         }

         else -> super.onContextItemSelected(item)
      }
   }

}