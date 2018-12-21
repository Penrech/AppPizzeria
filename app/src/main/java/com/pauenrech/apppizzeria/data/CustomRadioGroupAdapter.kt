package com.pauenrech.apppizzeria.data

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import com.pauenrech.apppizzeria.R
import com.pauenrech.apppizzeria.model.Extra
import com.pauenrech.apppizzeria.viewHolders.CustomRadioGroupViewHolder

class CustomRadioGroupAdapter (val list: ArrayList<Extra>, val changeSnackListener: ChangeSnackListener): RecyclerView.Adapter<CustomRadioGroupViewHolder>() {

    interface ChangeSnackListener{
        fun onChangeSnack(radioButton: CheckBox,snack: Extra)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomRadioGroupViewHolder {
        val vistaCelda = LayoutInflater.from(p0.context)
            .inflate(R.layout.snack_layout,p0,false)
        return CustomRadioGroupViewHolder(vistaCelda)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CustomRadioGroupViewHolder, p1: Int) {
        holder.bindItem(list[p1])
        holder.radioButton.setOnClickListener {
            changeSnackListener.onChangeSnack(holder.radioButton,list[p1])
        }
    }


}