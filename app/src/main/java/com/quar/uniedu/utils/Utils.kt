package com.quar.uniedu.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.http.HttpException
import android.widget.Toast
import com.quar.uniedu.network.Resource
import com.quar.uniedu.ui.introduction.IntroActivity

fun Activity.openActivity(cls: Class<*>) {
    startActivity(Intent(this, cls))
}

fun String.username(): String {
    return if (this.indexOf("@") != -1)
        this.substring(0, this.indexOf("@") - 1)
    else this
}

fun Activity.message(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.logout() {
    val builder = AlertDialog.Builder(this)

    builder.apply {
        setTitle("Log out")
        setMessage("Do you want exit?")
        builder.setCancelable(false)
        setPositiveButton("Yes") { dialog, _ ->
            Prefs(context).isLogin = false
            dialog.dismiss()
            startActivity(Intent(this@logout, IntroActivity::class.java))
        }
        setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
    }.create().show()
}



