package com.android.app.fancytextreplacer.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class GlobalFunction {
    Activity activity;

    public GlobalFunction(Activity activity) {
        this.activity = activity;
    }

    public void copyOutput(String result) {
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", result);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(activity, "Output Copied", Toast.LENGTH_SHORT).show();
    }

    public void shareMsg(String msg) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setType("text/plain");
        activity.startActivity(sendIntent);
    }
}
