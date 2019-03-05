package com.rayhan.interviewtesteducation.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Room
import android.content.Context
import com.rayhan.interviewtesteducation.models.Admin
import com.rayhan.interviewtesteducation.models.Student

object DataRepository {

    private const val DATABASE_NAME = "student-database"

    private fun getDatabase(context: Context): AppDatabase {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .build()

        return db
    }

    fun insertStudent(context: Context, student: Student) {
        getDatabase(context).studentDao().insert(student)
    }

    fun getAllStudent(context: Context): List<Student> {
        return getDatabase(context).studentDao().getAll()
    }

    fun getStudent(context: Context, id: Int): Student {
        return getDatabase(context).studentDao().getStudent(id)
    }

    fun insertAdmin(context: Context, admin: Admin) {
        getDatabase(context).adminDao().insert(admin)
    }

    fun getAdmin(context: Context, email: String, password: String): List<Admin> {
        return getDatabase(context).adminDao().get(email, password)
    }


}