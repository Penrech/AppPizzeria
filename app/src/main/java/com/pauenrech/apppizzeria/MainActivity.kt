package com.pauenrech.apppizzeria

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.pauenrech.apppizzeria.data.HamburguesaListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_layout.*
import android.content.Intent
import com.pauenrech.apppizzeria.model.Extra
import com.pauenrech.apppizzeria.viewHolders.HamburguesaViewHolder
import com.pauenrech.apppizzeria.model.Hamburguesa

class MainActivity : AppCompatActivity(), HamburguesaListAdapter.HamburguesaListAdapterClickListener {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<HamburguesaViewHolder>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar!!.title = getString(R.string.menu)

        rellenarListaHamburguesas()
        rellenarListaAcompanyamientos()
        rellenarListaIngredientes()

        layoutManager = LinearLayoutManager(this)
        adapter = HamburguesaListAdapter(ListaHamburgesas.hamburguesas!!,this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

    }


    object ListaHamburgesas{
        var hamburguesas: ArrayList<Hamburguesa>? = null
    }

    object ListaAcompanyamientos{
        var acompanyamientos: ArrayList<Extra>? = null
    }

    object ListaIngredientes{
        var ingredientes: ArrayList<Extra>? = null
    }


    //He querido simular el hecho de que se descargaran todos los datos desde un servidor y luego se printaran en las activities correspondientes
    private fun rellenarListaHamburguesas(){

        ListaHamburgesas.hamburguesas = arrayListOf(
            Hamburguesa("300 (gr) Beef Burger",10.99f, R.drawable.beef_burguer),
            Hamburguesa("Double Beef Burger",8.99f,R.drawable.doble_beef_burguer),
            Hamburguesa("Bacon Burger",7.99f,R.drawable.bacon_burguer),
            Hamburguesa("Special Burger",9.99f,R.drawable.special_burguer),
            Hamburguesa("Chicken Burger",7.99f,R.drawable.chicken_burguer),
            Hamburguesa("Vegan Burger",9.99f,R.drawable.vegan_burguer),
            Hamburguesa("Quinoa Burger",8.99f,R.drawable.quinoa_burguer)
        )
    }

    private fun rellenarListaAcompanyamientos(){

        ListaAcompanyamientos.acompanyamientos = arrayListOf(
            Extra("acomp1","Ensalada",2f),
            Extra("acomp2","Patata asada", 4f),
            Extra("acomp3","Patatas fritas",3f)
        )
    }

    private fun rellenarListaIngredientes(){

        ListaIngredientes.ingredientes = arrayListOf(
            Extra("extra1","Salsa barbacoa",0.5f),
            Extra("extra2","Cebolla cruda",0.5f),
            Extra("extra3","Cebolla frita",0.5f),
            Extra("extra4","Huevo Frito",1.0f),
            Extra("extra5","Pepinillos",0.5f),
            Extra("extra6","Aguacate",1f)
        )

    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        onStartNewActivity()
    }

    private fun onStartNewActivity() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    override fun hamburguesaItemClicked(id: Int) {
        val intentToDetails = Intent(this, IngredientesActivity::class.java)
        intentToDetails.putExtra("position",id)
        startActivity(intentToDetails)
    }

}
