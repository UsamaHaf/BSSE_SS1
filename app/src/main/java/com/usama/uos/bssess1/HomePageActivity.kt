package com.usama.uos.bssess1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.usama.uos.bssess1.Fragments.AboutUsFragment
import com.usama.uos.bssess1.Fragments.UserProfileFragment
import com.usama.uos.bssess1.SharedPref.MySharedPreferences

class HomePageActivity : AppCompatActivity() {

   /*lateinit var txtWelcomeText: TextView
   lateinit var btnLogoutUser: Button*/
   lateinit var sharedPreferences: MySharedPreferences

   lateinit var myDrawerLayout: DrawerLayout
   lateinit var myNavigationView: NavigationView
   lateinit var btnOpenSideMenu: ImageView
   lateinit var appBarTitle: TextView

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_home_page)

      sharedPreferences = MySharedPreferences(this@HomePageActivity)

      myDrawerLayout = findViewById(R.id.mainDrawerLayout)
      myNavigationView = findViewById(R.id.ss1NavigationView)
      btnOpenSideMenu = findViewById(R.id.btnSideMenuOpen)
      appBarTitle = findViewById(R.id.appBarTitle)

      setFragment(UserProfileFragment(), "User Profile")
      myDrawerLayout.closeDrawer(GravityCompat.START)

      //setting header view
      val viewHeader: View = myNavigationView.getHeaderView(0)
      val headerUsername = viewHeader.findViewById<TextView>(R.id.headerEmailAddress)
      headerUsername.text = sharedPreferences.getEmail("UserEmail")
      //setting header view


      btnOpenSideMenu.setOnClickListener {
         myDrawerLayout.openDrawer(GravityCompat.START)
      }

      val toggle =
          ActionBarDrawerToggle(this@HomePageActivity, myDrawerLayout, null, R.string.open_navigation_drawer, R.string.close_navigation_drawer)
      myDrawerLayout.addDrawerListener(toggle)
      toggle.syncState()

      myNavigationView.setNavigationItemSelectedListener { menuItems ->
         when (menuItems.itemId) {
            R.id.userProfile -> {
               setFragment(UserProfileFragment(), "User Profile")
            }

            R.id.aboutUs -> {
               setFragment(AboutUsFragment(), "About Us")
            }

            R.id.logoutUser -> {
               sharedPreferences.saveIsLoggedIn("UserLoginStatus", "False")
               Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show()
               startActivity(Intent(this@HomePageActivity, LoginActivity::class.java))
            }
         }
         true
      }
   }

   private fun setFragment(fragment: Fragment, title: String) {
      this@HomePageActivity.supportFragmentManager.beginTransaction()
         .replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()
      myDrawerLayout.closeDrawer(GravityCompat.START)
      appBarTitle.text = title
   }
}


/*txtWelcomeText = findViewById(R.id.txtWelcomeText)
    btnLogoutUser = findViewById(R.id.btnLogoutUser)
    txtWelcomeText.text = "Welcome Mr. ${sharedPreferences.getEmail("UserEmail")}"
    btnLogoutUser.setOnClickListener {
       sharedPreferences.saveIsLoggedIn("UserLoginStatus","False")
       Toast.makeText(this , "Logout Successfully" , Toast.LENGTH_SHORT).show()
       startActivity(Intent(this@HomePageActivity , LoginActivity::class.java))
    }*//*  val receivedData = intent.getStringExtra("TestData")
if(receivedData != null){

}else{
   txtWelcomeText.text = "No data found"

}*/

