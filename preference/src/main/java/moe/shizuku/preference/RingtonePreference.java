/*
 * Copyright (C) 2007 The Android Open Source Project
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
 * limitations under the License.
 */

package moe.shizuku.preference;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.core.content.res.TypedArrayUtils;

/**
 * A {@link Preference} that allows the user to choose a ringtone from those on the device.
 * The chosen ringtone's URI will be persisted as a string.
 * <p>
 * If the user chooses the "Default" item, the saved string will be one of
 * {@link System#DEFAULT_RINGTONE_URI},
 * {@link System#DEFAULT_NOTIFICATION_URI}, or
 * {@link System#DEFAULT_ALARM_ALERT_URI}. If the user chooses the "Silent"
 * item, the saved string will be an empty string.
 *
 * @attr ref android.R.styleable#RingtonePreference_ringtoneType
 * @attr ref android.R.styleable#RingtonePreference_showDefault
 * @attr ref android.R.styleable#RingtonePreference_showSilent
 */
public class RingtonePreference extends Preference implements
        PreferenceManager.OnActivityResultListener {

    private static final String TAG = "RingtonePreference";

    private String mSummary;
    private String mValue;
    private Uri mUri;
    private String mSummaryNone;

    private int mRingtoneType;
    private boolean mShowDefault;
    private boolean mShowSilent;

    private int mRequestCode;

    public RingtonePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.RingtonePreference, defStyleAttr, defStyleRes);

        mRingtoneType = TypedArrayUtils.getInt(a, R.styleable.RingtonePreference_ringtoneType,
                R.styleable.RingtonePreference_android_ringtoneType, RingtoneManager.TYPE_RINGTONE);
        mShowDefault = TypedArrayUtils.getBoolean(a, R.styleable.RingtonePreference_showDefault,
                R.styleable.RingtonePreference_android_showDefault, true);
        mShowSilent = TypedArrayUtils.getBoolean(a, R.styleable.RingtonePreference_showSilent,
                R.styleable.RingtonePreference_android_showSilent, true);
        mSummaryNone = a.getString(R.styleable.RingtonePreference_summaryNone);
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

    public RingtonePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.Preference_RingtonePreference);
    }

    public RingtonePreference(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.ringtonePreferenceStyle);
    }

    public RingtonePreference(Context context) {
        this(context, null);
    }

    /**
     * Returns the sound type(s) that are shown in the picker.
     *
     * @return The sound type(s) that are shown in the picker.
     * @see #setRingtoneType(int)
     */
    public int getRingtoneType() {
        return mRingtoneType;
    }

    /**
     * Sets the sound type(s) that are shown in the picker.
     *
     * @param type The sound type(s) that are shown in the picker.
     * @see RingtoneManager#EXTRA_RINGTONE_TYPE
     */
    public void setRingtoneType(int type) {
        mRingtoneType = type;
    }

    /**
     * Returns whether to a show an item for the default sound/ringtone.
     *
     * @return Whether to show an item for the default sound/ringtone.
     */
    public boolean getShowDefault() {
        return mShowDefault;
    }

    /**
     * Sets whether to show an item for the default sound/ringtone. The default
     * to use will be deduced from the sound type(s) being shown.
     *
     * @param showDefault Whether to show the default or not.
     * @see RingtoneManager#EXTRA_RINGTONE_SHOW_DEFAULT
     */
    public void setShowDefault(boolean showDefault) {
        mShowDefault = showDefault;
    }

    /**
     * Returns whether to a show an item for 'Silent'.
     *
     * @return Whether to show an item for 'Silent'.
     */
    public boolean getShowSilent() {
        return mShowSilent;
    }

    /**
     * Sets whether to show an item for 'Silent'.
     *
     * @param showSilent Whether to show 'Silent'.
     * @see RingtoneManager#EXTRA_RINGTONE_SHOW_SILENT
     */
    public void setShowSilent(boolean showSilent) {
        mShowSilent = showSilent;
    }

    @Override
    protected void onClick() {
        // Launch the ringtone picker
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        onPrepareRingtonePickerIntent(intent);
        PreferenceFragment owningFragment = getPreferenceManager().getFragment();
        if (owningFragment != null) {
            owningFragment.startActivityForResult(intent, mRequestCode);
        }/* else {
            getPreferenceManager().getActivity().startActivityForResult(intent, mRequestCode);
        }*/
    }

    /**
     * Prepares the intent to launch the ringtone picker. This can be modified
     * to adjust the parameters of the ringtone picker.
     *
     * @param ringtonePickerIntent The ringtone picker intent that can be
     *                             modified by putting extras.
     */
    protected void onPrepareRingtonePickerIntent(Intent ringtonePickerIntent) {

        ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI,
                onRestoreRingtone());

        ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, mShowDefault);
        if (mShowDefault) {
            ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI,
                    RingtoneManager.getDefaultUri(getRingtoneType()));
        }

        ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, mShowSilent);
        ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, mRingtoneType);
        ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, getTitle());
    }

    /**
     * Called when a ringtone is chosen.
     * <p>
     * By default, this saves the ringtone URI to the persistent storage as a
     * string.
     *
     * @param ringtoneUri The chosen ringtone's {@link Uri}. Can be null.
     */
    protected void onSaveRingtone(Uri ringtoneUri) {
        persistString(ringtoneUri != null ? ringtoneUri.toString() : "");
        setValue(ringtoneUri);
    }

    /**
     * Called when the chooser is about to be shown and the current ringtone
     * should be marked. Can return null to not mark any ringtone.
     * <p>
     * By default, this restores the previous ringtone URI from the persistent
     * storage.
     *
     * @return The ringtone to be marked as the current ringtone.
     */
    protected Uri onRestoreRingtone() {
        final String uriString = getPersistedString(null);
        return !TextUtils.isEmpty(uriString) ? Uri.parse(uriString) : null;
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    @Override
    protected void onSetInitialValue(Object defaultValue) {
        String uri = getPersistedString((String) defaultValue);
        if (!TextUtils.isEmpty(uri)) {
            setValue(Uri.parse(uri));
        } else {
            setValue(mSummaryNone == null ? "" : mSummaryNone);
        }
    }

    /**
     * Returns the summary of this RingtonePreference. If the summary
     * has a {@linkplain java.lang.String#format String formatting}
     * marker in it (i.e. "%s" or "%1$s"), then the current entry
     * value will be substituted in its place.
     *
     * @return the summary with appropriate string substitution
     */
    @Override
    public CharSequence getSummary() {
        final CharSequence value = mValue;
        if (mSummary == null) {
            return super.getSummary();
        } else {
            return String.format(mSummary, value == null ? "" : value);
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

    public void setValue(Uri uri) {
        if (uri != null) {
            Ringtone rng = RingtoneManager.getRingtone(getContext(), uri);
            if (rng != null) {
                setValue(rng.getTitle(getContext()));
            }
        } else {
            setValue(mSummaryNone == null ? ""/*getContext().getString(android.R.string.)*/ : mSummaryNone);
        }
        notifyChanged();
    }

    public void setValue(String value) {
        mValue = value;
    }

    @Override
    protected void onAttachedToHierarchy(PreferenceManager preferenceManager) {
        super.onAttachedToHierarchy(preferenceManager);

        preferenceManager.registerOnActivityResultListener(this);
        mRequestCode = preferenceManager.getNextRequestCode();
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == mRequestCode) {
            if (data != null) {
                Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

                if ((uri == null || !uri.equals(mUri)) && callChangeListener(mUri != null ? mUri.toString() : "")) {
                    //Log.d(TAG, uri == null ? "null" : uri.toString());

                    mUri = uri;
                    onSaveRingtone(mUri);
                }
            }
            return true;
        }
        return false;
    }
}