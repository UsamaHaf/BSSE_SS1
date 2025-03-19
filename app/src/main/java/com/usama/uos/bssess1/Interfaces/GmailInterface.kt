package com.usama.uos.bssess1.Interfaces

import android.icu.text.Transliterator.Position
import android.view.View
import com.usama.uos.bssess1.Models.GmailModel

interface GmailInterface {

   fun gmailCLickListener(view: View? , gmailModel: GmailModel , position: Int)

}