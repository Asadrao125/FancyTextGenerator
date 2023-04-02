package com.android.app.fancytextreplacer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.app.fancytextreplacer.R;
import com.android.app.fancytextreplacer.adapter.StylishTextAdapter;
import com.android.app.fancytextreplacer.font_utils.FontsGenerator;

import java.util.ArrayList;

public class StylishTextActivity extends AppCompatActivity implements TextWatcher {
    RecyclerView fontsRV;
    EditText editText;
    static final String KEY = "FontActivity";
    StylishTextAdapter mAdapter;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    ImageView close, symbolButton;
    FontsGenerator mGenerator;
    ImageView icBackOrMenu;
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylish_text);

        icBackOrMenu = findViewById(R.id.icBackOrMenu);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Stylish Text");

        icBackOrMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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

    private void convertText(String inp) {
        if (inp.isEmpty()) inp = "Stylish Text";
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