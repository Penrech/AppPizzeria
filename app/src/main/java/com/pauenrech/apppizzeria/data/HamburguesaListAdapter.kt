package com.pauenrech.apppizzeria.data

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pauenrech.apppizzeria.R
import com.pauenrech.apppizzeria.model.Hamburguesa
import com.pauenrech.apppizzeria.viewHolders.HamburguesaViewHolder

class HamburguesaListAdapter (val hamburguesas: ArrayList<Hamburguesa>, val clickListener: HamburguesaListAdapterClickListener): RecyclerView.Adapter<HamburguesaViewHolder>() {

    interface HamburguesaListAdapterClickListener{
        //Paso la posición simulando que es su id
        fun hamburguesaItemClicked(id: Int)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HamburguesaViewHolder {
        val vistaCelda = LayoutInflater.from(p0.context)
            .inflate(R.layout.card_layout,p0,false)
        return HamburguesaViewHolder(vistaCelda)
    }

    override fun getItemCount(): Int {
        return hamburguesas.size
    }

    override fun onBindViewHolder(holder: HamburguesaViewHolder, p1: Int) {
        holder.nombre.text = hamburguesas[p1].nombreHamburguesa
        holder.setPrice(hamburguesas[p1].precioHamburguesa,hamburguesas[p1].getPriceText())
        holder.setImage(hamburguesas[p1].imagenHamburguesa,200,200)
        holder.itemView.setOnClickListener {
            clickListener.hamburguesaItemClicked(p1)
        }
    }


}