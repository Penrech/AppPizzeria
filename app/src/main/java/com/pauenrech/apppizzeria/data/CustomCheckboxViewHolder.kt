package com.pauenrech.apppizzeria.data

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pauenrech.apppizzeria.model.Extra
import kotlinx.android.synthetic.main.topping_layout.view.*

class CustomCheckboxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    var checkBox = itemView.checkBtnCard
    var idButton: String?
    var price: Double?
    var name: String?

    init {
        idButton = null
        price = null
        name = null

        itemView.setOnClickListener {
            if (checkBox.isChecked){
                //todo añadir al pedido
            }
            else{
                //todo borrar del pedido
            }
        }
    }

    fun bindItem(topping: Extra){
        idButton = topping.id
        name = topping.name
        price = topping.price
        checkBox.text = topping.getLabelText()
    }

}