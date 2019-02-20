package com.pauenrech.apppizzeria.model

import android.os.Parcel
import android.os.Parcelable

class Hamburguesa constructor(val nombreHamburguesa: String, val precioHamburguesa : Float, val imagenHamburguesa : Int):
    Parcelable {

    constructor(source: Parcel): this(
        source.readString(),
        source.readFloat(),
        source.readInt()
    )

    fun getPriceText(): String{
        return if (precioHamburguesa == precioHamburguesa.toInt().toFloat()) {
            "${precioHamburguesa.toInt()} €"
        } else{
            "$precioHamburguesa €"
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(nombreHamburguesa)
        dest.writeFloat(precioHamburguesa)
        dest.writeInt(imagenHamburguesa)
    }

    override fun describeContents(): Int {
       return 0
    }

    companion object CREATOR: Parcelable.Creator<Hamburguesa>{
        override fun createFromParcel(source: Parcel?): Hamburguesa {
            return Hamburguesa(source!!)
        }

        override fun newArray(size: Int): Array<Hamburguesa?> {
            return arrayOfNulls(size)
        }
    }
}
