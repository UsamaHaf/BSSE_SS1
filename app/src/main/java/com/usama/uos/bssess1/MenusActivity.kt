package com.usama.uos.bssess1

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MenusActivity : AppCompatActivity() {

   lateinit var btnContextMenu: CardView
   lateinit var btnOptionMenu: CardView

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_menus)

      btnContextMenu = findViewById(R.id.btnContextMenu)
      btnOptionMenu = findViewById(R.id.btnOptionMenu)

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
               Toast.makeText(this@MenusActivity , "New PopUp Group" , Toast.LENGTH_SHORT).show()
            }
            R.id.broadPopMenu -> {
               Toast.makeText(this@MenusActivity , "Broadcast PopUp Menu" , Toast.LENGTH_SHORT).show()

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