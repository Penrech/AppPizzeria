package com.pauenrech.apppizzeria.model

class Pedido(var hamburguesaSeleccionada: Hamburguesa) {

    var listaAcompañamientos: MutableMap<String,Extra> = mutableMapOf()
    var listaIngredientes: MutableMap<String,Extra> = mutableMapOf()
    var precioTotal = 0.0

    init {
        precioTotal = hamburguesaSeleccionada.precioHamburguesa
    }

    fun addAcompañamiento(acompañamiento: Extra){
        val result = listaAcompañamientos.putIfAbsent(acompañamiento.id,acompañamiento)
        if (result == null){
            precioTotal += acompañamiento.price
        }
    }
    fun removeAcompañamiento(acompañamiento: Extra){
        val result = listaAcompañamientos.remove(acompañamiento.id)
        if (result != null){
            precioTotal -= acompañamiento.price
        }
    }
    fun addIngrediente(ingrediente: Extra){
        val result = listaIngredientes.putIfAbsent(ingrediente.id,ingrediente)
        if (result == null){
            precioTotal += ingrediente.price
        }
    }
    fun removeIngrediente(ingrediente: Extra){
        val result = listaIngredientes.remove(ingrediente.id)
        if (result != null){
            precioTotal -= ingrediente.price
        }
    }

    fun conAcompañamiento(): Boolean{
        return !listaAcompañamientos.isEmpty()
    }

    fun conIngredientes(): Boolean{
        return !listaIngredientes.isEmpty()
    }

    fun resumenAcompañamiento(): String {
        var resultString = ""
        if (conAcompañamiento()){
            val lista = listaAcompañamientos.toList()
            resultString = lista.joinToString {
                it.second.name
            }
        }
        else{
            resultString = "Sin acompañamiento"
        }
        return resultString
    }

    fun resumenIngredientes(): String {
        var resultString = ""
        if (conIngredientes()){
            val lista = listaIngredientes.toList()
            resultString = lista.joinToString {
                it.second.name
            }
        }
        else{
            resultString = "Sin ingredientes extra"
        }
        return resultString
    }

}