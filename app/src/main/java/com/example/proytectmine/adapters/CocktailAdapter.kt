package com.example.proytectmine.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.proytectmine.R
import com.example.proytectmine.data.Cocktail

class CocktailAdapter (var items : List<Cocktail>, val onClick: (Int) -> Unit): Adapter<CocktailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cocktail, parent, false)
        return CocktailViewHolder(view)

    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {

        val item = items[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClick(position) }
    }

    override fun getItemCount(): Int = items.size //b el tamaño del listado


}
class CocktailViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val nameTextView: TextView = view.findViewById(R.id.nameCocktail_textView)

    //val pictureImageView: ImageView = view.findViewById(R.id.cocktail_imageView)

    fun render(cocktail: Cocktail) {
        nameTextView.text = cocktail.nameCocktail
        //Picasso.get().load(superhero.image.url).into(pictureImageView);
    }
}