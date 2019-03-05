package com.rayhan.interviewtesteducation.views

import android.app.Activity
import android.arch.persistence.room.Room
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.rayhan.interviewtesteducation.R
import com.rayhan.interviewtesteducation.database.AppDatabase
import com.rayhan.interviewtesteducation.database.DataRepository
import com.rayhan.interviewtesteducation.models.Student
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    private  val READ_REQUEST_CODE: Int = 42

    private  var selectedPhotoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        imageView.setOnClickListener{
            performFileSearch()
        }


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
                        photoUri = selectedPhotoUri.toString()
                    }

                    DataRepository.insertStudent(applicationContext, student)
                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
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

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            // Filter to only show results that can be "opened", such as a
            // file (as opposed to a list of contacts or timezones)
            addCategory(Intent.CATEGORY_OPENABLE)

            // Filter to show only images, using the image MIME data type.
            // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
            // To search for all documents available via installed storage providers,
            // it would be "*/*".
            type = "image/*"
        }

        startActivityForResult(intent, READ_REQUEST_CODE)
    }
}
