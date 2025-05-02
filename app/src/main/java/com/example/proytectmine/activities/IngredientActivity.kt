package com.example.proytectmine.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proytectmine.R
import com.example.proytectmine.adapters.CocktailAdapter
import com.example.proytectmine.data.CocktailService
import com.example.proytectmine.data.Drink
import com.example.proytectmine.data.FavoritesDAO
import com.example.proytectmine.databinding.ActivityDetailBinding
import com.example.proytectmine.databinding.ActivityIngredientBinding
import com.example.proytectmine.src.FavoritesRepository
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IngredientActivity : AppCompatActivity() {

    lateinit var binding: ActivityIngredientBinding
    lateinit var adapter: CocktailAdapter

    private var filteredDrinks: List<Drink> = emptyList()


    private val ingredientes = arrayOf(
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Search BY INGREDIENT"
        searchAllsCocktailsByIngredient("gin")
        val coctelesFavoritosById = FavoritesRepository.getFavoriteDrinkIds(this)


        adapter = CocktailAdapter(filteredDrinks,coctelesFavoritosById) { position ->

            val clickedDrink = filteredDrinks[position]

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("COCKTAIL_ID", clickedDrink.idDrink)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
       
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, ingredientes)
        (binding.menu.editText as? AutoCompleteTextView)?.setAdapter(adapter)// esto hace se vea el autocompletar

        (binding.menu.editText as? AutoCompleteTextView)?.setOnItemClickListener { parent, view, position, id ->
            val selectedIngredient = parent.getItemAtPosition(position).toString()
            searchAllsCocktailsByIngredient(selectedIngredient)
            Toast.makeText(this, "Ingredient: $selectedIngredient", Toast.LENGTH_SHORT).show()
        }




    }
    fun getRetrofit(): CocktailService {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CocktailService::class.java)
    }
    fun searchAllsCocktailsByIngredient(ingredient: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = getRetrofit()
                val result = service.findCocktailByIngredients(ingredient)
                filteredDrinks = result.drinks!!
                Log.i("CocktailsIngre Hilo secundario", "Response: $filteredDrinks")


                CoroutineScope(Dispatchers.Main).launch {
                    adapter.items = filteredDrinks
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("ErrorIngredients", "Error: ${e.message}")
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {


            android.R.id.home -> {
                //
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}