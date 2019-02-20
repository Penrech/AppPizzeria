package com.pauenrech.apppizzeria

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_resumen.*

class Resumen : AppCompatActivity() {


    private var escenaIncial: Scene? = null
    private var escenaFinal: Scene? = null
    private var submited = false
    private var transitionManager: Transition? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen)

        escenaIncial = Scene.getSceneForLayout(resumenRoot,
            R.layout.activity_resumen, this)


        escenaFinal = Scene.getSceneForLayout(resumenRoot,
            R.layout.activity_resumen_submited,this)

        escenaIncial?.enter()

        transitionManager = TransitionInflater.from(this)
            .inflateTransition(R.transition.submit)

        val hamburguesa = intent.getStringExtra(IngredientesActivity.HAMBURGUESA_RESUM)
        val imagenHamburguesa = intent.getIntExtra(IngredientesActivity.IMAGEN_RESUM , R.drawable.ic_launcher_background)
        val acompañamientos = intent.getStringExtra(IngredientesActivity.ACOMPANYAMIENTOS_RESUM)
        val toppings = intent.getStringExtra(IngredientesActivity.TOPPINGS_RESUM)
        val direccion = intent.getStringExtra(IngredientesActivity.DIRECCION_RESUM)

        val telefono = intent.getLongExtra(IngredientesActivity.TELEFONO_RESUM,666666666)
        val precio = intent.getFloatExtra(IngredientesActivity.PRECIO_RESUM,0f)

        Picasso.get().load(imagenHamburguesa).into(imagenTopResumen)
        resumenBurgerName.text = hamburguesa
        resumenAcompañamiento.text = acompañamientos
        resumenExtras.text = toppings
        resumenDireccion.text = direccion
        resumenTelefono.text = "$telefono"
        resumenPrecio.text = String.format(getString(R.string.total_prize),precio.toInt())

    }

    fun submitButton(view: View){
        if (submited){
            val homeIntent = Intent(this,MainActivity::class.java)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(homeIntent)
        }
        else{
            TransitionManager.go(escenaFinal,transitionManager)
            submited = true
        }
    }

    override fun onBackPressed() {
        if (submited){
            val homeIntent = Intent(this,MainActivity::class.java)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(homeIntent)
        }
        else{
            super.onBackPressed()
        }

    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        onStartNewActivity()
    }

    override fun finish() {
        super.finish()
        onLeaveThisActivity()
    }

    private fun onLeaveThisActivity() {
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out)
    }

    private fun onStartNewActivity() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }
}
