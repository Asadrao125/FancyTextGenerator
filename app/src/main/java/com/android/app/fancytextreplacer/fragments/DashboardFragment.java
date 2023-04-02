package com.android.app.fancytextreplacer.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.cardview.widget.CardView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.fancytextreplacer.R;
import com.android.app.fancytextreplacer.activities.CloneTextActivity;
import com.android.app.fancytextreplacer.activities.CountTextActivity;
import com.android.app.fancytextreplacer.activities.MarkTextActivity;
import com.android.app.fancytextreplacer.activities.ReplaceTextActivity;
import com.android.app.fancytextreplacer.activities.SearchTextActivity;
import com.android.app.fancytextreplacer.activities.StylishTextActivity;

public class DashboardFragment extends Fragment {
    CardView cvReplace, cvSearch, cvCount, cvClone, cvStylishText, cvMark;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        cvReplace = view.findViewById(R.id.cvReplace);
        cvSearch = view.findViewById(R.id.cvSearch);
        cvCount = view.findViewById(R.id.cvCount);
        cvClone = view.findViewById(R.id.cvClone);
        cvStylishText = view.findViewById(R.id.cvStylishText);
        cvMark = view.findViewById(R.id.cvMark);

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
        return view;
    }
}