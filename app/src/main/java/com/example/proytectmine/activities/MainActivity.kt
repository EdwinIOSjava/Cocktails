package com.example.proytectmine.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proytectmine.R
import com.example.proytectmine.adapters.CocktailAdapter
import com.example.proytectmine.data.Cocktail
import com.example.proytectmine.data.CocktailResponse
import com.example.proytectmine.data.CocktailService
import com.example.proytectmine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var adapter: CocktailAdapter
    lateinit var binding: ActivityMainBinding

    // creamos una lista de superheroes vacia para poder llenarla con los resultados de la API
    var cocktailList: List<Cocktail> = listOf()
    var responseList: String=""

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

       /* adapter = CocktailAdapter(cocktailList) { position ->
            val cocktail = cocktailList[position]

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("COCKTAIL_ID", cocktail.id)
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        searchCocktailsById("o")*/

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



    // usamos corrutinas para hacer la peticion a la API y mostrar los resultados en el RV
    fun searchCocktailsById(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = getRetrofit()
                val result = service.findCocktailById(query)

                responseList = result.response

                CoroutineScope(Dispatchers.Main).launch {
                    Log.i("MainActivity", "Response: $responseList")
//                    adapter.items = cocktailList
//                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}