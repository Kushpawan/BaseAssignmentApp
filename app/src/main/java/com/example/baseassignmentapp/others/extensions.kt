package com.example.baseassignmentapp.others

import android.content.Context
import android.text.Editable

fun showProgress(context: Context, message: String? = null) {
    LoadingUtils.showDialog(context, message)
}

fun hideProgress() {
    LoadingUtils.hideDialog()
}

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
