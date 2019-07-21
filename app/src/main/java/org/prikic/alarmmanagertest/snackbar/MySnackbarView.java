package org.prikic.alarmmanagertest.snackbar;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.snackbar.ContentViewCallback;
import android.util.AttributeSet;
import android.view.View;

import org.prikic.alarmmanagertest.R;

public class MySnackbarView extends ConstraintLayout implements ContentViewCallback {

    public MySnackbarView(Context context) {
        super(context);
        initSnackbarView(context);
    }

    public MySnackbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSnackbarView(context);
    }

    public MySnackbarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSnackbarView(context);
    }

    private void initSnackbarView(Context context) {
        View.inflate(context, R.layout.alarm_black_bg_layout, this);
        setClipToPadding(false);
    }

    @Override
    public void animateContentIn(int i, int i1) {
    }

    @Override
    public void animateContentOut(int i, int i1) {
    }
}