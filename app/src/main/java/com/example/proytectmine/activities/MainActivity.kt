package com.example.proytectmine.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proytectmine.R
import com.example.proytectmine.adapters.CocktailAdapter
import com.example.proytectmine.data.CocktailResponse
import com.example.proytectmine.data.CocktailService
import com.example.proytectmine.data.Drink
import com.example.proytectmine.data.Ingredients
import com.example.proytectmine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var adapter: CocktailAdapter
    lateinit var binding: ActivityMainBinding

    // creamos una lista de cocktails vacia para poder llenarla con los resultados de la API

    var ingredientsList: List<Ingredients> = listOf()
    var drinkCaracteristics : List<Drink> = listOf()// aqui guardamos la respuesta de la API
    var allsCocktailsByFirstLetter : List<Drink> = listOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title = "Cocktails"

//        searchCocktailByName("Whiskey Sour")
//        searchCocktailsIngredients("553")


        adapter = CocktailAdapter(allsCocktailsByFirstLetter) { position ->

            val cocktail = allsCocktailsByFirstLetter[position]

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("COCKTAIL_ID", cocktail.idDrink)
            Toast.makeText(this,"IdCOctel: ${cocktail.idDrink}", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
        //binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        searchAllsCocktailsByFirstLetter("m")

        //accedemos a los listener de los botones
        initListeners()
    }
    // usamos retrofit para hacer la peticion a la API
    fun getRetrofit(): CocktailService {
        //https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11007
        //lookup.php?i=11007

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CocktailService::class.java)
    }


//    fun searchCocktailsIngredients(query: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val service = getRetrofit()
//                val result = service.findCocktailIngredientsById(query)
//                //ingredientsList = result.ingredients
//                //Log.i("Retrofit Coroutine", "Response: $ingredientsList")
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Log.i("ErrorMainActivityIngredients", "Error: ${e.message}")
//            }
//        }
//    }
//    fun searchCocktailByName(query: String){
//        CoroutineScope(Dispatchers.IO).launch {
//            try{
//                val service =getRetrofit()
//                val result=service.findCocktailByName(query)
//                drinkCaracteristics = result.drinks
//                //Log.i("MainActivity","Response: $drinkCaracteristics")
//            }catch (e: Exception){
//                e.printStackTrace()
//                Log.i("ErrorMainActivityName", "Error: ${e.message}")
//
//            }
//        }
//    }
    fun searchAllsCocktailsByFirstLetter(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = getRetrofit()
                val result = service.findAllsCocktailsByFirstLetter(query)
                allsCocktailsByFirstLetter = result.drinks!!
                //Log.i("CocktailsByLetter Hilo secundario", "Response: $allsCocktailsByFirstLetter")


                CoroutineScope(Dispatchers.Main).launch {
                    adapter.items = allsCocktailsByFirstLetter
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("ErrorMainActivityName", "Error: ${e.message}")
            }
        }
    }
    private fun initListeners() {
        binding.searchCocktailButton.setOnClickListener {
            navigateSearchCocktailView()
        }
        binding.ingredientButton.setOnClickListener {
            navigateIngredientView()
        }
        binding.randomButton.setOnClickListener {
            navigateRandomView()
        }
    }

    fun navigateSearchCocktailView() {
        val intent = Intent(this, SearchCocktailActivity::class.java)
        startActivity(intent)
    }
    fun navigateIngredientView() {
        val intent = Intent(this, IngredientActivity::class.java)
        startActivity(intent)
    }
    fun navigateRandomView() {
        //val intent = Intent(this, RandomActivity::class.java)
    }
}