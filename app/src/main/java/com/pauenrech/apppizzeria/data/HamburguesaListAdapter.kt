package com.pauenrech.apppizzeria.data

import android.content.Context
import android.content.Intent

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.pauenrech.apppizzeria.BitmapTransform
import com.pauenrech.apppizzeria.MainActivity
import com.pauenrech.apppizzeria.R
import com.pauenrech.apppizzeria.ingredientesActivity
import com.pauenrech.apppizzeria.model.Hamburguesa
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_layout.view.*

class HamburguesaListAdapter (val context: Context): RecyclerView.Adapter<HamburguesaListAdapter.ViewHolder>() {

    var hamburguesas = MainActivity.ListaHamburgesas.hamburguesas!!
    val picasso = Picasso.Builder(context)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val vistaCelda = LayoutInflater.from(p0.context)
            .inflate(R.layout.card_layout,p0,false)
        return ViewHolder(vistaCelda)
    }

    override fun getItemCount(): Int {
        return hamburguesas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {

        holder.nombre.text = hamburguesas[p1].nombreHamburguesa
        holder.setPrice(hamburguesas[p1].precioHamburguesa!!)
        holder.setImage(hamburguesas[p1].imagenHamburguesa!!,hamburguesas[p1].imagenHamburguesa_thumb!!)

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var nombre: TextView
        var precioString: TextView
        var precio: Double?
        var imagenId: Int?
        var imagenId_thumb: Int?
        var imagen: ImageView
        init{
            precio = null
            imagenId = null
            imagenId_thumb = null
            nombre = itemView.hamNameTextView
            precioString = itemView.hamPriceTextView
            imagen = itemView.imageView

            itemView.setOnClickListener {

                val intentToDetails = Intent(it.context,ingredientesActivity::class.java)
                intentToDetails.putExtra("position",adapterPosition)
                Log.i("LOG","SEND ACTIVITY")
                it.context.startActivity(intentToDetails)

            }
        }

        fun setPrice(price: Double){
            precio = price
            precioString.text = "$precio €"
        }

        fun setImage(image: Int, image_thumb: Int){
            imagenId = image
            imagenId_thumb = image_thumb

            Picasso.get()
                .load(image)
                .transform(BitmapTransform(200,200))
                .into(imagen)
            //imagen.setImageResource(imagenId_thumb!!)
        }


    }


}