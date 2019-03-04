package com.rayhan.interviewtesteducation.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.rayhan.interviewtesteducation.R
import com.rayhan.interviewtesteducation.adapter.StudentAdapter
import com.rayhan.interviewtesteducation.database.DataRepository
import com.rayhan.interviewtesteducation.models.Student
import kotlinx.android.synthetic.main.activity_student_list.*

class StudentListActivity : AppCompatActivity() {

    private lateinit var studentsAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        studentsAdapter = StudentAdapter(applicationContext, getStudentList())

        initRecyclerView()
    }

    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recycler_view.adapter = studentsAdapter
    }

    /**
     * Get student list from database(sqlite)
     */
    private fun getStudentList(): List<Student> {
        return DataRepository.getAllStudent(applicationContext)
    }
}
