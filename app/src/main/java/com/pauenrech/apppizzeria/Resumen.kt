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


    var escenaIncial: Scene? = null
    var escenaFinal: Scene? = null
    var submited = false
    var transitionManager: Transition? = null

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

        val hamburguesa = intent.getStringExtra(ingredientesActivity.HAMBURGUESA_RESUM)
        val imagen_hamburguesa = intent.getIntExtra(ingredientesActivity.IMAGEN_RESUM , R.drawable.ic_launcher_background)
        val acompañamientos = intent.getStringExtra(ingredientesActivity.ACOMPAÑAMIENTOS_RESUM)
        val toppings = intent.getStringExtra(ingredientesActivity.TOPPINGS_RESUM)
        val direccion = intent.getStringExtra(ingredientesActivity.DIRECCION_RESUM)

        val telefono = intent.getLongExtra(ingredientesActivity.TELEFONO_RESUM,666666666)
        val precio = intent.getFloatExtra(ingredientesActivity.PRECIO_RESUM,0f)

        Picasso.get().load(imagen_hamburguesa).into(imagenTopResumen)
        resumenBurgerName.text = hamburguesa
        resumenAcompañamiento.text = acompañamientos
        resumenExtras.text = toppings
        resumenDireccion.text = direccion
        resumenTelefono.text = "$telefono"
        if (precio == precio.toInt().toFloat()) {
            resumenPrecio.text = "Precio total: ${precio.toInt()} €"
        } else {
            resumenPrecio.text = "Precio total: $precio €"
        }


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

    protected fun onLeaveThisActivity() {
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out)
    }

    protected fun onStartNewActivity() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }
}
