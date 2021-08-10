package com.example.myinventory.presentation.utils

import android.util.Patterns
import java.util.regex.Pattern


object FormValidator {

    fun isFieldEmpty(field: String): Boolean {
        return "" == field.trim().toString()
    }

    fun isValidPersonName(name: String): Boolean {
        val pattern = Pattern.compile("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}")
        val matcher =  pattern.matcher(name)
        return matcher.matches()
    }

    fun isValidEmail(emailAddress: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
    }

    fun isValidMobileNumber(mobile: String): Boolean {
        return Patterns.PHONE.matcher(mobile).matches()
    }

    fun isValidUrl(url: String): Boolean {
        return Patterns.WEB_URL.matcher(url).matches()
    }

    fun isValidGSTNo(gst: String): Boolean {
        val pattern = Pattern.compile("\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}")
        val matcher =  pattern.matcher(gst)
        return matcher.matches()
    }

}