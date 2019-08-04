package org.prikic.alarmmanagertest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyDialogFragment extends DialogFragment {

    static MyDialogFragment newInstance() {
        MyDialogFragment f = new MyDialogFragment();

        Bundle args = new Bundle();
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.loading_dialog_fragment, container, false);
        final CustomTextView tv = v.findViewById(R.id.txt);

        setCancelable(true);

        //final TextView newTv = v.findViewById(R.id.new_tv);
        //final EditText editText = v.findViewById(R.id.username);

//        // Watch for button clicks.
//        final Button button = v.findViewById(R.id.btn_apply);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                newTv.setText("New title");
//                newTv.setVisibility(View.VISIBLE);
//                button.setText("Test");
//                newTv.sendAccessibilityEvent(TYPE_WINDOW_STATE_CHANGED);
//                button.sendAccessibilityEvent(TYPE_WINDOW_STATE_CHANGED);
//            }
//        });

        return v;
    }
}
