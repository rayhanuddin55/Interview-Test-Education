package com.rayhan.interviewtesteducation.views

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rayhan.interviewtesteducation.R
import com.rayhan.interviewtesteducation.database.DataRepository
import com.rayhan.interviewtesteducation.models.Student
import kotlinx.android.synthetic.main.activity_student_details.*

class StudentDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_STUDENT_ID = "extra_student_id"
    }

    var studentId: Int? = null

    var studentObj: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        studentId = intent?.getIntExtra(EXTRA_STUDENT_ID, -1)

        getStudentFromDatabase()
        setValues()

        iv_back.setOnClickListener { onBackPressed() }
    }

    private fun setValues() {
        studentObj?.let {
            tv_name.text = it.name
            iv_student.setImageURI(Uri.parse(it.photoUri.toString()))
        }
    }

    private fun getStudentFromDatabase() {
        studentId?.let { studentObj = DataRepository.getStudent(applicationContext, it) }
    }
}
