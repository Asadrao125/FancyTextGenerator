package com.android.app.fancytextreplacer.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.app.fancytextreplacer.R;
import com.android.app.fancytextreplacer.utils.GlobalFunction;

import org.w3c.dom.Text;

public class MarkTextActivity extends AppCompatActivity {
    TextView tvOutput;
    String result;

    ImageView icBackOrMenu;
    TextView toolbarTitle;
    TextView tvnMarkText;
    LinearLayout outputLayout;
    EditText edtInput, edtTotalWord;
    GlobalFunction globalFunction;
    LinearLayout shareLayout, copyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_text);

        icBackOrMenu = findViewById(R.id.icBackOrMenu);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Mark Text");

        icBackOrMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tvOutput = findViewById(R.id.tvOutput);
        edtInput = findViewById(R.id.edtInput);
        edtTotalWord = findViewById(R.id.edtTotalWord);
        tvnMarkText = findViewById(R.id.tvnMarkText);
        shareLayout = findViewById(R.id.shareLayout);
        copyLayout = findViewById(R.id.copyLayout);
        outputLayout = findViewById(R.id.outputLayout);
        globalFunction = new GlobalFunction(this);

        tvnMarkText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(MarkTextActivity.this);
                String input = edtInput.getText().toString();
                String num = edtTotalWord.getText().toString().trim();
                if (!input.isEmpty() && !num.isEmpty()) {
                    outputLayout.setVisibility(View.VISIBLE);
                    int totalWord = Integer.parseInt(num);
                    if (totalWord <= input.length()) {
                        String val = input.substring(0, totalWord);
                        String newS = input.replace(val, "");
                        String next = "<font color='#EE0000'>" + val + "</font>";
                        tvOutput.setText(Html.fromHtml(next + newS));
                        result = String.valueOf(Html.fromHtml(next + newS));
                    } else {
                        outputLayout.setVisibility(View.GONE);
                        Toast.makeText(MarkTextActivity.this, "Please donot enter limit greater than input length", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    outputLayout.setVisibility(View.GONE);
                    Toast.makeText(MarkTextActivity.this, "Please enter input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        shareLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tvOutput.getText().toString().equals("Output")) {
                    globalFunction.shareMsg(tvOutput.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "No Output To Share", Toast.LENGTH_SHORT).show();
                }
            }
        });

        copyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tvOutput.getText().toString().equals("Output")) {
                    globalFunction.copyOutput(tvOutput.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "No Output To Share", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}