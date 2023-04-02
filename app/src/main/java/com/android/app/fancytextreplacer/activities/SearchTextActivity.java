package com.android.app.fancytextreplacer.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.app.fancytextreplacer.R;
import com.android.app.fancytextreplacer.utils.GlobalFunction;

public class SearchTextActivity extends AppCompatActivity {
    EditText edtInput, edtSearchFor;
    TextView tvOutput;
    ImageView icBackOrMenu;
    TextView toolbarTitle;
    LinearLayout outputLayout;
    TextView tvSearchText;
    CheckBox cbLowercase, cbCountWithSpaces;
    LinearLayout shareLayout, copyLayout;
    String result;
    GlobalFunction globalFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_text);

        icBackOrMenu = findViewById(R.id.icBackOrMenu);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Search Text");

        icBackOrMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        edtInput = findViewById(R.id.edtInput);
        tvOutput = findViewById(R.id.tvOutput);
        tvSearchText = findViewById(R.id.tvSearchText);
        cbLowercase = findViewById(R.id.cbLowercase);
        cbCountWithSpaces = findViewById(R.id.cbCountWithSpaces);
        edtSearchFor = findViewById(R.id.edtSearchFor);
        shareLayout = findViewById(R.id.shareLayout);
        copyLayout = findViewById(R.id.copyLayout);
        outputLayout = findViewById(R.id.outputLayout);
        globalFunction = new GlobalFunction(this);

        tvSearchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(SearchTextActivity.this);
                String input = edtInput.getText().toString().trim();
                String searchFor = edtSearchFor.getText().toString().trim();
                String temp = input;
                if (!input.isEmpty() && !searchFor.isEmpty()) {
                    outputLayout.setVisibility(View.VISIBLE);
                    if (cbLowercase.isChecked()) {
                        input = input.toLowerCase();
                        tvOutput.setText(input);
                        result = input;
                    } else {
                        tvOutput.setText(input);
                        result = input;
                    }

                    if (cbCountWithSpaces.isChecked()) {
                        Log.d("lkjhg", "onClick: " + removeSpace(temp));
                        tvOutput.setText(removeSpace(temp));
                        result = removeSpace(temp);
                    } else {
                        tvOutput.setText(input);
                        result = input;
                    }

                    if (input.contains(searchFor)) {
                        input = input.replaceAll(searchFor, "<font color='#EE0000'>" + searchFor + "</font>");
                        tvOutput.setText(Html.fromHtml(input));
                        result = "" + Html.fromHtml(input);
                    } else {
                        Toast.makeText(SearchTextActivity.this, "No Text Found: " + input, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    outputLayout.setVisibility(View.GONE);
                    Toast.makeText(SearchTextActivity.this, "Please enter input", Toast.LENGTH_SHORT).show();
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

    public static String removeSpace(String s) {
        String withoutspaces = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ')
                withoutspaces += s.charAt(i);

        }
        return withoutspaces;
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

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}