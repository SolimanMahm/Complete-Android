package com.example.chatapp;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class Utils {

    public static boolean isNotEmpty(@NonNull EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError("Cannot be empty!");
            editText.requestFocus();
            return true;
        }
        return false;
    }

    public static void setLoading(boolean isLoading, List<View> inputViews, View progressBar) {
        // Enable/Disable the input views
        for (View view : inputViews) {
            view.setEnabled(!isLoading);
        }

        // Show or hide the progress bar
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public static void showToast(Context context, int text) {
        Toast.makeText(context, context.getText(text), Toast.LENGTH_LONG).show();
    }

    public static void getErrorMessage(Context context, String authException) {
        switch (authException) {
            case "ERROR_EMAIL_ALREADY_IN_USE":
                Utils.showToast(context, R.string.ERROR_EMAIL_ALREADY_IN_USE);
                break;
            case "ERROR_WEAK_PASSWORD":
                Utils.showToast(context, R.string.ERROR_WEAK_PASSWORD);
                break;
            case "ERROR_INVALID_EMAIL":
                Utils.showToast(context, R.string.ERROR_INVALID_EMAIL);
                break;
            case "ERROR_INVALID_CREDENTIAL":
                Utils.showToast(context, R.string.ERROR_INVALID_CREDENTIAL);
                break;
        }
    }
}