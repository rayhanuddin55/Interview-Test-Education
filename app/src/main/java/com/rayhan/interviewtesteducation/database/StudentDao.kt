package com.rayhan.interviewtesteducation.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.rayhan.interviewtesteducation.models.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM student")
    fun getAll(): List<Student>

    @Insert
    fun insert(users: Student)
}