package com.rayhan.interviewtesteducation.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SPreferences {

    private const val SP_FIRST_TIME_APP_LAUNCH = "firstTimeLaunch"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setFirstTimeAppLaunched(context : Context) {
        getSharedPreferences(context).edit().putBoolean(SP_FIRST_TIME_APP_LAUNCH, false).apply()
    }

    fun isFirstTimeAppLaunch(context : Context): Boolean {
        return getSharedPreferences(context).getBoolean(SP_FIRST_TIME_APP_LAUNCH, true)
    }

}