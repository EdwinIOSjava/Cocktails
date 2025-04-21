package com.example.proytectmine.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.proytectmine.data.Drink
import com.example.proytectmine.databinding.ItemCocktailBinding
import com.squareup.picasso.Picasso

class CocktailAdapter (
    var items : List<Drink>,
    val onClick: (Int) -> Unit
): Adapter<CocktailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val binding = ItemCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CocktailViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size // retorna el tamaño del listado

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val cocktail = items[position]
        holder.render(cocktail)
        holder.itemView.setOnClickListener { onClick(position) }
    }

}

class CocktailViewHolder(val binding: ItemCocktailBinding) : RecyclerView.ViewHolder(binding.root) {

    //Log.d("VariablestrImageSource", "Response:")

    fun render(cocktail: Drink) {

        binding.nameCocktailTextView.text = cocktail.strDrink
//        Log.d("VariablestrImageSource", "Response: ${cocktail.strImageSource}")
//        Log.d("VariableCocktailImageView", "Response: ${cocktail.strImageSource}")

        Picasso.get()
        .load(cocktail.strDrinkThumb)
        .into(binding.cocktailImageView)
         }
}

