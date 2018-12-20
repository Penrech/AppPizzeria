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
        rellenarListaHamburguesas()
        rellenarListaAcompañamientos()
        rellenarListaIngredientes()

        layoutManager = LinearLayoutManager(this)
        adapter = HamburguesaListAdapter(ListaHamburgesas.hamburguesas!!,this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

    }


    object ListaHamburgesas{
        public var hamburguesas: ArrayList<Hamburguesa>? = null
    }

    object ListaAcompañamientos{
        public var acompañamientos: ArrayList<Extra>? = null
    }

    object ListaIngredientes{
        public var ingredientes: ArrayList<Extra>? = null
    }

    fun rellenarListaHamburguesas(){

        ListaHamburgesas.hamburguesas = arrayListOf(
            Hamburguesa("300 (gr) Beef Burger",10.99, R.drawable.beef_burguer),
            Hamburguesa("Double Beef Burger",8.99,R.drawable.doble_beef_burguer),
            Hamburguesa("Bacon Burger",7.99,R.drawable.bacon_burguer),
            Hamburguesa("Special Burger",9.99,R.drawable.special_burguer),
            Hamburguesa("Chicken Burger",7.99,R.drawable.chicken_burguer),
            Hamburguesa("Vegan Burger",9.99,R.drawable.vegan_burguer),
            Hamburguesa("Quinoa Burger",8.99,R.drawable.quinoa_burguer)
        )
    }

    fun rellenarListaAcompañamientos(){

        ListaAcompañamientos.acompañamientos = arrayListOf(
            Extra("acomp1","Ensalada",2.0),
            Extra("acomp2","Patata asada", 4.0),
            Extra("acomp3","Patatas fritas con coles de bruselas y tal y cual",3.0)
        )
    }

    fun rellenarListaIngredientes(){

        ListaIngredientes.ingredientes = arrayListOf(
            Extra("extra1","Salsa barbacoa",0.5),
            Extra("extra2","Cebolla cruda",0.5),
            Extra("extra3","Cebolla frita y con caramelo",0.5),
            Extra("extra4","Huevo Frito",1.0),
            Extra("extra5","Pepinillos",0.5),
            Extra("extra6","Aguacate",1.0)
        )

    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        onStartNewActivity()
    }

    override fun finish() {
        super.finish()
        onLeaveThisActivity()
    }

    protected fun onLeaveThisActivity() {
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out)
    }

    protected fun onStartNewActivity() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    override fun hamburguesaItemClicked(id: Int) {
        val intentToDetails = Intent(this, ingredientesActivity::class.java)
        intentToDetails.putExtra("position",id)
        startActivity(intentToDetails)
    }

}
