package com.android.app.fancytextreplacer.activities;

import android.app.Activity;
import android.os.Bundle;
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

public class ReplaceTextActivity extends AppCompatActivity {
    EditText edtInput, edtFindText, edtReplaceWith;
    TextView tvReplaceText;
    ImageView icBackOrMenu;
    TextView toolbarTitle;
    GlobalFunction globalFunction;
    TextView tvOutput, tvInputWord, tvOutputWord, tvInputLetter, tvOutputLetter;
    CheckBox cbLowercase, cbCountWord, cbCountLetter, cbReverseText;
    LinearLayout shareLayout, copyLayout;
    LinearLayout inputWordLayout, outputWordLayout, inputLetterLayout, outputLetterLayout;
    String result;
    LinearLayout outputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_text);

        icBackOrMenu = findViewById(R.id.icBackOrMenu);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Replace Text");

        icBackOrMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        edtInput = findViewById(R.id.edtInput);
        edtFindText = findViewById(R.id.edtFindText);
        edtReplaceWith = findViewById(R.id.edtReplaceWith);
        tvReplaceText = findViewById(R.id.tvReplaceText);
        cbLowercase = findViewById(R.id.cbLowercase);
        cbCountWord = findViewById(R.id.cbCountWord);
        cbCountLetter = findViewById(R.id.cbCountLetter);
        cbReverseText = findViewById(R.id.cbReverseText);
        tvOutput = findViewById(R.id.tvOutput);
        tvInputLetter = findViewById(R.id.tvInputLetter);
        tvOutputLetter = findViewById(R.id.tvOutputLetter);
        tvInputWord = findViewById(R.id.tvInputWord);
        tvOutputWord = findViewById(R.id.tvOutputWord);
        shareLayout = findViewById(R.id.shareLayout);
        copyLayout = findViewById(R.id.copyLayout);
        outputLayout = findViewById(R.id.outputLayout);
        inputWordLayout = findViewById(R.id.inputWordLayout);
        outputWordLayout = findViewById(R.id.outputWordLayout);
        inputLetterLayout = findViewById(R.id.inputLetterLayout);
        outputLetterLayout = findViewById(R.id.outputLetterLayout);
        globalFunction = new GlobalFunction(this);

        tvReplaceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(ReplaceTextActivity.this);
                if (!edtInput.getText().toString().trim().isEmpty()) {

                    String originalText = edtInput.getText().toString().trim();
                    String findText = edtFindText.getText().toString().trim();

                    if (originalText.contains(findText)) {
                        processText();
                        outputLayout.setVisibility(View.VISIBLE);
                    } else {
                        outputLayout.setVisibility(View.GONE);
                        Toast.makeText(ReplaceTextActivity.this, "No Text Found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        shareLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tvOutput.getText().toString().equals("Output")) {
                    globalFunction.shareMsg(result);
                } else {
                    Toast.makeText(getApplicationContext(), "No Output To Share", Toast.LENGTH_SHORT).show();
                }
            }
        });

        copyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tvOutput.getText().toString().equals("Output")) {
                    globalFunction.copyOutput(result);
                } else {
                    Toast.makeText(getApplicationContext(), "No Output To Share", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void processText() {
        String originalText = edtInput.getText().toString().trim();
        String findText = edtFindText.getText().toString().trim();
        String replaceText = edtReplaceWith.getText().toString().trim();

        String outputText = "";
        String inputText = originalText;

        if (cbLowercase.isChecked()) {
            inputText = inputText.toLowerCase();
        }

        if (cbReverseText.isChecked()) {
            StringBuffer buffer = new StringBuffer(inputText);
            inputText = buffer.reverse().toString();
        }

        outputText = inputText.replaceAll(findText, replaceText);

        if (cbCountWord.isChecked()) {
            inputWordLayout.setVisibility(View.VISIBLE);
            outputWordLayout.setVisibility(View.VISIBLE);
        } else {
            inputWordLayout.setVisibility(View.GONE);
            outputWordLayout.setVisibility(View.GONE);
        }

        if (cbCountLetter.isChecked()) {
            inputLetterLayout.setVisibility(View.VISIBLE);
            outputLetterLayout.setVisibility(View.VISIBLE);
        } else {
            inputLetterLayout.setVisibility(View.GONE);
            outputLetterLayout.setVisibility(View.GONE);
        }

        tvOutput.setText(outputText);
        tvOutputLetter.setText("" + outputText.replaceAll(" ", "").length());
        tvInputLetter.setText("" + inputText.replaceAll(" ", "").length());
        tvOutputWord.setText("" + countWords(outputText));
        tvInputWord.setText("" + countWords(originalText));
        result = outputText;
    }

    public int countWords(String text) {
        text = text.replaceAll("\\s{2,}", " ").trim();
        String words[] = text.trim().split(" ");
        return words.length;
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