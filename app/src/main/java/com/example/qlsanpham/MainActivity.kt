package com.example.qlsanpham

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var addButton: Button
    private val firebaseManager = FirebaseManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        addButton = findViewById(R.id.button_add_product)
        recyclerView.layoutManager = LinearLayoutManager(this)

        firebaseManager.getProducts { productList ->
            productAdapter = ProductAdapter(productList) { product ->
                val intent = Intent(this, ProductDetailActivity::class.java)
                intent.putExtra("productId", product.id)
                startActivity(intent)
            }
            recyclerView.adapter = productAdapter
        }

        addButton.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)
        }
    }
}
