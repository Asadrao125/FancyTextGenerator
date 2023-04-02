package com.android.app.fancytextreplacer.activities;

import android.app.Activity;
import android.os.Bundle;
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

public class ReverseTextActivity extends AppCompatActivity {
    TextView tvReverseText;
    ImageView icBackOrMenu;
    TextView toolbarTitle;
    EditText edtInput;
    TextView tvOutput;
    LinearLayout shareLayout, copyLayout;
    GlobalFunction globalFunction;
    LinearLayout outputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_text);

        icBackOrMenu = findViewById(R.id.icBackOrMenu);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Reverse Text");

        icBackOrMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tvReverseText = findViewById(R.id.tvReverseText);
        edtInput = findViewById(R.id.edtInput);
        tvOutput = findViewById(R.id.tvOutput);
        shareLayout = findViewById(R.id.shareLayout);
        copyLayout = findViewById(R.id.copyLayout);
        outputLayout = findViewById(R.id.outputLayout);
        globalFunction = new GlobalFunction(this);

        tvReverseText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(ReverseTextActivity.this);
                String input = edtInput.getText().toString().trim();
                if (!input.isEmpty()) {
                    outputLayout.setVisibility(View.VISIBLE);
                    StringBuffer buffer = new StringBuffer(input);
                    tvOutput.setText(buffer.reverse());
                } else {
                    outputLayout.setVisibility(View.GONE);
                    Toast.makeText(ReverseTextActivity.this, "Please enter input", Toast.LENGTH_SHORT).show();
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