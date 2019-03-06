package com.rayhan.interviewtesteducation.views

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import com.rayhan.interviewtesteducation.R
import com.rayhan.interviewtesteducation.database.DataRepository
import com.rayhan.interviewtesteducation.models.Student
import com.rayhan.interviewtesteducation.utils.AppUtils
import kotlinx.android.synthetic.main.activity_registration.*
import java.util.*

class RegistrationActivity : AppCompatActivity() {

    private lateinit var progress: ProgressDialog

    private val READ_REQUEST_CODE: Int = 42

    private var selectedPhotoUri: Uri? = null
    private var selectedDob: String? = null
    private var selectedGender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        initClickListeners()
    }

    private fun initClickListeners() {
        imageView.setOnClickListener {
            performFileSearch()
        }

        tv_dob.setOnClickListener {
            showDatePicker()
        }

        tv_gender.setOnClickListener {
            val popupMenu = PopupMenu(this, tv_gender)
            popupMenu.menu.add(0, 0, 0, "Male")
            popupMenu.menu.add(0, 1, 0, "Female")

            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    0 -> {
                        tv_gender.text = "Male"
                        selectedGender = "Male"
                    }
                    1 -> {
                        tv_gender.text = "Female"
                        selectedGender = "Female"
                    }
                }
                true
            }
            popupMenu.show()
        }

        btn_done.setOnClickListener {
            when {
                et_student_name.text.toString().isBlank() ->
                    Toast.makeText(applicationContext, "please enter student name", Toast.LENGTH_LONG).show()
                selectedDob == null ->
                    Toast.makeText(applicationContext, "please select date of birth", Toast.LENGTH_LONG).show()
                selectedGender == null ->
                    Toast.makeText(applicationContext, "please select you gender", Toast.LENGTH_LONG).show()
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
                    saveStudent()
                }
            }
        }

        iv_back.setOnClickListener { onBackPressed() }


    }

    private fun saveStudent() {
        showProgressDialog()
        Handler().postDelayed({
            hideLoading()
            val student = Student().apply {
                name = et_student_name.text.toString()
                dob = selectedDob
                bloodGroup = et_blood_group.text.toString()
                contactNumber = et_contact_number.text.toString()
                fatherName = et_father_name.text.toString()
                motherName = et_mother_name.text.toString()
                address = et_address.text.toString()
                photoUri = selectedPhotoUri?.toString()
            }
            DataRepository.insertStudent(applicationContext, student)

            showSuccessfulMessage()
        }, 700)
    }

    private fun showSuccessfulMessage() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setMessage("Your data save successfully")
        dialog.setPositiveButton("Ok") { dialog, which ->
            onBackPressed()
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            resultData?.data?.also { uri ->
                Log.i("uri", "Uri: $uri")
                selectedPhotoUri = uri
                showImage(uri)
            }
        }
    }

    private fun showImage(uri: Uri) {
        imageView.setImageURI(uri)
    }

    /**
     * Fires an intent to spin up the "file chooser" UI and select an image.
     */
    private fun performFileSearch() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }

        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    private fun showProgressDialog() {
        progress = ProgressDialog(this).apply {
            setTitle("Please wait")
            setMessage("Credentials checking ...")
            show()
        }
    }

    private fun hideLoading() {
        if (progress != null && !progress.isShowing) return
        progress.dismiss()
    }

    private fun showDatePicker() {
        val dialogDatePicker = DatePickerDialog(
            this, { view1, year1, month1, dayOfMonth ->
                val dob = "$dayOfMonth ${AppUtils.getMonth(month1)} $year1"
                selectedDob = dob
                tv_dob.text = dob
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        dialogDatePicker.datePicker.minDate = System.currentTimeMillis()
        dialogDatePicker.show()
    }
}
