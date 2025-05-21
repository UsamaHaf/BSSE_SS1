package com.usama.uos.bssess1.Interfaces

import android.view.View
import com.usama.uos.bssess1.Models.UserModel

interface UserItemClick {

   fun userItemClick(v: View, model: UserModel, position: Int)


}