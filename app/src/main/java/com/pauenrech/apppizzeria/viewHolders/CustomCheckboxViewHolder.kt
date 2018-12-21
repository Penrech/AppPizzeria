package com.pauenrech.apppizzeria.viewHolders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pauenrech.apppizzeria.model.Extra
import kotlinx.android.synthetic.main.topping_layout.view.*

class CustomCheckboxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    var checkBox = itemView.checkBtnCard
    var idButton: String? = null
    var price: Float? = null
    var name: String? = null

    fun bindItem(topping: Extra){
        idButton = topping.id
        name = topping.name
        price = topping.price
        checkBox.text = topping.getLabelText()
    }

}