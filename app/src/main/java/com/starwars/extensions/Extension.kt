package com.starwars.extensions

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.starwars.R
import kotlin.reflect.KClass

////////////////////////////////////////

fun hideKeyboard(context: Context, view: View) {
    try {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(view.windowToken, 0)
    } catch (e: Exception) {
        Log.d("Exception", e.message)
    }
}

fun openKeyboard(context: Context, view: View) {
    try {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    } catch (e: Exception) {
        Log.d("Exception", e.message)
    }
}

fun showAlertDialog(context: Context, title: String, message: String) {
    AlertDialog.Builder(context, R.style.CustomDialogTheme)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
        .show()
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun <T : Activity> KClass<T>.start(activity: Activity, finish: Boolean = false) {
    Intent(activity, this.java).apply {
        activity.startActivity(this)
    }
    when {
        finish ->
            activity.finish()
    }
}

@BindingAdapter("imageUrl")
fun bindRecyclerViewAdapter(imageView: ImageView, imageUrl: String?) {
    if (imageUrl != null) {
        // If we don't do this, you'll see the old image appear briefly
        // before it's replaced with the current image

        val requestOptions = RequestOptions()
        if(imageView.id == R.id.photo_image){
            requestOptions.apply {
                placeholder(R.drawable.loading)
            }
        }

        Glide.with(imageView)
            .setDefaultRequestOptions(requestOptions)
            .load("https://starwars-visualguide.com/assets/img$imageUrl.jpg")
            .into(imageView)
    }

}

const val anErrorOccured = "An error occurred"


