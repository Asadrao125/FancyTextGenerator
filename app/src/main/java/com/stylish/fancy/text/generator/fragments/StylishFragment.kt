package com.stylish.fancy.text.generator.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stylish.fancy.text.generator.R;
import com.stylish.fancy.text.generator.adapter.StylishTextAdapter;
import com.stylish.fancy.text.generator.font_utils.FontsGenerator;

import java.util.ArrayList;
import java.util.Locale;

public class StylishFragment extends Fragment implements TextWatcher {

    RecyclerView fontsRV;
    EditText editText;
    static final String KEY = "FontActivity";
    StylishTextAdapter mAdapter;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    ImageView close, symbolButton, micBtn;
    FontsGenerator mGenerator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stylish, container, false);

        mGenerator = new FontsGenerator(getActivity());

        close = view.findViewById(R.id.closebtn);
        symbolButton = view.findViewById(R.id.symbols);
        micBtn = view.findViewById(R.id.micBtn);

        fontsRV = view.findViewById(R.id.recycle_view_FF);
        fontsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        editText = view.findViewById(R.id.edit_text_FF);
        fontsRV.setHasFixedSize(true);
        mAdapter = new StylishTextAdapter(getActivity());
        fontsRV.setAdapter(mAdapter);
        editText.addTextChangedListener(this);

        restoreText();

        micBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startForResult();
            }
        });

        close.setOnClickListener(v -> {
            int length = editText.getText().length();
            if (length > 0) {
                editText.getText().delete(length - 1, length);
            }
        });

        close.setOnLongClickListener(v -> {
            editText.getText().clear();
            return false;
        });
        return view;
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
    public void onDestroyView() {
        super.onDestroyView();
        sharedPreferences = getActivity().getSharedPreferences("MyPre", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        editor.putString(KEY + 1, editText.getText().toString()).apply();
        super.onDestroy();
    }

    private void restoreText() {
        sharedPreferences = getActivity().getSharedPreferences("MyPre", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        editText.setText(sharedPreferences.getString(KEY + 1, ""));
    }
}