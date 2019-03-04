package com.rayhan.interviewtesteducation.views

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.rayhan.interviewtesteducation.R
import com.rayhan.interviewtesteducation.database.DataRepository
import com.rayhan.interviewtesteducation.models.Admin
import com.rayhan.interviewtesteducation.models.Student
import kotlinx.android.synthetic.main.activity_admin_login.*

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var progress: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        initClickListeners()
    }

    private fun initClickListeners() {
        btn_login.setOnClickListener {
            checkLogin()
        }
    }

    private fun checkLogin() {
        when {
            et_email.text.toString().isBlank() ->
                Toast.makeText(applicationContext, "please enter admin email", Toast.LENGTH_LONG).show()
            !Patterns.EMAIL_ADDRESS.matcher(et_email.text.toString()).matches() ->
                Toast.makeText(applicationContext, "please enter valid email", Toast.LENGTH_LONG).show()
            et_password.text.toString().isBlank() ->
                Toast.makeText(applicationContext, "please enter admin password", Toast.LENGTH_LONG).show()
            else -> {
                checkCredentials()
            }
        }
    }


    /**
     * Check credentials in database with fake loading
     */
    private fun checkCredentials() {
        showProgressDialog()
        Handler().postDelayed({
            hideLoading()

            val admins = DataRepository.getAdmin(
                applicationContext,
                et_email.text.toString(),
                et_password.text.toString()
            )

            if (admins.isNotEmpty()) {
                startActivity(Intent(applicationContext, StudentListActivity::class.java))
            } else {
                Toast.makeText(applicationContext, "Credentials does not match", Toast.LENGTH_LONG).show()
            }
        }, 1200)
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
}
