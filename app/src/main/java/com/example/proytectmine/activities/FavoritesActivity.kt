package com.example.proytectmine.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proytectmine.R
import com.example.proytectmine.adapters.CocktailAdapter
import com.example.proytectmine.data.Drink
import com.example.proytectmine.data.FavoritesDAO
import com.example.proytectmine.databinding.ActivityFavoritesBinding
import com.example.proytectmine.src.FavoritesRepository

class FavoritesActivity : AppCompatActivity() {

    lateinit var binding: ActivityFavoritesBinding
    lateinit var adapter: CocktailAdapter
    
    lateinit var favoritesDAO: FavoritesDAO


    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        favoritesDAO= FavoritesDAO(this)// aqui creamos la base de datos y le pasamos el contexto



        supportActionBar?.title = "Favorites Cocktail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val coctelesFavoritos = FavoritesRepository.getFavoriteDrinks(this)
            .map { Drink(it.idDrink ?: "", it.strDrink ?: "", it.strDrinkThumb ?: "") }

        val coctelesFavoritosById = FavoritesRepository.getFavoriteDrinkIds(this)


        Log.i("Favorites", "Favorites: $coctelesFavoritos")
        adapter = CocktailAdapter(coctelesFavoritos,coctelesFavoritosById) { position ->

            val cocktail = coctelesFavoritos[position]

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("COCKTAIL_ID", cocktail.idDrink)
            Toast.makeText(this, "IdCOctel: ${cocktail.idDrink}", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
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