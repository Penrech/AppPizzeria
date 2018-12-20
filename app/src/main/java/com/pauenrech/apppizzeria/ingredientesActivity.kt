package com.pauenrech.apppizzeria

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_ingredientes.*
import android.support.v4.app.ActivityOptionsCompat
import android.content.Intent
import com.pauenrech.apppizzeria.data.*
import com.pauenrech.apppizzeria.model.Hamburguesa
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pedir_layout.*



class ingredientesActivity : AppCompatActivity() {

    var snackAdapter : CustomRadioGroupAdapter? = null
    var snackLayoutManager: NoScrollLinearLayoutManager? = null
    var toppingAdapter : CustomCheckboxGroupAdapter? = null
    var toppingLayoutManager: NoScrollGridLayoutManager? = null

    var hamburguesaSeleccionada: Hamburguesa? = null
    var imagen: Int? = null
    var nombre: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredientes)

        setSupportActionBar(toolbar_ingredients)

        snackAdapter = CustomRadioGroupAdapter(this,MainActivity.ListaAcompañamientos.acompañamientos!!)
        snackLayoutManager = NoScrollLinearLayoutManager(this)
        toppingAdapter = CustomCheckboxGroupAdapter(this,MainActivity.ListaIngredientes.ingredientes!!)
        toppingLayoutManager = NoScrollGridLayoutManager(this,2)

        snack_recyclerView.isNestedScrollingEnabled = false
        snack_recyclerView.adapter = snackAdapter
        snack_recyclerView.layoutManager = snackLayoutManager
        topping_recyclerView.isNestedScrollingEnabled = false
        topping_recyclerView.adapter = toppingAdapter
        topping_recyclerView.layoutManager = toppingLayoutManager

        hamburguesaSeleccionada = MainActivity.ListaHamburgesas.hamburguesas!![intent.getIntExtra("position",0)]


        imagen = hamburguesaSeleccionada!!.imagenHamburguesa!!
        nombre = hamburguesaSeleccionada!!.nombreHamburguesa

        Picasso.get().load(imagen!!).into(imageDetail_top)
        pedirNombreBurger.text = nombre

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

    fun ampliarImagen(view: View){
        val intent = Intent(this, showImageActivity::class.java)
        // Pass data object in the bundle and populate details activity.
        intent.putExtra("imageToResize",imagen)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageDetail_top as View, "resizeImg")
        startActivity(intent, options.toBundle())

    }

}
