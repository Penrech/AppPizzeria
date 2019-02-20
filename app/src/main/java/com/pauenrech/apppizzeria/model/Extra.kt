package com.pauenrech.apppizzeria.model

import android.os.Parcel
import android.os.Parcelable

//Ya que intenté hacer el pedido parceble, tanto esta clase como la de hamburguesas tienen la implementación realizada
class Extra constructor(val id: String, val name: String, val price: Float): Parcelable {

    constructor(source: Parcel): this(
        source.readString(),
        source.readString(),
        source.readFloat()
    )


    fun getLabelText(): String{
        return if (price == price.toInt().toFloat()) {
            "$name (${price.toInt()} €)"
        } else{
            "$name ($price €)"
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(name)
        dest.writeFloat(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR: Parcelable.Creator<Extra>{
        override fun createFromParcel(source: Parcel): Extra {
            return Extra(source)
        }

        override fun newArray(size: Int): Array<Extra?> {
            return arrayOfNulls(size)
        }
    }
}