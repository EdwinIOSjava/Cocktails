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
        "7-Up",
        "Absolut Citron",
        "Ale",
        "Amaretto",
        "Angelica root",
        "Añejo rum",
        "Apple brandy",
        "Apple cider",
        "Apple juice",
        "Applejack",
        "Apricot brandy",
        "Baileys irish cream",
        "Berries",
        "Bitters",
        "Blackberry brandy",
        "Blended whiskey",
        "Bourbon",
        "Brandy",
        "Cantaloupe",
        "Carbonated water",
        "Champagne",
        "Cherry brandy",
        "Chocolate",
        "Chocolate liqueur",
        "Chocolate syrup",
        "Cider",
        "Cocoa powder",
        "Coffee",
        "Coffee brandy",
        "Coffee liqueur",
        "Cognac",
        "Cranberries",
        "Cranberry juice",
        "Creme de Cacao",
        "Creme de Cassis",
        "Dark rum",
        "Dry Vermouth",
        "Dubonnet Rouge",
        "Egg",
        "Egg yolk",
        "Espresso",
        "Everclear",
        "Firewater",
        "Galliano",
        "Gin",
        "Ginger",
        "Grape juice",
        "Grapes",
        "Grapefruit juice",
        "Grenadine",
        "Heavy cream",
        "Irish cream",
        "Irish whiskey",
        "Jack Daniels",
        "Johnnie Walker",
        "Kahlua",
        "Kiwi",
        "Lager",
        "Lemon",
        "Lemon juice",
        "Lemon vodka",
        "Lemonade",
        "Light rum",
        "Lime",
        "Lime juice",
        "Mango",
        "Midori melon liqueur",
        "Milk",
        "Orange",
        "Orange bitters",
        "Ouzo",
        "Peach nectar",
        "Peach Vodka",
        "Peppermint schnapps",
        "Pineapple juice",
        "Pisco",
        "Port",
        "Red wine",
        "Ricard",
        "Rum",
        "Sambuca",
        "Scotch",
        "Sherry",
        "Sloe gin",
        "Southern Comfort",
        "Spiced rum",
        "Sprite",
        "Strawberry schnapps",
        "Strawberries",
        "Sugar",
        "Sugar syrup",
        "Sweet Vermouth",
        "Tea",
        "Tequila",
        "Tomato juice",
        "Triple sec",
        "Vodka",
        "Water",
        "Whiskey",
        "Yoghurt"
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
    override fun onResume() {
        super.onResume()
        var coctelesFavoritosById = FavoritesRepository.getFavoriteDrinkIds(this)
        adapter.updateData(coctelesFavoritosById)
    }

}