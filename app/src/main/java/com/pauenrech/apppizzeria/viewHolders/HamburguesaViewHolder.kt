package com.pauenrech.apppizzeria.viewHolders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.pauenrech.apppizzeria.BitmapTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_layout.view.*

class HamburguesaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var nombre: TextView
    var precioString: TextView
    var precio: Float?
    var imagenId: Int?
    var imagen: ImageView

    init{
        precio = null
        imagenId = null
        nombre = itemView.hamNameTextView
        precioString = itemView.hamPriceTextView
        imagen = itemView.imageView
    }

    fun setPrice(price: Float, priceString: String){
        precio = price
        precioString.text = priceString
    }

    fun setImage(image: Int,maxWidth: Int, maxHeight: Int){
        imagenId = image
        Picasso.get()
            .load(image)
            .transform(BitmapTransform(maxWidth,maxHeight))
            .into(imagen)
    }

}