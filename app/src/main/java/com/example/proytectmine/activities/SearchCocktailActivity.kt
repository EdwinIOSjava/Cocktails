package com.example.proytectmine.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proytectmine.R
import com.example.proytectmine.adapters.CocktailAdapter
import com.example.proytectmine.data.CocktailService
import com.example.proytectmine.data.Drink
import com.example.proytectmine.databinding.ActivitySearchCocktailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchCocktailActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchCocktailBinding
    lateinit var adapter: CocktailAdapter

    var allsCocktailsByFirstLetter : List<Drink> = listOf()
    var allsCocktailsByName: List<Drink> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySearchCocktailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.activity_search_cocktail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title = "Search Cocktails"

        adapter = CocktailAdapter(allsCocktailsByFirstLetter) { position ->

            val cocktail = allsCocktailsByFirstLetter[position]

//            val intent = Intent(this, DetailActivity::class.java)
//            intent.putExtra("COCKTAIL_ID", cocktail.idDrink)
//            Toast.makeText(this,"IdCOctel: ${cocktail.idDrink}", Toast.LENGTH_SHORT).show()
//            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this,2)
        searchAllsCocktailsByFirstLetter("m")
    }

    // vamos a crear en la appBar del searchActivity un boton search para que el usuario pueda buscar un coctel por nombre
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_search, menu)
        val menuItem = menu?.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {

                searchAllsCocktailsByName(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                return false
            }
        })

         //return super.onCreateOptionsMenu(menu)
        return true
    }

    fun getRetrofit(): CocktailService {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CocktailService::class.java)
    }

    fun searchAllsCocktailsByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = getRetrofit()
                val result = service.findCocktailByName(query)
                allsCocktailsByName = result.drinks
                //Log.i("CocktailsByLetter Hilo secundario", "Response: $allsCocktailsByFirstLetter")


                CoroutineScope(Dispatchers.Main).launch {
                    adapter.items = allsCocktailsByName
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("ErrorMainActivityName", "Error: ${e.message}")
            }
        }
    }
    fun searchAllsCocktailsByFirstLetter(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = getRetrofit()
                val result = service.findAllsCocktailsByFirstLetter(query)
                allsCocktailsByFirstLetter = result.drinks
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

}