package com.android.app.fancytextreplacer.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.app.fancytextreplacer.R;
import com.android.app.fancytextreplacer.utils.DialogCustomProgress;
import com.android.app.fancytextreplacer.utils.GlobalFunction;

public class CloneTextActivity extends AppCompatActivity {
    String result = "";
    ImageView icBackOrMenu;
    TextView toolbarTitle;
    TextView tvOutput;
    TextView tvCloneText;
    LinearLayout outputLayout;
    EditText edtInput, edtCloneLimit;
    LinearLayout shareLayout, copyLayout;
    GlobalFunction globalFunction;

    DialogCustomProgress dialog;

    public static final String TAG = CloneTextActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clone_text);

        icBackOrMenu = findViewById(R.id.icBackOrMenu);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Clone Text");

        icBackOrMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tvCloneText = findViewById(R.id.tvCloneText);
        tvOutput = findViewById(R.id.tvOutput);
        edtInput = findViewById(R.id.edtInput);
        edtCloneLimit = findViewById(R.id.edtCloneLimit);
        shareLayout = findViewById(R.id.shareLayout);
        copyLayout = findViewById(R.id.copyLayout);
        outputLayout = findViewById(R.id.outputLayout);
        globalFunction = new GlobalFunction(this);

        dialog = new DialogCustomProgress(this);

        tvCloneText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(CloneTextActivity.this);
                String input = edtInput.getText().toString().trim();
                String num = edtCloneLimit.getText().toString().trim();
                if (!num.isEmpty()) {
                    int limit = Integer.parseInt(num);
                    if (limit <= 10000) {
                        if (!input.isEmpty() && !num.isEmpty()) {
                            dialog.show();
                            tvCloneText.setEnabled(false);
                            cloneTextLatest(input, limit);
                        } else {
                            outputLayout.setVisibility(View.GONE);
                            Toast.makeText(CloneTextActivity.this, "Please enter input and limit", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CloneTextActivity.this, "Clone limit crossed to 10K", Toast.LENGTH_SHORT).show();
                    }
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

    private void cloneTextLatest(String input, int limit) {
        result = "";
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < limit; i++) {
                    result = result + input + "\n";
                }
                String finalResult = result;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismissProgressDialog();
                        tvOutput.setText(finalResult);
                        outputLayout.setVisibility(View.VISIBLE);
                        tvCloneText.setEnabled(true);
                    }
                });
            }
        };
        thread.start();
    }

    private void cloneText(String input, int limit) {
        result = "";
        if (limit == 2000) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < limit; i++) {
                        result = result + input + "\n";
                    }
                    String finalResult = result;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismissProgressDialog();
                            tvOutput.setText(finalResult);
                            outputLayout.setVisibility(View.VISIBLE);
                            tvCloneText.setEnabled(true);
                        }
                    });
                }
            };
            thread.start();
        } else {
            int loopVar = 0;
            int reminder = limit % 2;
            if (reminder > 0) {
                loopVar = ((limit - 1) / 5);
            } else {
                loopVar = limit / 5;
            }
            Log.d(TAG, "cloneText: " + loopVar);


            int finalLoopVar = loopVar;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < finalLoopVar; i++) {
                        result = result + input + "\n";
                    }
                    String result2 = "";

                    if (reminder > 0) {
                        result2 = result + result + result + result + result + input;
                    } else {
                        result2 = result + result + result + result + result;
                    }

                    String finalResult = result2;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismissProgressDialog();
                            tvCloneText.setEnabled(true);
                            tvOutput.setText(finalResult);
                            outputLayout.setVisibility(View.VISIBLE);
                        }
                    });
                }
            };
            thread.start();
        }
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