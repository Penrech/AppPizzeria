package com.pauenrech.apppizzeria.model



class Hamburguesa {

    var nombreHamburguesa: String? = null
    var precioHamburguesa: Double? = null
    var imagenHamburguesa: Int? = null

    constructor(name: String, price: Double, img: Int){
        nombreHamburguesa = name
        precioHamburguesa = price
        imagenHamburguesa = img
    }

    fun getPriceText(): String{
        if (precioHamburguesa == precioHamburguesa!!.toInt().toDouble()) {
            return "${precioHamburguesa!!.toInt()} €"
        }
        else{
            return "$precioHamburguesa €"
        }

    }

}
