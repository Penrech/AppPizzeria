package com.pauenrech.apppizzeria.model


import android.util.Log

//Tenía implementado el envio del pedido a la activity resumen con diversos setExtra, intenté hacerlo parceable pero
//me surgían diversos errores, nose si es por el hecho de que hay una interfaz en el constructor, pero al final
//lo he dejado como estaba
class Pedido constructor(var hamburguesaSeleccionada: Hamburguesa ,var changePriceListener : PedidoChangePriceListener){

    interface PedidoChangePriceListener{
        fun changePriceText(actualPrice: Float)
    }

    //No he implementado validación porque me ha ido justo de tiempo, así que he puesto valores por defecto por si no
    //se escribe nada
    var direccion = "Desconocida"
    var telefono: Int = 666666666
    var listaIngredientes: MutableMap<String,Extra> = mutableMapOf()
    var acompañamiento_item: Extra?
    var precioWatcher
        get() = precioTotal
        set(value) {
            precioTotal = value
            Log.i("TAG","Precio cambiado")
            changePriceListener?.changePriceText(precioTotal)
        }
    var precioTotal = 0.0f

    init {
        acompañamiento_item = null
        precioWatcher = hamburguesaSeleccionada.precioHamburguesa
    }

    fun addAcompañamiento(acompañamiento: Extra){
        acompañamiento_item?.let {
            precioWatcher -= it.price
            acompañamiento_item = null
        }
        acompañamiento_item = acompañamiento
        precioWatcher += acompañamiento.price

    }
    fun removeAcompañamiento(){
        precioWatcher -= acompañamiento_item!!.price
        acompañamiento_item = null
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

    fun conAcompañamiento(): Boolean{
        acompañamiento_item?.let {
            Log.i("TAG","acompañamiento")
            return true
        }
        return false
    }

    fun conIngredientes(): Boolean{
        return !listaIngredientes.isEmpty()
    }

    fun resumenAcompañamiento(): String {
        var resultString = ""
        if (conAcompañamiento()){
            resultString = "Acompañamiento: ${acompañamiento_item!!.name}"
        }
        else{
            resultString = "Sin acompañamiento"
        }
        return resultString
    }

    fun resumenIngredientes(): String {
        var resultString = ""
        if (conIngredientes()){
            resultString = "Toppings: "
            val lista = listaIngredientes.toList()
            resultString += lista.joinToString {
                it.second.name
            }
        }
        else{
            resultString = "Sin ingredientes extra"
        }
        return resultString
    }

}