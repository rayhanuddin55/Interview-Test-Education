package com.rayhan.interviewtesteducation.views

import ADMIN_EMAIL
import ADMIN_PASSWORD
import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rayhan.interviewtesteducation.R
import com.rayhan.interviewtesteducation.database.AppDatabase
import com.rayhan.interviewtesteducation.database.DataRepository
import com.rayhan.interviewtesteducation.models.Admin
import com.rayhan.interviewtesteducation.utils.SPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (SPreferences.isFirstTimeAppLaunch(applicationContext)) {
            // First time launch set to false
            SPreferences.setFirstTimeAppLaunched(applicationContext)
            insertDefaultAdminAc()
        }

        initClickListeners()
    }

    /**
     * Set on click listeners for this view
     */
    private fun initClickListeners() {
        tv_registration.setOnClickListener {
            startActivity(Intent(applicationContext, RegistrationActivity::class.java))
        }

        tv_admin_login.setOnClickListener {
            startActivity(Intent(applicationContext, AdminLoginActivity::class.java))
        }
    }


    /**
     * Create fake admin account
     * Email : admin@admin.com
     * Password : 123456
     */
    private fun insertDefaultAdminAc() {
        DataRepository.insertAdmin(applicationContext,
            Admin().apply {
                id = 1
                email = ADMIN_EMAIL
                password = ADMIN_PASSWORD
            })
    }
}
