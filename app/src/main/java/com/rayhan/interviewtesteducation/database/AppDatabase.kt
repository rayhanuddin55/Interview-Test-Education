package com.rayhan.interviewtesteducation.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.rayhan.interviewtesteducation.models.Admin
import com.rayhan.interviewtesteducation.models.Student

@Database(entities = [Student::class, Admin::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao() : StudentDao
    abstract fun adminDao() : AdminDao
}