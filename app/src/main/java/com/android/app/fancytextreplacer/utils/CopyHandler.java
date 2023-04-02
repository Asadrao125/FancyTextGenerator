package com.android.app.fancytextreplacer.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CopyHandler {
    Context context;

    public CopyHandler(Context context) {
        this.context = context;
    }

    public void copyText(String data) {
        if (data.isEmpty()) {
            Toast.makeText(context, "Enter some text", Toast.LENGTH_SHORT).show();
            return;
        }
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        Toast.makeText(context, "Copied to clipboard! Your copied text is\n" + data, Toast.LENGTH_SHORT).show();
        ClipData clip = ClipData.newPlainText("simple text", data);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(clip);
        }
    }

    public void shareText(String data) {
        if (data.isEmpty()) {
            Toast.makeText(context, "Enter some text", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, data);
            context.startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }
}
