package com.rayhan.interviewtesteducation.utils

import android.app.Activity
import android.app.AlertDialog
import java.text.DateFormatSymbols

object AppUtils {

    fun showAlertDialog(activity: Activity, message: String) {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(activity)
        dialog.setMessage(message)
        dialog.setPositiveButton("Ok") { dialog, which ->
            dialog.dismiss()
        }
        dialog.show()
    }

    fun getMonth(month: Int): String {
        return DateFormatSymbols().months[month]
    }
}