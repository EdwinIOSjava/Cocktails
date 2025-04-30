package com.example.proytectmine.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proytectmine.R
import com.example.proytectmine.data.CocktailService
import com.example.proytectmine.data.Drink
import com.example.proytectmine.data.SessionManager
import com.example.proytectmine.data.FavoritesDAO
import com.example.proytectmine.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_HOROSCOPE_ID = "COCKTAIL_ID"// esto sirve para pasar datos a la actividad
    }

    lateinit var binding: ActivityDetailBinding
    lateinit var drink: Drink// creamos esta variable para recibir la respuesta de la API

    var isFavorite = false
    lateinit var favoriteMenu: MenuItem

    lateinit var session: SessionManager

    lateinit var favoritesDAO: FavoritesDAO

//    // variables para Menu item Favorito y manejarlo
//    var isFavorite = false // si es favorito o no
//    lateinit var favoriteMenu: MenuItem // identificar y hacer algo cuando le demos click al favorito <3
//    //creamos variable para la session
//    lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
       supportActionBar?.title = "Drink Details"
        session = SessionManager(this)
        favoritesDAO = FavoritesDAO(this)
        //obtenemos el id del coctel que se selecciono en el activity anterior
        val id = intent.getStringExtra(EXTRA_HOROSCOPE_ID)!!
        Log.i("ID==", "Id: $id ")
        getCocktailById(id)//llamamos a la funcion para obtener los datos del coctel por id

    }

    fun loadData() {
        Picasso.get().load(drink.strDrinkThumb).into(binding.coctelImageView)
        binding.cocktailNameTextView.text = drink.strDrink
        //binding.ingredientsTextView.text = drink.strIngredient1 + ", " + drink.strIngredient2 + ", " + drink.strIngredient3 + ", " + drink.strIngredient4 + ", " + drink.strIngredient5
//        binding.ingredientsTextView.text = drink.getIngredientsWithMeasures().joinToString("\n")
//        binding.instruccionsTextView.text = drink.strInstructionsES

        //isFavorite = session.isFavorite(drink.idDrink!!)
        isFavorite = favoritesDAO.findById(drink.idDrink) != null
        setFavoriteIcon()
    }

    fun getRetrofit(): CocktailService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CocktailService::class.java)
    }
    fun getCocktailById(id: String) {
        // creamos un hilo secundario par ahacer la soilicitud a la API
        CoroutineScope(Dispatchers.IO).launch{
            try{
                val service = getRetrofit()
                val result = service.findCocktailById(id)
                drink= result.drinks!!.get(0)

                Log.i("nombre del COctail", "Nombre del Drink: ${drink.strDrink}")

                CoroutineScope(Dispatchers.Main).launch {
                    loadData()
                    Log.i("Detail Hilo principal", "Response: $drink")
                }

            }catch(e:Exception){
                Log.e("Error en Detail", "Error: ${e.message}")
                e.printStackTrace()

            }
        }
    }
    fun formatearIngredientes(texto:String): String {
        val listaFormateada = texto
            .split(",")
            .joinToString("\n") { "* $it" }

// Asignamos el resultado al EditText
        return listaFormateada
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_detail, menu)

        favoriteMenu = menu.findItem(R.id.action_favorite)
        setFavoriteIcon()

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                isFavorite = !isFavorite

                if (isFavorite) {
                    favoritesDAO.insert(drink)
                    session.setFavorite(drink.idDrink!!, isFavorite)
                    Toast.makeText(this,"Se ha Agregado a Favoritos el Cocktail: ${drink.strDrink}", Toast.LENGTH_SHORT).show()
                } else {
                    favoritesDAO.delete(drink)
                }
                setFavoriteIcon()
                true
            }
            R.id.action_share -> {
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                true
            }
            android.R.id.home -> {
                //
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun setFavoriteIcon() {
        if (isFavorite) {
            favoriteMenu.setIcon(R.drawable.ic_favorite_selected)
        } else {
            favoriteMenu.setIcon(R.drawable.ic_favorite)
        }
    }
}
