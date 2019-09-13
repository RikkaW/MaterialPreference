package moe.shizuku.preference;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

public class PreferenceDialogBuilder {

    private AlertDialog.Builder mBuilder;

    public PreferenceDialogBuilder(Context context) {
        mBuilder = new AlertDialog.Builder(context);
    }

    public PreferenceDialogBuilder(Context context, int themeResId) {
        mBuilder = new AlertDialog.Builder(context, themeResId);
    }

    public PreferenceDialogBuilder setTitle(int titleId) {
        mBuilder.setTitle(titleId);
        return this;
    }

    public PreferenceDialogBuilder setTitle(CharSequence title) {
        mBuilder.setTitle(title);
        return this;
    }

    public PreferenceDialogBuilder setCustomTitle(View customTitleView) {
        mBuilder.setCustomTitle(customTitleView);
        return this;
    }

    public PreferenceDialogBuilder setMessage(int messageId) {
        mBuilder.setMessage(messageId);
        return this;
    }

    public PreferenceDialogBuilder setMessage(CharSequence message) {
        mBuilder.setMessage(message);
        return this;
    }

    public PreferenceDialogBuilder setIcon(int iconId) {
        mBuilder.setIcon(iconId);
        return this;
    }

    public PreferenceDialogBuilder setIcon(Drawable icon) {
        mBuilder.setIcon(icon);
        return this;
    }

    public PreferenceDialogBuilder setIconAttribute(int attrId) {
        mBuilder.setIconAttribute(attrId);
        return this;
    }

    public PreferenceDialogBuilder setPositiveButton(int textId, OnClickListener listener) {
        mBuilder.setPositiveButton(textId, listener);
        return this;
    }

    public PreferenceDialogBuilder setPositiveButton(CharSequence text, OnClickListener listener) {
        mBuilder.setPositiveButton(text, listener);
        return this;
    }

    public PreferenceDialogBuilder setNegativeButton(int textId, OnClickListener listener) {
        mBuilder.setNegativeButton(textId, listener);
        return this;
    }

    public PreferenceDialogBuilder setNegativeButton(CharSequence text, OnClickListener listener) {
        mBuilder.setNegativeButton(text, listener);
        return this;
    }

    public PreferenceDialogBuilder setNeutralButton(int textId, OnClickListener listener) {
        mBuilder.setNeutralButton(textId, listener);
        return this;
    }

    public PreferenceDialogBuilder setNeutralButton(CharSequence text, OnClickListener listener) {
        mBuilder.setNeutralButton(text, listener);
        return this;
    }

    public PreferenceDialogBuilder setCancelable(boolean cancelable) {
        mBuilder.setCancelable(cancelable);
        return this;
    }

    public PreferenceDialogBuilder setOnCancelListener(OnCancelListener onCancelListener) {
        mBuilder.setOnCancelListener(onCancelListener);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public PreferenceDialogBuilder setOnDismissListener(OnDismissListener onDismissListener) {
        mBuilder.setOnDismissListener(onDismissListener);
        return this;
    }

    public PreferenceDialogBuilder setOnKeyListener(OnKeyListener onKeyListener) {
        mBuilder.setOnKeyListener(onKeyListener);
        return this;
    }

    public PreferenceDialogBuilder setItems(int itemsId, OnClickListener listener) {
        mBuilder.setItems(itemsId, listener);
        return this;
    }

    public PreferenceDialogBuilder setItems(CharSequence[] items, OnClickListener listener) {
        mBuilder.setItems(items, listener);
        return this;
    }

    public PreferenceDialogBuilder setAdapter(ListAdapter adapter, OnClickListener listener) {
        mBuilder.setAdapter(adapter, listener);
        return this;
    }

    public PreferenceDialogBuilder setCursor(Cursor cursor, OnClickListener listener, String labelColumn) {
        mBuilder.setCursor(cursor, listener, labelColumn);
        return this;
    }

    public PreferenceDialogBuilder setMultiChoiceItems(int itemsId, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
        mBuilder.setMultiChoiceItems(itemsId, checkedItems, listener);
        return this;
    }

    public PreferenceDialogBuilder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
        mBuilder.setMultiChoiceItems(items, checkedItems, listener);
        return this;
    }

    public PreferenceDialogBuilder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, OnMultiChoiceClickListener listener) {
        mBuilder.setMultiChoiceItems(cursor, isCheckedColumn, labelColumn, listener);
        return this;
    }

    public PreferenceDialogBuilder setSingleChoiceItems(int itemsId, int checkedItem, OnClickListener listener) {
        mBuilder.setSingleChoiceItems(itemsId, checkedItem, listener);
        return this;
    }

    public PreferenceDialogBuilder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, OnClickListener listener) {
        mBuilder.setSingleChoiceItems(cursor, checkedItem, labelColumn, listener);
        return this;
    }

    public PreferenceDialogBuilder setSingleChoiceItems(CharSequence[] items, int checkedItem, OnClickListener listener) {
        mBuilder.setSingleChoiceItems(items, checkedItem, listener);
        return this;
    }

    public PreferenceDialogBuilder setSingleChoiceItems(ListAdapter adapter, int checkedItem, OnClickListener listener) {
        mBuilder.setSingleChoiceItems(adapter, checkedItem, listener);
        return this;
    }

    public PreferenceDialogBuilder setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        mBuilder.setOnItemSelectedListener(listener);
        return this;
    }

    public PreferenceDialogBuilder setView(int layoutResId) {
        mBuilder.setView(layoutResId);
        return this;
    }

    public PreferenceDialogBuilder setView(View view) {
        mBuilder.setView(view);
        return this;
    }

    public Dialog create() {
        return mBuilder.create();
    }

    public Dialog show() {
        return mBuilder.show();
    }
}
