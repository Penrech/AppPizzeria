package com.pauenrech.apppizzeria

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_ingredientes.*
import android.support.v4.app.ActivityOptionsCompat
import android.content.Intent
import android.widget.CheckBox
import com.pauenrech.apppizzeria.data.*
import com.pauenrech.apppizzeria.model.Extra
import com.pauenrech.apppizzeria.model.Hamburguesa
import com.pauenrech.apppizzeria.model.Pedido
import com.pauenrech.apppizzeria.viewHolders.CustomRadioGroupViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pedir_layout.*

class IngredientesActivity : AppCompatActivity()
    , Pedido.PedidoChangePriceListener
    , CustomCheckboxGroupAdapter.ChangeToppingListener
    , CustomRadioGroupAdapter.ChangeSnackListener{

    //Para printar tanto la lista de acompanyamientos como la de ingredientes extra, he utilizado recyclerView
    //Sin la caracteristica de scroll activiada

    private var snackAdapter : CustomRadioGroupAdapter? = null
    private var snackLayoutManager: NoScrollLinearLayoutManager? = null
    private var toppingAdapter : CustomCheckboxGroupAdapter? = null
    private var toppingLayoutManager: NoScrollGridLayoutManager? = null
    private var pedido: Pedido? = null

    companion object {
        const val HAMBURGUESA_RESUM = "hamburguesa"
        const val IMAGEN_RESUM = "imagen_hamburguesa"
        const val ACOMPANYAMIENTOS_RESUM = "acompanyamientos"
        const val TOPPINGS_RESUM = "toppings"
        const val DIRECCION_RESUM = "direccion"
        const val TELEFONO_RESUM = "telefono"
        const val PRECIO_RESUM = "precio"
    }

    private var hamburguesaSeleccionada: Hamburguesa? = null
    private var imagen: Int? = null
    private var nombre: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredientes)

        setSupportActionBar(toolbar_ingredients)

        snackAdapter = CustomRadioGroupAdapter(MainActivity.ListaAcompanyamientos.acompanyamientos!!,this as CustomRadioGroupAdapter.ChangeSnackListener)
        snackLayoutManager = NoScrollLinearLayoutManager(this)
        toppingAdapter = CustomCheckboxGroupAdapter(MainActivity.ListaIngredientes.ingredientes!!,this as CustomCheckboxGroupAdapter.ChangeToppingListener)
        toppingLayoutManager = NoScrollGridLayoutManager(this,2)

        snack_recyclerView.isNestedScrollingEnabled = false
        snack_recyclerView.adapter = snackAdapter
        snack_recyclerView.layoutManager = snackLayoutManager
        topping_recyclerView.isNestedScrollingEnabled = false
        topping_recyclerView.adapter = toppingAdapter
        topping_recyclerView.layoutManager = toppingLayoutManager

        hamburguesaSeleccionada = MainActivity.ListaHamburgesas.hamburguesas!![intent.getIntExtra("position",0)]

        pedido = Pedido(this, hamburguesaSeleccionada!!,this)

        imagen = hamburguesaSeleccionada!!.imagenHamburguesa
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

    private fun onLeaveThisActivity() {
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out)
    }

    private fun onStartNewActivity() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    fun ampliarImagen(view: View){
        val intent = Intent(this, showImageActivity::class.java)
        // Pass data object in the bundle and populate details activity.
        intent.putExtra("imageToResize",imagen)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageDetail_top as View, "resizeImg")
        startActivity(intent, options.toBundle())

    }

    fun toResumenActivity(view: View){
        if(!directionEditText.text.isNullOrEmpty()){
            pedido!!.direccion = directionEditText.text.toString()
        }
        if(!phoneEditText.text.isNullOrEmpty()){
            pedido!!.telefono =  phoneEditText.text.toString().toLong()
        }
        val intent = Intent(this,Resumen::class.java)
        intent.putExtra(HAMBURGUESA_RESUM,pedido!!.hamburguesaSeleccionada.nombreHamburguesa)
        intent.putExtra(IMAGEN_RESUM,pedido!!.hamburguesaSeleccionada.imagenHamburguesa)
        intent.putExtra(ACOMPANYAMIENTOS_RESUM,pedido!!.resumenAcompanyamiento())
        intent.putExtra(TOPPINGS_RESUM,pedido!!.resumenIngredientes())
        intent.putExtra(DIRECCION_RESUM,pedido!!.direccion)
        intent.putExtra(TELEFONO_RESUM,pedido!!.telefono)
        intent.putExtra(PRECIO_RESUM,pedido!!.precioTotal)
        startActivity(intent)
    }

    //Esta interfaz proviene de la clase Pedido y es para cambiar el texto del textView que indica el precio de forma automatica
    override fun changePriceText(actualPrice: Float) {
            totalPriceTextView.text = String.format(getString(R.string.total_prize),actualPrice.toInt())
    }

    //Estas dos interfaces gestionan junto con sus clases y adapters si se añaden o eliminan ingredientes.
    //Ambos son muy similares y estuve pensando si hacer una clase general para los dos, pero complicaba las cosas
    // sobretodo a la hora de pasar el parametro al recyclerView.Adapter<Este parametro> así que hice dos de to_do de ambos
    override fun onChangeTopping(isCheck: Boolean, topping: Extra) {
        if (isCheck){
            pedido!!.addIngrediente(topping)
        }
        else{
            pedido!!.removeIngrediente(topping)
        }
    }

    override fun onChangeSnack(radioButton: CheckBox, snack: Extra) {
        for (rb in CustomRadioGroupViewHolder.radioButtonsArrayList){
            if(rb == radioButton){
                if (rb.isChecked){
                    pedido!!.addAcompanyamiento(snack)
                }
                else{
                    pedido!!.removeAcompanyamiento()
                }
            }
            else{
                rb.isChecked = false
            }
        }
    }
}
