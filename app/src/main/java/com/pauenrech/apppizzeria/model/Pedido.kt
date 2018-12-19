package com.pauenrech.apppizzeria.model

import java.io.Serializable
import kotlin.properties.Delegates

class Pedido(var hamburguesaSeleccionada: Hamburguesa) : Serializable {

    var listaAcompañamientos: MutableMap<String,Double> = mutableMapOf()
    var listaIngredientes: MutableMap<String,Double> = mutableMapOf()
    var precioTotal = 0.0

    init {
        precioTotal = hamburguesaSeleccionada.precioHamburguesa!!
    }

    fun addAcompañamiento(acompañamiento: Pair<String,Double>){
        var result = listaAcompañamientos.putIfAbsent(acompañamiento.first,acompañamiento.second)
        if (result == null){
            precioTotal += acompañamiento.second
        }
    }
    fun removeAcompañamiento(acompañamiento: Pair<String, Double>){
        var result = listaAcompañamientos.remove(acompañamiento.first)
        if (result != null){
            precioTotal -= acompañamiento.second
        }
    }
    fun addIngrediente(ingrediente: Pair<String,Double>){
        var result = listaIngredientes.putIfAbsent(ingrediente.first,ingrediente.second)
        if (result == null){
            precioTotal += ingrediente.second
        }
    }
    fun removeIngrediente(ingrediente: Pair<String, Double>){
        var result = listaIngredientes.remove(ingrediente.first)
        if (result != null){
            precioTotal -= ingrediente.second
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
            var lista = listaAcompañamientos.toList()
            resultString = lista.joinToString {
                it.first
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
            var lista = listaIngredientes.toList()
            resultString = lista.joinToString {
                it.first
            }
        }
        else{
            resultString = "Sin ingredientes extra"
        }
        return resultString
    }

}