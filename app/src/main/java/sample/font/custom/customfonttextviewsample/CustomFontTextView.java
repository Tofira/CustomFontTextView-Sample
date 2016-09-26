package sample.font.custom.customfonttextviewsample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * See <a href="https://futurestud.io/tutorials/custom-fonts-on-android-dynamic-font-selection-via-xml">this tutorial</a>  for custom styles.
 */
public class CustomFontTextView extends TextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.CustomFontTextView);

        String fontName = attributeArray.getString(R.styleable.CustomFontTextView_font);
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

        Typeface customFont = selectTypeface(context, fontName, textStyle);
        setTypeface(customFont);

        attributeArray.recycle();
    }

    private Typeface selectTypeface(Context context, String fontName, int textStyle) {
        if (fontName.contentEquals(context.getString(R.string.font_name_source_sans_pro))) {
              /*
              information about the TextView textStyle:
              http://developer.android.com/reference/android/R.styleable.html#TextView_textStyle
              */
            switch (textStyle) {
                case Typeface.BOLD: // bold
                    return FontCache.getTypeface("SourceSansPro-Bold.ttf", context);

                case Typeface.ITALIC: // italic
                    return FontCache.getTypeface("SourceSansPro-Italic.ttf", context);

                case Typeface.BOLD_ITALIC: // bold italic
                    return FontCache.getTypeface("SourceSansPro-BoldItalic.ttf", context);

                case Typeface.NORMAL: // regular
                default:
                    return FontCache.getTypeface("SourceSansPro-Regular.ttf", context);
            }
        }

        //If font wasn't found - return null and Android will use the default Font (Roboto).
        return FontCache.getTypeface(fontName, context);

    }
}