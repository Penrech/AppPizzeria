package com.pauenrech.apppizzeria.model

import android.content.Context
import android.util.Log
import com.pauenrech.apppizzeria.R

//Tenía implementado el envio del pedido a la activity resumen con diversos setExtra, intenté hacerlo parceable pero
//me surgían diversos errores, nose si es por el hecho de que hay una interfaz en el constructor, pero al final
//lo he dejado como estaba
class Pedido constructor(val context: Context,var hamburguesaSeleccionada: Hamburguesa ,private var changePriceListener : PedidoChangePriceListener){

    interface PedidoChangePriceListener{
        fun changePriceText(actualPrice: Float)
    }

    //No he implementado validación porque me ha ido justo de tiempo, así que he puesto valores por defecto por si no
    //se escribe nada
    var direccion = "Desconocida"
    var telefono: Long = 666666666
    private var listaIngredientes: MutableMap<String,Extra> = mutableMapOf()
    private var acompanyamientoItem: Extra?
    private var precioWatcher
        get() = precioTotal
        set(value) {
            precioTotal = value
            Log.i("TAG","Precio cambiado")
            changePriceListener.changePriceText(precioTotal)
        }
    var precioTotal = 0.0f

    init {
        acompanyamientoItem = null
        precioWatcher = hamburguesaSeleccionada.precioHamburguesa
    }

    fun addAcompanyamiento(acompanyamiento: Extra){
        acompanyamientoItem?.let {
            precioWatcher -= it.price
            acompanyamientoItem = null
        }
        acompanyamientoItem = acompanyamiento
        precioWatcher += acompanyamiento.price

    }
    fun removeAcompanyamiento(){
        precioWatcher -= acompanyamientoItem!!.price
        acompanyamientoItem = null
    }
    fun addIngrediente(ingrediente: Extra){
        val result = listaIngredientes.putIfAbsent(ingrediente.id,ingrediente)
        if (result == null){
            precioWatcher += ingrediente.price
        }
    }
    fun removeIngrediente(ingrediente: Extra){
        val result = listaIngredientes.remove(ingrediente.id)
        if (result != null){
            precioWatcher -= ingrediente.price
        }
    }

    private fun conAcompanyamiento(): Boolean{
        acompanyamientoItem?.let {
            Log.i("TAG","acompañamiento")
            return true
        }
        return false
    }

    private fun conIngredientes(): Boolean{
        return !listaIngredientes.isEmpty()
    }

    fun resumenAcompanyamiento(): String {
        val resultString : String
        resultString = if (conAcompanyamiento()){
            String.format(context.getString(R.string.aconpanyamiento_label),acompanyamientoItem!!.name)
        } else{
            context.getString(R.string.no_acompanyamiento)
        }
        return resultString
    }

    fun resumenIngredientes(): String {
        var resultString: String
        if (conIngredientes()){
            resultString = context.getString(R.string.toppings_pedido_label)
            val lista = listaIngredientes.toList()
            resultString += lista.joinToString {
                it.second.name
            }
        }
        else{
            resultString = context.getString(R.string.no_ingredients_pedido)
        }
        return resultString
    }

}