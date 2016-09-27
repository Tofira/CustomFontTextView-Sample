package sample.font.custom.customfonttextviewsample;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

public class FontCache {

    private static HashMap<String, Typeface> fontCache = new HashMap<>();
    private static String fontPrefix = "fonts/";
    public static Typeface getTypeface(String fontName, Context context) {
        Typeface typeface = fontCache.get(fontName);

        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontPrefix + fontName);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(fontName, typeface);
        }

        return typeface;
    }
}