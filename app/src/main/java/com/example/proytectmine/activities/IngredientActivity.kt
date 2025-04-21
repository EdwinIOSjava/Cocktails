package com.example.proytectmine.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proytectmine.R
import com.example.proytectmine.databinding.ActivityDetailBinding
import com.example.proytectmine.databinding.ActivityIngredientBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class IngredientActivity : AppCompatActivity() {

    lateinit var binding: ActivityIngredientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIngredientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")
        val ingredientes = arrayOf(
            "Light rum",
            "Bourbon",
            "Vodka",
            "Gin",
            "Blended whiskey",
            "Tequila",
            "Sweet Vermouth",
            "Apricot brandy",
            "Triple sec",
            "Southern Comfort",
            "Orange bitters",
            "Brandy",
            "Lemon vodka",
            "Dry Vermouth",
            "Dark rum",
            "Amaretto",
            "Tea",
            "Applejack",
            "Champagne",
            "Scotch",
            "Coffee liqueur",
            "Añejo rum",
            "Bitters",
            "Sugar",
            "Kahlua",
            "Dubonnet Rouge",
            "Lime juice",
            "Irish whiskey",
            "Apple brandy",
            "Carbonated water",
            "Cherry brandy",
            "Creme de Cacao",
            "Grenadine",
            "Port",
            "Coffee brandy",
            "Red wine",
            "Rum",
            "Grapefruit juice",
            "Ricard",
            "Sherry",
            "Cognac",
            "Sloe gin",
            "Strawberry schnapps",
            "Apple juice",
            "Pineapple juice",
            "Lemon juice",
            "Sugar syrup",
            "Milk",
            "Strawberries",
            "Chocolate syrup",
            "Yoghurt",
            "Mango",
            "Ginger",
            "Lime",
            "Cantaloupe",
            "Berries",
            "Grapes",
            "Kiwi",
            "Tomato juice",
            "Cocoa powder",
            "Chocolate",
            "Heavy cream",
            "Galliano",
            "Peach Vodka",
            "Ouzo",
            "Coffee",
            "Spiced rum",
            "Water",
            "Espresso",
            "Angelica root",
            "Orange",
            "Cranberries",
            "Johnnie Walker",
            "Apple cider",
            "Everclear",
            "Cranberry juice",
            "Egg yolk",
            "Egg",
            "Grape juice",
            "Peach nectar",
            "Lemon",
            "Firewater",
            "Lemonade",
            "Lager",
            "Whiskey",
            "Absolut Citron",
            "Pisco",
            "Irish cream",
            "Ale",
            "Chocolate liqueur",
            "Midori melon liqueur",
            "Sambuca",
            "Cider",
            "Sprite",
            "7-Up",
            "Blackberry brandy",
            "Peppermint schnapps",
            "Creme de Cassis",
            "Jack Daniels",
            "Baileys irish cream"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, ingredientes)

        (binding.menu.editText as? AutoCompleteTextView)?.setAdapter(adapter)




    }
}