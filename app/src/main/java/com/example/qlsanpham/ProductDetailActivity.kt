package com.example.qlsanpham

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var price: EditText
    private lateinit var description: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var productId: String
    private val firebaseManager = FirebaseManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        name = findViewById(R.id.product_name)
        price = findViewById(R.id.product_price)
        description = findViewById(R.id.product_description)
        updateButton = findViewById(R.id.update_button)
        deleteButton = findViewById(R.id.delete_button)

        productId = intent.getStringExtra("productId") ?: return

        firebaseManager.getProducts { productList ->
            val product = productList.find { it.id == productId }
            product?.let {
                name.setText(it.name)
                price.setText(it.price.toString())
                description.setText(it.description)
            }
        }

        updateButton.setOnClickListener {
            val updatedProduct = Product(productId, name.text.toString(), price.text.toString().toInt(), description.text.toString())
            firebaseManager.updateProduct(updatedProduct)
            finish()
        }

        deleteButton.setOnClickListener {
            firebaseManager.deleteProduct(productId)
            finish()
        }
    }
}
