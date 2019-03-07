package com.rayhan.interviewtesteducation.views

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
            it.photoUri?.let { uri ->
                iv_student.setImageURI(Uri.parse(uri))
            }
            tv_name.text = it.name
            tv_dob.text = "DOB : ${it.dob}"
            tv_class.text = "Class : ${it.className}"
            tv_gender.text = "Gender : ${it.sex}"
            tv_blood.text = "Blood group : ${it.bloodGroup}"
            tv_contact.text = "Contact number : ${it.contactNumber}"
            tv_father_name.text = "Father's name : ${it.fatherName}"
            tv_mother_name.text = "Mother's name : ${it.motherName}"
            tv_address.text = "Address : ${it.address}"
        }
    }

    private fun getStudentFromDatabase() {
        studentId?.let { studentObj = DataRepository.getStudent(applicationContext, it) }
    }
}
