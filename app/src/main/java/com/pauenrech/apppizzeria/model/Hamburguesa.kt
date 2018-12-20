package com.pauenrech.apppizzeria.model

import android.os.Parcel
import android.os.Parcelable


class Hamburguesa constructor(val nombreHamburguesa: String, val precioHamburguesa : Double, val imagenHamburguesa : Int):
    Parcelable {

    constructor(source: Parcel): this(
        source.readString(),
        source.readDouble(),
        source.readInt()

    )

    fun getPriceText(): String{
        if (precioHamburguesa == precioHamburguesa.toInt().toDouble()) {
            return "${precioHamburguesa.toInt()} €"
        }
        else{
            return "$precioHamburguesa €"
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(nombreHamburguesa)
        dest.writeDouble(precioHamburguesa)
        dest.writeInt(imagenHamburguesa)
    }

    override fun describeContents(): Int {
       return 0
    }

    companion object CREATOR: Parcelable.Creator<Hamburguesa>{
        override fun createFromParcel(source: Parcel): Hamburguesa {
            return Hamburguesa(source)
        }

        override fun newArray(size: Int): Array<Hamburguesa?> {
            return arrayOfNulls(size)
        }
    }
}
