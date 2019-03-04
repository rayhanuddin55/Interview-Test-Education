package com.rayhan.interviewtesteducation.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.rayhan.interviewtesteducation.models.Admin
import com.rayhan.interviewtesteducation.models.Student

@Dao
interface AdminDao {

    @Query("SELECT * FROM admin WHERE email == :email AND password == :password")
    fun get(email: String, password: String): List<Admin>

    @Insert
    fun insert(admin: Admin)
}