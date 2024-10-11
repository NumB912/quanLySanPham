package com.example.qlsanpham

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class AddProductActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var price: EditText
    private lateinit var description: EditText
    private lateinit var saveButton: Button
    private val firebaseManager = FirebaseManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        name = findViewById(R.id.product_name)
        price = findViewById(R.id.product_price)
        description = findViewById(R.id.product_description)
        saveButton = findViewById(R.id.save_button)

        saveButton.setOnClickListener {
            val productId = FirebaseDatabase.getInstance().reference.child("products").push().key.toString()
            val newProduct = Product(productId, name.text.toString(), price.text.toString().toInt(), description.text.toString())
            firebaseManager.addProduct(newProduct)
            finish()
        }
    }
}

