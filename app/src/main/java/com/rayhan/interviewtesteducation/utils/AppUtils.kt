package com.rayhan.interviewtesteducation.utils

import android.app.Activity
import android.app.AlertDialog

object AppUtils {

    fun showAlertDialog(activity: Activity, message: String) {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(activity)
        dialog.setMessage(message)
        dialog.setPositiveButton("Ok") { dialog, which ->
            dialog.dismiss()
        }
        dialog.show()
    }
}