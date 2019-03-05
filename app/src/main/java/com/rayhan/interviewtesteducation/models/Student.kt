package com.rayhan.interviewtesteducation.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.net.Uri

@Entity
class Student {
    @PrimaryKey
    var id: Int? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "dob")
    var dob: String? = null

    @ColumnInfo(name = "sex")
    var sex: String? = null

    @ColumnInfo(name = "blood_group")
    var bloodGroup: String? = null

    @ColumnInfo(name = "contact_number")
    var contactNumber: String? = null

    @ColumnInfo(name = "father_name")
    var fatherName: String? = null

    @ColumnInfo(name = "mother_name")
    var motherName: String? = null

    @ColumnInfo(name = "address")
    var address: String? = null

    @ColumnInfo(name = "photo_uri")
    var photoUri: String? = null

}