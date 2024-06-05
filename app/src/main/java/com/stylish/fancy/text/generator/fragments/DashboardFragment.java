package com.stylish.fancy.text.generator.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.stylish.fancy.text.generator.R;
import com.stylish.fancy.text.generator.activities.CloneTextActivity;
import com.stylish.fancy.text.generator.activities.CountTextActivity;
import com.stylish.fancy.text.generator.activities.FavouritesActivity;
import com.stylish.fancy.text.generator.activities.SelectEmoticonsActivity;
import com.stylish.fancy.text.generator.activities.MarkTextActivity;
import com.stylish.fancy.text.generator.activities.ReplaceTextActivity;
import com.stylish.fancy.text.generator.activities.SearchTextActivity;
import com.stylish.fancy.text.generator.activities.StylishTextActivity;
import com.stylish.fancy.text.generator.activities.TextToEmojiActivity;

public class DashboardFragment extends Fragment {
    CardView cvReplace, cvSearch, cvCount, cvClone, cvStylishText, cvMark, cvEmoticons, cvTextToEmoji, cvFavourites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        cvReplace = view.findViewById(R.id.cvReplace);
        cvSearch = view.findViewById(R.id.cvSearch);
        cvCount = view.findViewById(R.id.cvCount);
        cvClone = view.findViewById(R.id.cvClone);
        cvStylishText = view.findViewById(R.id.cvStylishText);
        cvEmoticons = view.findViewById(R.id.cvEmoticons);
        cvMark = view.findViewById(R.id.cvMark);
        cvTextToEmoji = view.findViewById(R.id.cvTextToEmoji);
        cvFavourites = view.findViewById(R.id.cvFavourites);

        cvReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ReplaceTextActivity.class));
            }
        });

        cvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SearchTextActivity.class));
            }
        });

        cvCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CountTextActivity.class));
            }
        });

        cvClone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CloneTextActivity.class));
            }
        });

        cvStylishText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), StylishTextActivity.class));
            }
        });

        cvMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MarkTextActivity.class));
            }
        });

        cvEmoticons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SelectEmoticonsActivity.class));
            }
        });

        cvTextToEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TextToEmojiActivity.class));
            }
        });

        cvFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FavouritesActivity.class));
            }
        });
        return view;
    }
}