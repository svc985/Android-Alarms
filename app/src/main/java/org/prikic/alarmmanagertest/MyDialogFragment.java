package org.prikic.alarmmanagertest;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.view.accessibility.AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;

public class MyDialogFragment extends DialogFragment {

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    static MyDialogFragment newInstance() {
        MyDialogFragment f = new MyDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment, container, false);
        final TextView tv = v.findViewById(R.id.txt);
        final TextView newTv = v.findViewById(R.id.new_tv);
        final EditText editText = v.findViewById(R.id.username);

        // Watch for button clicks.
        final Button button = v.findViewById(R.id.btn_apply);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newTv.setText("New title");
                newTv.setVisibility(View.VISIBLE);
                button.setText("Test");
                newTv.sendAccessibilityEvent(TYPE_WINDOW_STATE_CHANGED);
                button.sendAccessibilityEvent(TYPE_WINDOW_STATE_CHANGED);
            }
        });

        return v;
    }
}
