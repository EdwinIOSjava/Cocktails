package com.example.proytectmine.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proytectmine.R
import com.example.proytectmine.activities.DetailActivity.Companion.EXTRA_HOROSCOPE_ID
import com.example.proytectmine.adapters.CocktailAdapter
import com.example.proytectmine.data.CocktailService
import com.example.proytectmine.data.Drink
import com.example.proytectmine.data.FavoritesDAO
import com.example.proytectmine.databinding.ActivityMainBinding
import com.example.proytectmine.src.FavoritesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var adapter: CocktailAdapter
    lateinit var binding: ActivityMainBinding
    var coctelesFavoritos: List<Drink> = listOf()
    lateinit var coctelesFavoritosById : List<String>



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

        val coctelesFavoritosById = FavoritesRepository.getFavoriteDrinkIds(this)
        adapter = CocktailAdapter(allsCocktailsByFirstLetter,coctelesFavoritosById) { position ->

            val cocktail = allsCocktailsByFirstLetter[position]

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(EXTRA_HOROSCOPE_ID, cocktail.idDrink)
            Toast.makeText(this,"IdCOctel: ${cocktail.idDrink}", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

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
        binding.favoritesButton.setOnClickListener {
            navigateFavoritesView()
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
    fun navigateFavoritesView() {
        val intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
    }
    fun navigateRandomView() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = getRetrofit()
                val result = service.findRandomCocktail()
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
    override fun onResume() {
        super.onResume()
        var coctelesFavoritosById = FavoritesRepository.getFavoriteDrinkIds(this)
        adapter.updateData(coctelesFavoritosById)
    }

}