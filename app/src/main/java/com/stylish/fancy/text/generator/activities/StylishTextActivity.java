package com.stylish.fancy.text.generator.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stylish.fancy.text.generator.R;
import com.stylish.fancy.text.generator.adapter.StylishTextAdapter;
import com.stylish.fancy.text.generator.font_utils.FontsGenerator;

import java.util.ArrayList;
import java.util.Locale;

public class StylishTextActivity extends AppCompatActivity implements TextWatcher {
    RecyclerView fontsRV;
    EditText editText;
    static final String KEY = "FontActivity";
    StylishTextAdapter mAdapter;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    ImageView close, symbolButton;
    FontsGenerator mGenerator;
    ImageView icBackOrMenu, micBtn;
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylish_text);

        icBackOrMenu = findViewById(R.id.icBackOrMenu);
        micBtn = findViewById(R.id.micBtn);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(getString(R.string.stylish_text));

        icBackOrMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        micBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startForResult();
            }
        });

        mGenerator = new FontsGenerator(this);

        close = findViewById(R.id.closebtn);
        symbolButton = findViewById(R.id.symbols);

        fontsRV = findViewById(R.id.recycle_view_FF);
        fontsRV.setLayoutManager(new LinearLayoutManager(this));
        editText = findViewById(R.id.edit_text_FF);
        fontsRV.setHasFixedSize(true);
        mAdapter = new StylishTextAdapter(this);
        fontsRV.setAdapter(mAdapter);
        editText.addTextChangedListener(this);

        restoreText();

        //Text Delete from EditText
        close.setOnClickListener(v -> {
            int length = editText.getText().length();
            if (length > 0) {
                editText.getText().delete(length - 1, length);
            }
        });

        //All Text Delete from EditText
        close.setOnLongClickListener(v -> {
            editText.getText().clear();
            return false;
        });
    }

    private void startForResult() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi Speak Something");
        activityResultLauncher.launch(intent);
    }

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        editText.setText(results.get(0));
                    }
                }
            }
    );

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && requestCode == 123) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            editText.setText(results.get(0));
        }
    }

    private void convertText(String inp) {
        if (inp.isEmpty()) inp = "Fancy Text";
        ArrayList<String> translate = mGenerator.generate(inp);
        mAdapter.setData(translate);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        convertText(editText.getText().toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    protected void onDestroy() {
        sharedPreferences = getSharedPreferences("MyPre", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        editor.putString(KEY + 1, editText.getText().toString()).apply();
        super.onDestroy();
    }

    private void restoreText() {
        sharedPreferences = getSharedPreferences("MyPre", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        editText.setText(sharedPreferences.getString(KEY + 1, ""));
    }
}