package com.stylish.fancy.text.generator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stylish.fancy.text.generator.R;
import com.stylish.fancy.text.generator.adapter.SelectEmoticonsAdapter;
import com.stylish.fancy.text.generator.utils.StaticMethods;

public class SelectEmoticonsActivity extends AppCompatActivity {
    RecyclerView rvEmoticons;
    ImageView icBackOrMenu;
    SelectEmoticonsAdapter emoticonsAdapter;
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_emoticons);

        emoticonsAdapter = new SelectEmoticonsAdapter(this);
        rvEmoticons = findViewById(R.id.rvEmoticons);
        rvEmoticons.setLayoutManager(new GridLayoutManager(this, 2));
        rvEmoticons.setHasFixedSize(true);
        rvEmoticons.setAdapter(emoticonsAdapter);
        emoticonsAdapter.setData(StaticMethods.getEmoticons());

        icBackOrMenu = findViewById(R.id.icBackOrMenu);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Emoticons");

        icBackOrMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}