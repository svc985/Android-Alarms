package org.prikic.alarmmanagertest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class CustomTextView extends AppCompatTextView {

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onPopulateAccessibilityEvent(AccessibilityEvent event) {
        Log.d("test", "onPopulateAccessibilityEvent");
        event.getText().add("");
        super.onPopulateAccessibilityEvent(event);
    }
}
