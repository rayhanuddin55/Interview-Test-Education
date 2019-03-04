package com.rayhan.interviewtesteducation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rayhan.interviewtesteducation.R
import com.rayhan.interviewtesteducation.models.Student
import kotlinx.android.synthetic.main.item_student.view.*

class StudentAdapter(private val context: Context, private val items: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.ItemView>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ItemView {
        return ItemView(
            LayoutInflater.from(context).inflate(
                R.layout.item_student,
                null,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {

        holder.tvName.text = items[position].name
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemView(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.tv_name
    }
}