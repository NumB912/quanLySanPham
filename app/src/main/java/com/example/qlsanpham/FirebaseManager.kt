package com.example.qlsanpham

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseManager {
    private val database = FirebaseDatabase.getInstance().reference

    fun addProduct(product: Product) {
        database.child("products").child(product.id).setValue(product)
    }

    fun getProducts(onDataChange: (List<Product>) -> Unit) {
        database.child("products").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = mutableListOf<Product>()
                for (data in snapshot.children) {
                    data.getValue(Product::class.java)?.let { productList.add(it) }
                }
                onDataChange(productList)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun updateProduct(product: Product) {
        database.child("products").child(product.id).setValue(product)
    }

    fun deleteProduct(productId: String) {
        database.child("products").child(productId).removeValue()
    }
}
