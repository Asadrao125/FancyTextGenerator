package com.stylish.fancy.text.generator.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.stylish.fancy.text.generator.R

class DialogCustomProgress(var activity: Activity) : Dialog(activity) {
    private lateinit var progressDialog: LinearLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        setContentView(R.layout.custom_progress_dialog_style)
        initUI()
    }

    private fun initUI() {
        progressDialog = findViewById<View>(R.id.ll_progress_dialog) as LinearLayout
        progressBar = findViewById<View>(R.id.progressBar2) as ProgressBar
        progressBar.indeterminateDrawable.setColorFilter(
            activity.resources.getColor(R.color.bottom_nav_tint),
            PorterDuff.Mode.MULTIPLY
        )
    }

    fun dismissProgressDialog() {
        dismiss()
    }
}