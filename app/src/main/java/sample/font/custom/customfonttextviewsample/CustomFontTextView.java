package sample.font.custom.customfonttextviewsample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Based on <a href="https://futurestud.io/tutorials/custom-fonts-on-android-dynamic-font-selection-via-xml">this tutorial</a> .
 */
public class CustomFontTextView extends TextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";
    private static final String[] typefaceFileTypes = {"",".ttf", ".otf",".ttc",".pfb", ".dfont", ".fnt"};
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

        boolean useStyle= attributeArray.getBoolean(R.styleable.CustomFontTextView_useStyle, false);

        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

        Typeface customFont = selectTypeface(context, fontName, useStyle,textStyle);
        setTypeface(customFont);

        attributeArray.recycle();
    }

    private Typeface selectTypeface(Context context, String fontName, boolean useStyle,int textStyle) {
        if(useStyle)
            fontName = fontName + getFontStyleSuffix(textStyle);

        //If font wasn't found - return null and Android will use the default Font (Roboto).
        return selectTypefaceWithFileType(context,fontName);
    }

    private String getFontStyleSuffix(int textStyle)
    {
        String fontStyleSuffix;
        switch (textStyle) {
            case Typeface.BOLD: // bold
                fontStyleSuffix =  "Bold";
                break;

            case Typeface.ITALIC: // italic
                fontStyleSuffix =  "Italic";
                break;
            case Typeface.BOLD_ITALIC: // bold italic
                fontStyleSuffix =  "BoldItalic";
                break;
            case Typeface.NORMAL: // regular
            default:
                fontStyleSuffix =  "Normal";
        }
        return "-"+fontStyleSuffix;
    }

    private Typeface selectTypefaceWithFileType(Context context, String fontName)
    {
        Typeface typeface;
        for(String suffix:typefaceFileTypes)
        {
            if( (typeface = FontCache.getTypeface(fontName+suffix, context)) != null)
                return typeface;
        }
        return null;
    }

}