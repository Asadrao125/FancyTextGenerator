package com.android.app.fancytextreplacer.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.app.fancytextreplacer.R;
import com.android.app.fancytextreplacer.adapter.StylishTextAdapter;
import com.android.app.fancytextreplacer.font_utils.FontsGenerator;

import java.util.ArrayList;

public class StylishFragment extends Fragment implements TextWatcher {

    RecyclerView fontsRV;
    EditText editText;
    static final String KEY = "FontActivity";
    StylishTextAdapter mAdapter;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    ImageView close, symbolButton;
    FontsGenerator mGenerator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stylish, container, false);

        mGenerator = new FontsGenerator(getActivity());

        close = view.findViewById(R.id.closebtn);
        symbolButton = view.findViewById(R.id.symbols);

        fontsRV = view.findViewById(R.id.recycle_view_FF);
        fontsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        editText = view.findViewById(R.id.edit_text_FF);
        fontsRV.setHasFixedSize(true);
        mAdapter = new StylishTextAdapter(getActivity());
        fontsRV.setAdapter(mAdapter);
        editText.addTextChangedListener(this);

        restoreText();

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