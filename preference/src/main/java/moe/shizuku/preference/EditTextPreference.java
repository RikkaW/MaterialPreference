/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package moe.shizuku.preference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.content.res.TypedArrayUtils;
import androidx.fragment.app.DialogFragment;

/**
 * A {@link Preference} that allows for string
 * input.
 * <p>
 * It is a subclass of {@link DialogPreference} and shows the {@link EditText}
 * in a dialog.
 * <p>
 * This preference will store a string into the SharedPreferences.
 */
public class EditTextPreference extends DialogPreference {

    private String mText;
    private String mSummary;
    private String mHint;
    private int mInputType;
    private boolean mSingleLine;
    private boolean mSelectAllOnFocus;
    private boolean mCommitOnEnter;

    @SuppressLint("RestrictedApi")
    public EditTextPreference(Context context, AttributeSet attrs, int defStyleAttr,
                              int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray a;
        a = context.obtainStyledAttributes(attrs, R.styleable.EditTextPreference);

        mInputType = TypedArrayUtils.getInt(a, R.styleable.EditTextPreference_inputType,
                R.styleable.EditTextPreference_android_inputType, InputType.TYPE_CLASS_TEXT);

        mSingleLine = TypedArrayUtils.getBoolean(a, R.styleable.EditTextPreference_singleLine,
                R.styleable.EditTextPreference_android_singleLine, true);

        mSelectAllOnFocus = TypedArrayUtils.getBoolean(a, R.styleable.EditTextPreference_selectAllOnFocus,
                R.styleable.EditTextPreference_android_selectAllOnFocus, false);

        mHint = TypedArrayUtils.getString(a, R.styleable.EditTextPreference_hint,
                R.styleable.EditTextPreference_android_hint);

        mCommitOnEnter = a.getBoolean(R.styleable.EditTextPreference_commitOnEnter, false);
        a.recycle();

        /* Retrieve the Preference summary attribute since it's private
         * in the Preference class.
         */
        a = context.obtainStyledAttributes(attrs,
                R.styleable.Preference, defStyleAttr, defStyleRes);

        mSummary = TypedArrayUtils.getString(a, R.styleable.Preference_summary,
                R.styleable.Preference_android_summary);

        a.recycle();
    }

    public EditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.Preference_DialogPreference_EditTextPreference_Material);
    }

    public EditTextPreference(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextPreferenceStyle);
    }

    public EditTextPreference(Context context) {
        this(context, null);
    }

    @NonNull
    @Override
    protected DialogFragment onCreateDialogFragment(String key) {
        final EditTextPreferenceDialogFragment
                fragment = new EditTextPreferenceDialogFragment();
        final Bundle b = new Bundle(1);
        b.putString(PreferenceDialogFragment.ARG_KEY, key);
        fragment.setArguments(b);
        return fragment;
    }

    /**
     * Saves the text to the {@link android.content.SharedPreferences}.
     *
     * @param text The text to save
     */
    public void setText(String text) {
        final boolean wasBlocking = shouldDisableDependents();
        final boolean changed = !TextUtils.equals(mText, text);
        mText = text;

        if (changed) {
            persistString(text);
            notifyChanged();
        }

        final boolean isBlocking = shouldDisableDependents();
        if (isBlocking != wasBlocking) {
            notifyDependencyChange(isBlocking);
        }
    }

    /**
     * Returns the summary of this EditTextPreference. If the summary
     * has a {@linkplain java.lang.String#format String formatting}
     * marker in it (i.e. "%s" or "%1$s"), then the current entry
     * value will be substituted in its place.
     *
     * @return the summary with appropriate string substitution
     */
    @Override
    public CharSequence getSummary() {
        final CharSequence entry = getText();
        if (mSummary == null) {
            return super.getSummary();
        } else {
            return String.format(mSummary, entry == null ? "" : entry);
        }
    }

    /**
     * Sets the summary for this Preference with a CharSequence.
     * If the summary has a
     * {@linkplain java.lang.String#format String formatting}
     * marker in it (i.e. "%s" or "%1$s"), then the current entry
     * value will be substituted in its place when it's retrieved.
     *
     * @param summary The summary for the preference.
     */
    @Override
    public void setSummary(CharSequence summary) {
        super.setSummary(summary);
        if (summary == null && mSummary != null) {
            mSummary = null;
        } else if (summary != null && !summary.equals(mSummary)) {
            mSummary = summary.toString();
        }
    }

    /**
     * Gets the text from the {@link android.content.SharedPreferences}.
     *
     * @return The current preference value.
     */
    public String getText() {
        return mText;
    }

    /**
     * @return Input type value.
     */
    public int getInputType() {
        return mInputType;
    }

    /**
     *
     * @return Hint
     */
    public String getHint() {
        return mHint;
    }

    /**
     * @return is SingleLine
     */
    public boolean isSingleLine() {
        return mSingleLine;
    }

    public boolean isSelectAllOnFocus() {
        return mSelectAllOnFocus;
    }

    public boolean isCommitOnEnter() {
        return mCommitOnEnter;
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    @Override
    protected void onSetInitialValue(Object defaultValue) {
        setText(getPersistedString((String) defaultValue));
    }

    @Override
    public boolean shouldDisableDependents() {
        return TextUtils.isEmpty(mText) || super.shouldDisableDependents();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        if (isPersistent()) {
            // No need to save instance state since it's persistent
            return superState;
        }

        final SavedState myState = new SavedState(superState);
        myState.text = getText();
        return myState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state == null || !state.getClass().equals(SavedState.class)) {
            // Didn't save state for us in onSaveInstanceState
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState myState = (SavedState) state;
        super.onRestoreInstanceState(myState.getSuperState());
        setText(myState.text);
    }

    private static class SavedState extends BaseSavedState {
        String text;

        public SavedState(Parcel source) {
            super(source);
            text = source.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeString(text);
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    @Override
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    @Override
                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }
}
