package com.example.mobilehotelmanagementsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_layout.view.*

class DataAdapter (var list:ArrayList<Staff>) :RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var staffId = itemView.tv_id
        var staffName = itemView.tv_name
        var satffGender = itemView.tv_gender
        var staffBirth = itemView.tv_birth
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.staffId.text = list[position].StaffId
        holder.staffName.text=list[position].StaffName
        holder.satffGender.text=list[position].StaffGender
        holder.staffBirth.text=list[position].StaffBirth

    }

    override fun getItemCount(): Int {
        return list.size
    }
}