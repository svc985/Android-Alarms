package org.prikic.alarmmanagertest.snackbar;

import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import org.prikic.alarmmanagertest.R;

public class ChefSnackbar extends BaseTransientBottomBar<ChefSnackbar> {

    private ChefSnackbar(@NonNull ViewGroup parent,
                         @NonNull MySnackbarView content) {
        super(parent, content, content);

        getView().setBackgroundColor(ContextCompat.getColor(getView().getContext(), android.R.color.transparent));
        getView().setPadding(0, 0, 0, 0);
    }

    public static ChefSnackbar make(View view) {
        // First we find a suitable parent for our custom view
        ViewGroup parent = findSuitableParent(view);

        if (parent == null) {
            throw new IllegalArgumentException("No suitable parent");
        }

        // We inflate our custom view
        MySnackbarView customView = (MySnackbarView) LayoutInflater.from(
                view.getContext()).inflate(
                R.layout.layout_snackbar_chef,
                parent,
                false);

        // We create and return our Snackbar
        return new ChefSnackbar(parent, customView);

    }

    private static ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;
        do {
            if (view instanceof CoordinatorLayout) {
                // We've found a CoordinatorLayout, use it
                return (ViewGroup) view;
            } else if (view instanceof FrameLayout) {
                if (view.getId() == android.R.id.content) {
                    // If we've hit the decor content view, then we didn't find a CoL in the
                    // hierarchy, so use it.
                    return (ViewGroup) view;
                } else {
                    // It's not the content view but we'll use it as our fallback
                    fallback = (ViewGroup) view;
                }
            }

            if (view != null) {
                // Else, we will loop and crawl up the view hierarchy and try to find a parent
                final ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
        } while (view != null);

        // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
        return fallback;
    }
}
