package org.prikic.alarmmanagertest;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class DialogActivity extends AppCompatActivity {

    private TextView txtMillis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        txtMillis = findViewById(R.id.txtMillis);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                dismissActivityIfFinished(bundle);
            }
        }

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        View view = getWindow().getDecorView();
        WindowManager.LayoutParams lp = (WindowManager.LayoutParams) view.getLayoutParams();
        lp.gravity = Gravity.BOTTOM;
        getWindowManager().updateViewLayout(view, lp);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            Log.e("test", "bundle is null");
            return;
        }

        dismissActivityIfFinished(bundle);
    }

    private void setTxtMillis(Bundle bundle) {
        int seconds = bundle.getInt("millis");
        Resources resources = getResources();
        txtMillis.setText(resources.getQuantityString(
                R.plurals.txt_millis_label,
                seconds,
                seconds));
    }

    private void dismissActivityIfFinished(Bundle bundle) {
        boolean isFinished = bundle.getBoolean("isFinished");
        if (isFinished) {
            finish();
        } else {
            setTxtMillis(bundle);
        }
    }
}