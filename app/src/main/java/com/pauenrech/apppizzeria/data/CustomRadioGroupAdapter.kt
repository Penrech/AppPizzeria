package com.pauenrech.apppizzeria.data

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pauenrech.apppizzeria.R
import com.pauenrech.apppizzeria.model.Extra

class CustomRadioGroupAdapter (val context: Context, val list: ArrayList<Extra>): RecyclerView.Adapter<CustomRadioGroupViewHolder>() {


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
    }


}