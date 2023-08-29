package com.example.throw_fornt.util.common

import android.view.View
import androidx.annotation.StringRes
import com.example.throw_fornt.R
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(
    @StringRes
    textId: Int,
    @StringRes
    actionId: Int = R.string.all_snackbar_default_action,
    action: () -> Unit = {},
) {
    Snackbar.make(this, context.getString(textId), Snackbar.LENGTH_SHORT)
        .setAction(actionId) {
            action()
        }
        .show()
}

fun View.showSnackbar(
    message: String,
    actionMessage: String,
    action: () -> Unit = {},
) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
        .setAction(actionMessage) {
            action()
        }
        .show()
}
