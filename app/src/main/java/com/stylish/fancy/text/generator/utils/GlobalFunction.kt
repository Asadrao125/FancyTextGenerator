package com.stylish.fancy.text.generator.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.stylish.fancy.text.generator.R

class GlobalFunction(var activity: Activity) {

    fun copyOutput(result: String?) {
        val clipboard = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", result)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(activity, "Output Copied", Toast.LENGTH_SHORT).show()
    }

    fun shareMsg(msg: String?) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg)
        sendIntent.type = "text/plain"
        activity.startActivity(sendIntent)
    }

    fun showToast(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun intentBrowser(linkToOpen: String?) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkToOpen))
            activity.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun sendToGmail() {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            val recipients = arrayOf("venture.technologies001@gmail.com")
            intent.putExtra(Intent.EXTRA_EMAIL, recipients)
            intent.putExtra(
                Intent.EXTRA_SUBJECT,
                "Feedback For " + activity.getString(R.string.app_name)
            )
            intent.putExtra(
                Intent.EXTRA_TEXT, """
     Dear Development Team
     Greetings,
     
     """.trimIndent()
            )
            intent.type = "text/html"
            intent.setPackage("com.google.android.gm")
            activity.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    fun shareApp() {
        try {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Hey check out " + activity.getString(R.string.app_name) +
                        ": " + activity.getString(R.string.appLink)
            )
            sendIntent.type = "text/plain"
            activity.startActivity(sendIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}