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
    lateinit var adapter: CocktailAdapter
    lateinit var binding: ActivitySearchCocktailBinding

    var cocktails: List<Drink> = listOf()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySearchCocktailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //supportActionBar?.hide()
        supportActionBar?.title = "Search Cocktail"
        searchAllsCocktailsByFirstLetter("w")

        adapter = CocktailAdapter(cocktails) { position ->

            val cocktail = cocktails[position]

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("COCKTAIL_ID", cocktail.idDrink)
            Toast.makeText(this,"IdCOctel: ${cocktail.idDrink}", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {// aqui se hace la busqueda
//                searchCocktailByName(query)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {// aqui se actualiza la lista
//                return false
//            }
//
//        })

    }

    fun getRetrofit(): CocktailService {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CocktailService::class.java)
    }

    fun searchCocktailByName(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val service = getRetrofit()
                val result = service.findAllsCocktailsByName(name)
                cocktails = result.drinks

            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("Error SEARCHcoctail ", "Error: RECIBIENDO TODOS LOS COCTELES  : ${e.message}")

            }
        }
    }

    fun searchAllsCocktailsByFirstLetter(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = getRetrofit()
                val result = service.findAllsCocktailsByFirstLetter(query)
                cocktails = result.drinks
                //Log.i("CocktailsByLetter Hilo secundario", "Response: $allsCocktailsByFirstLetter")


                CoroutineScope(Dispatchers.Main).launch {
                    adapter.items = cocktails
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("ErrorMainActivityName", "Error: ${e.message}")
            }
        }
    }
}