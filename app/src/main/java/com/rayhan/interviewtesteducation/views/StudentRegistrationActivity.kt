package com.rayhan.interviewtesteducation.views

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rayhan.interviewtesteducation.R
import com.rayhan.interviewtesteducation.database.AppDatabase
import com.rayhan.interviewtesteducation.models.Student
import kotlinx.android.synthetic.main.activity_student_registration.*

class StudentRegistrationActivity : AppCompatActivity() {

    private val DATABASE_NAME = "student-database"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_registration)


        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .build()

        Toast.makeText(applicationContext, db.studentDao().getAll().size.toString(), Toast.LENGTH_LONG).show()


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

                    val student = Student()

                    student.name = et_student_name.text.toString()
                    student.bloodGroup = et_student_name.text.toString()
                    student.contactNumber = et_student_name.text.toString()
                    student.fatherName = et_student_name.text.toString()
                    student.motherName = et_student_name.text.toString()
                    student.address = et_student_name.text.toString()

                    db.studentDao().insert(student)
                }

            }
        }

    }
}
