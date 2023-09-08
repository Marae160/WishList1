package com.example.list1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var itemNameEditText: EditText
    private lateinit var itemPriceEditText: EditText
    private lateinit var itemLocationEditText: EditText
    private lateinit var addButton: Button
    private lateinit var wishListView: ListView
    private val wishList = mutableListOf<WishListItem>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemNameEditText = findViewById(R.id.editTextItemName)
        itemPriceEditText = findViewById(R.id.editTextItemPrice)
        itemLocationEditText = findViewById(R.id.editTextItemLocation)
        addButton = findViewById(R.id.addButton)
        wishListView = findViewById(R.id.wishListView)

        // Initialize the adapter for the ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        wishListView.adapter = adapter

        addButton.setOnClickListener {
            val itemName = itemNameEditText.text.toString()
            val itemPrice = itemPriceEditText.text.toString().toDoubleOrNull()
            val itemLocation = itemLocationEditText.text.toString()

            if (itemName.isNotBlank() && itemPrice != null && itemLocation.isNotBlank()) {
                val newItem = WishListItem(itemName, itemPrice, itemLocation)
                wishList.add(newItem)
                adapter.add("$itemName - $${"%.2f".format(itemPrice)}, $itemLocation")
                clearInputFields()
            } else {
                // Handle invalid input (e.g., show an error message).
            }
        }
    }

    private fun clearInputFields() {
        itemNameEditText.text.clear()
        itemPriceEditText.text.clear()
        itemLocationEditText.text.clear()
    }
}
