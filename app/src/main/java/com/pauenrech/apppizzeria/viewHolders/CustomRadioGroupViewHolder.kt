package com.pauenrech.apppizzeria.viewHolders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import com.pauenrech.apppizzeria.model.Extra
import kotlinx.android.synthetic.main.snack_layout.view.*

class CustomRadioGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    var radioButton = itemView.radioBtnCard
    var idButton: String?
    var price: Float?
    var name: String?

    companion object {
        var radioButtonsArrayList = arrayListOf<CheckBox>()
    }

    init {
        idButton = null
        price = null
        name = null

    }

    fun bindItem(acompañamiento: Extra){
        idButton = acompañamiento.id
        name = acompañamiento.name
        price = acompañamiento.price
        radioButton.text = acompañamiento.getLabelText()
        radioButtonsArrayList.add(radioButton)
    }


}