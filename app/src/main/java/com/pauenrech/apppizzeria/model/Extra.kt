package com.pauenrech.apppizzeria.model

class Extra(val id: String, val name: String, val price: Double) {

    fun getLabelText(): String{
        if (price == price.toInt().toDouble()) {
            return "$name (${price.toInt()} €)"
        }
        else{
            return "$name ($price €)"
        }
    }

}