package com.android.androidlocalizing;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    public CustomTextView(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        // Load attributes
        final TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                attrs, R.styleable.LocalizationAttributes, defStyle, 0);

        // get the locText value that might be set in the xml
        String localizedString = typedArray.getString(
                R.styleable.LocalizationAttributes_locText);

        typedArray.recycle();

        if (localizedString != null) {

            setLocalizedText(localizedString);

        }

    }

    /**
     * Sets the text to the text being saved into SharedPreferences with the given key
     */
    public void setLocalizedText(String key) {

        SharedPreferences settings = getContext().getSharedPreferences("localization_strings_file", 0);

        // fetch the localized string with the given key (that is set in XML)
        // and set it to the text view, or set an empty String
        setText(settings.getString(key, ""));

    }

}
