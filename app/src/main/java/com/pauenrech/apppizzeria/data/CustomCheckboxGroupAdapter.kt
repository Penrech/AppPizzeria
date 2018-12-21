package com.pauenrech.apppizzeria.data

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pauenrech.apppizzeria.R
import com.pauenrech.apppizzeria.model.Extra
import com.pauenrech.apppizzeria.viewHolders.CustomCheckboxViewHolder

class CustomCheckboxGroupAdapter (val list: ArrayList<Extra>, val changeToppingListener: ChangeToppingListener): RecyclerView.Adapter<CustomCheckboxViewHolder>() {

    interface ChangeToppingListener{
        fun onChangeTopping(isCheck: Boolean,topping: Extra)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomCheckboxViewHolder {
        val vistaCelda = LayoutInflater.from(p0.context)
            .inflate(R.layout.topping_layout,p0,false)
        return CustomCheckboxViewHolder(vistaCelda)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CustomCheckboxViewHolder, p1: Int) {
        holder.bindItem(list[p1])
        holder.checkBox.setOnClickListener {
            changeToppingListener.onChangeTopping(holder.checkBox.isChecked,list[p1])
        }
    }


}