package com.android.app.fancytextreplacer.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import com.android.app.fancytextreplacer.R;
import com.android.app.fancytextreplacer.fragments.DashboardFragment;
import com.android.app.fancytextreplacer.fragments.StylishFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.kaelli.niceratingbar.NiceRatingBar;
import com.kaelli.niceratingbar.OnRatingChangedListener;

public class HomeActivity extends AppCompatActivity {
    ChipNavigationBar chipNavBar;
    ImageView icBackOrMenu, icMenu;
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        icBackOrMenu = findViewById(R.id.icBackOrMenu);
        icMenu = findViewById(R.id.icMenu);

        icBackOrMenu.setVisibility(View.GONE);
        icMenu.setVisibility(View.VISIBLE);

        icMenu.setOnClickListener(v -> {
            showStatusPopup();
        });

        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Stylish Text");
        chipNavBar = findViewById(R.id.chipNavBar);

        changeFragment(new StylishFragment());
        chipNavBar.setItemSelected(R.id.stylishMenu, true);

        chipNavBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                if (i == R.id.homeMenu) {
                    toolbarTitle.setText("Home");
                    fragment = new DashboardFragment();
                } else {
                    toolbarTitle.setText("Stylish Text");
                    fragment = new StylishFragment();
                }
                changeFragment(fragment);
            }
        });
    }

    private void showStatusPopup() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popup_layout, null);
        PopupWindow popupWindow = new PopupWindow(view, 350, ConstraintLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAsDropDown(icMenu, 0, 0);

        LinearLayout shareApp = view.findViewById(R.id.shareApp);
        LinearLayout privacyPolicy = view.findViewById(R.id.privacyPolicy);
        LinearLayout checkUpdate = view.findViewById(R.id.checkUpdate);
        LinearLayout feedback = view.findViewById(R.id.feedback);
        LinearLayout moreApps = view.findViewById(R.id.moreApps);
        LinearLayout rateApp = view.findViewById(R.id.rateUs);

        shareApp.setOnClickListener(v -> {
            popupWindow.dismiss();
            Toast.makeText(this, "Share App", Toast.LENGTH_SHORT).show();
        });

        privacyPolicy.setOnClickListener(v -> {
            popupWindow.dismiss();
            Toast.makeText(this, "Privacy Policy", Toast.LENGTH_SHORT).show();
        });

        checkUpdate.setOnClickListener(v -> {
            popupWindow.dismiss();
            Toast.makeText(this, "Check Update", Toast.LENGTH_SHORT).show();
        });

        feedback.setOnClickListener(v -> {
            popupWindow.dismiss();
            Toast.makeText(this, "Feedback App", Toast.LENGTH_SHORT).show();
        });

        rateApp.setOnClickListener(v -> {
            popupWindow.dismiss();
            Toast.makeText(this, "Rate App", Toast.LENGTH_SHORT).show();
        });

        moreApps.setOnClickListener(v -> {
            popupWindow.dismiss();
            Toast.makeText(this, "More App", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
        });
    }

    private void changeFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void showDialog() {
        final float[] rating = {0};
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_exit, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        TextView tvExit = dialogView.findViewById(R.id.tvExit);
        TextView tvRateUs = dialogView.findViewById(R.id.tvRateUs);

        NiceRatingBar niceRatingBar = dialogView.findViewById(R.id.niceRatingBar);

        tvExit.setOnClickListener(v -> {
            finish();
        });

        niceRatingBar.setOnRatingChangedListener(new OnRatingChangedListener() {
            @Override
            public void onRatingChanged(float starRating) {
                rating[0] = starRating;
            }
        });

        tvRateUs.setOnClickListener(v -> {
            if (rating[0] == 0) {
                Toast.makeText(this, "Please select stars for rating", Toast.LENGTH_SHORT).show();
            } else if (rating[0] > 0 && rating[0] < 4) {
                Toast.makeText(this, "Will redirect to email", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        showDialog();
    }
}