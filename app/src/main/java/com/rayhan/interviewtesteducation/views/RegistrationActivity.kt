package com.rayhan.interviewtesteducation.views

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rayhan.interviewtesteducation.R
import com.rayhan.interviewtesteducation.database.AppDatabase
import com.rayhan.interviewtesteducation.database.DataRepository
import com.rayhan.interviewtesteducation.models.Student
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        btn_done.setOnClickListener {
            when {
                et_student_name.text.toString().isBlank() ->
                    Toast.makeText(applicationContext, "please enter student name", Toast.LENGTH_LONG).show()
                et_blood_group.text.toString().isBlank() ->
                    Toast.makeText(applicationContext, "please enter student's blood group", Toast.LENGTH_LONG).show()
                et_contact_number.text.toString().isBlank() ->
                    Toast.makeText(applicationContext, "please enter a contact number", Toast.LENGTH_LONG).show()
                et_father_name.text.toString().isBlank() ->
                    Toast.makeText(applicationContext, "please enter father's name", Toast.LENGTH_LONG).show()
                et_mother_name.text.toString().isBlank() ->
                    Toast.makeText(applicationContext, "please enter mother's name", Toast.LENGTH_LONG).show()
                et_address.text.toString().isBlank() ->
                    Toast.makeText(applicationContext, "please enter address", Toast.LENGTH_LONG).show()
                else -> {

                    val student = Student().apply {
                        name = et_student_name.text.toString()
                        bloodGroup = et_student_name.text.toString()
                        contactNumber = et_student_name.text.toString()
                        fatherName = et_student_name.text.toString()
                        motherName = et_student_name.text.toString()
                        address = et_student_name.text.toString()
                    }

                    DataRepository.insertStudent(applicationContext, student)
                }
            }
        }

    }
}
