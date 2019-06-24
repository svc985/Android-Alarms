package org.prikic.alarmmanagertest.snackbar;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.snackbar.ContentViewCallback;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import org.prikic.alarmmanagertest.R;

public class MySnackbarView extends ConstraintLayout implements ContentViewCallback {

    private ImageView chefImage;

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
        View.inflate(context, R.layout.custom_snackbar_layout, this);
        this.chefImage = findViewById(R.id.chef_image);
        setClipToPadding(false);
    }

    @Override
    public void animateContentIn(int i, int i1) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(chefImage, View.SCALE_X, 0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(chefImage, View.SCALE_Y, 0f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new OvershootInterpolator());
        animatorSet.setDuration(500);
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.start();
    }

    @Override
    public void animateContentOut(int i, int i1) {
    }
}
