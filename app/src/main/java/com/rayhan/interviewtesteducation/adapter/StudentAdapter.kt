package com.rayhan.interviewtesteducation.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rayhan.interviewtesteducation.R
import com.rayhan.interviewtesteducation.listeners.ListItemClick
import com.rayhan.interviewtesteducation.models.Student
import kotlinx.android.synthetic.main.item_student.view.*

class StudentAdapter(
    private val context: Context,
    private val items: List<Student>,
    private val listItemClick: ListItemClick<Student>
) :
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
        holder.tvContact.text = items[position].contactNumber
        holder.tvBloodGroup.text = items[position].bloodGroup

        if (items[position].photoUri != null) {
            holder.ivStudent.setImageURI(Uri.parse(items[position].photoUri))
        }


        holder.view.setOnClickListener {
            listItemClick.onListitemClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemView(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.tv_name
        val tvContact: TextView = view.tv_contact
        val tvBloodGroup: TextView = view.tv_blood
        val ivStudent: ImageView = view.iv_student
    }
}