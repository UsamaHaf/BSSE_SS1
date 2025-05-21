package com.usama.uos.bssess1.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.usama.uos.bssess1.Models.ProductsModel
import com.usama.uos.bssess1.R


class AddProductFragment : Fragment() {

   private var databaseReference: DatabaseReference? = null
   lateinit var edtProductName: EditText
   lateinit var edtProductInventory: EditText
   lateinit var edtPurchasePrice: EditText
   lateinit var edtSalePrice: EditText
   lateinit var btnAddProduct: Button
   var _stockModel: ProductsModel? = null


   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      val view = inflater.inflate(R.layout.fragment_add_product, container, false)

      btnAddProduct = view.findViewById(R.id.btnAddProduct)
      edtProductName = view.findViewById(R.id.edtProductName)
      edtProductInventory = view.findViewById(R.id.edtProductInventory)
      edtPurchasePrice = view.findViewById(R.id.edtPurchasePrice)
      edtSalePrice = view.findViewById(R.id.edtSalePrice)

      return view
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      databaseReference = FirebaseDatabase.getInstance().getReference("Products")

      btnAddProduct.setOnClickListener {
         val mStockItemName = edtProductName?.text.toString().trim()
         val mStockItemInventory = edtProductInventory?.text.toString().trim()
         val mWholeSalePrice = edtPurchasePrice?.text.toString().trim()
         val mSalePrice = edtSalePrice?.text.toString().trim()

         if (mStockItemName.isEmpty()) {
            Toast.makeText(activity, "Please Enter Product Name", Toast.LENGTH_SHORT).show()

         } else if (mStockItemInventory.isEmpty()) {
            Toast.makeText(activity, "Please Enter Inventory", Toast.LENGTH_SHORT).show()

         } else if (mWholeSalePrice.isEmpty()) {
            Toast.makeText(activity, "Please Enter Purchase Price", Toast.LENGTH_SHORT).show()

         } else if (mSalePrice.isEmpty()) {
            Toast.makeText(activity, "Please Enter Retail Price", Toast.LENGTH_SHORT).show()

         } else {
            addProductStock(mStockItemName, mStockItemInventory.toInt(), mWholeSalePrice.toDouble(), mSalePrice.toDouble())
         }
      }


   }

   private fun addProductStock(mStockItemName: String, mStockItemInventory: Int, mWholeSalePrice: Double, mSalePrice: Double) {

      val productsModel = ProductsModel()

      val id = databaseReference?.push()?.key
      productsModel.productName = mStockItemName
      productsModel.productInventory = mStockItemInventory
      productsModel.txtSalePrice = mSalePrice
      productsModel.txtPurchasePrice = mWholeSalePrice
      productsModel.productID = id

      databaseReference?.child(id!!)?.setValue(productsModel)

      Toast.makeText(activity, "Product Added Successfully", Toast.LENGTH_SHORT).show()


   }


}