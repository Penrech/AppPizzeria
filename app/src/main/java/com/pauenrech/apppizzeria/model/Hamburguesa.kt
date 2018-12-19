package com.pauenrech.apppizzeria.model

import java.io.Serializable

class Hamburguesa : Serializable{

    var nombreHamburguesa: String? = null
    var precioHamburguesa: Double? = null
    var imagenHamburguesa_thumb: Int? = null
    var imagenHamburguesa: Int? = null

    constructor(name: String, price: Double, img: Int, img_thumb: Int){
        nombreHamburguesa = name
        precioHamburguesa = price
        imagenHamburguesa = img
        imagenHamburguesa_thumb = img_thumb
    }

}
