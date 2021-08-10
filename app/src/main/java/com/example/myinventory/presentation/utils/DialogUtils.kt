package com.example.myinventory.presentation.utils


import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

object DialogUtils {

    //use this function if you want to show AlertDialog immediately
    fun showDialog(
        context: Context?,
        title: String,
        message: String = "",
        positiveButton: String = "Ok",
        negativeButton: String = "",
        neutralButton: String = "",
        isCancelable: Boolean = true,
        clickListener: DialogInterface.OnClickListener? = null,
        cancelListener: DialogInterface.OnCancelListener? = null,
        dismissListener: DialogInterface.OnDismissListener? = null,
    ): AlertDialog? {

        val builder = buildDialog(
            context,
            title,
            message,
            positiveButton,
            negativeButton,
            neutralButton,
            isCancelable,
            clickListener,
            cancelListener,
            dismissListener
        )

        return builder?.show()
    }

    //use this function if you want to display dialog later by later calling builder.show()
    fun buildDialog(
        context: Context?,
        title: String,
        message: String = "",
        positiveButton: String = "Ok",
        negativeButton: String = "",
        neutralButton: String = "",
        isCancelable: Boolean = true,
        clickListener: DialogInterface.OnClickListener? = null,
        cancelListener: DialogInterface.OnCancelListener? = null,
        dismissListener: DialogInterface.OnDismissListener? = null,
    ): AlertDialog.Builder? {

        if (context == null || title == "")
            return null

        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)

        if (message != "")
            builder.setMessage(message)

        builder.setPositiveButton(positiveButton, clickListener)

        if (negativeButton != "")
            builder.setNegativeButton(negativeButton, clickListener)

        if (neutralButton != "")
            builder.setNeutralButton(neutralButton, clickListener)

        builder.setCancelable(isCancelable)
        builder.setOnCancelListener(cancelListener)         // when user presses back button or click outside the dialog
        builder.setOnDismissListener(dismissListener)       //when dialog.dismiss() is called explicitly and also when back is pressed

        return builder
    }

    fun showDialog(dialog: AlertDialog?) {
        dialog?.show()
    }

    fun hideDialog(dialog: AlertDialog?) {
        dialog?.hide()
    }

    fun dismissDialog(dialog: AlertDialog?) {
        dialog?.dismiss()
    }

}


