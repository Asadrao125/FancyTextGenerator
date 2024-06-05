package com.stylish.fancy.text.generator.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CopyHandler(var context: Context) {

    fun copyText(data: String) {
        if (data.isEmpty()) {
            Toast.makeText(context, "Enter some text", Toast.LENGTH_SHORT).show()
            return
        }
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        Toast.makeText(context, "Copied to clipboard!", Toast.LENGTH_SHORT).show()
        val clip = ClipData.newPlainText("simple text", data)
        clipboardManager.setPrimaryClip(clip)
    }

    fun shareText(data: String) {
        if (data.isEmpty()) {
            Toast.makeText(context, "Enter some text", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_TEXT, data)
            context.startActivity(Intent.createChooser(i, "choose one"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}