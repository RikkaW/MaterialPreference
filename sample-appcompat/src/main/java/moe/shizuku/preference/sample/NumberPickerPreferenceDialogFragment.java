package moe.shizuku.preference.sample;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.NumberPicker;

import moe.shizuku.preference.PreferenceDialogFragment;

public class NumberPickerPreferenceDialogFragment extends PreferenceDialogFragment {

    private NumberPickerPreference mPreference;
    private NumberPicker mNumberPicker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPreference = (NumberPickerPreference) getPreference();
        mNumberPicker = mPreference.getNumberPicker();
    }

    @Override
    protected View onCreateDialogView(Context context) {
        ViewParent parent = mNumberPicker.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(mNumberPicker);
        }
        return mNumberPicker;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        mNumberPicker.setValue(mPreference.getValue());
        mNumberPicker.requestFocus();

        mNumberPicker.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, 0);
            }
        });
    }

    @Override
    public boolean needInputMethod() {
        return true;
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mNumberPicker.getWindowToken(), 0);
        }

        mNumberPicker.clearFocus();
        if (positiveResult) {
            int value = mNumberPicker.getValue();
            mPreference.setValue(value);
        }
    }
}
