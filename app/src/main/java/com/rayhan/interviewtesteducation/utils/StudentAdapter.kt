package com.rayhan.interviewtesteducation.utils

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rayhan.interviewtesteducation.R
import com.rayhan.interviewtesteducation.models.Student
import kotlinx.android.synthetic.main.item_student.view.*

class StudentAdapter(val context: Context, val items: ArrayList<Student>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return ItemView(LayoutInflater.from(context).inflate(R.layout.item_student, null, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    private class ItemView(var view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.textView
    }
}