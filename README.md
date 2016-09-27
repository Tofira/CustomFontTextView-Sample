# CustomFontTextView-Sample
Sample project for supporting custom fonts in Android applications.

It uses font cache and allows for setting fonts directly from the XML.

Based on the following great Tutorial - 

https://futurestud.io/tutorials/custom-fonts-on-android-extending-textview


How to use - 
Copy the files CustomFontTextView, FontCache and the contents of attrs.xml to your project.

Add your fonts to the folder /assets/fonts.

Simple sample usage - 

```xml<sample.font.custom.customfonttextviewsample.CustomFontTextView
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:layout_margin="12dp"
	android:text="Maven Pro Light"
	android:textSize="18sp"
	app:font="@string/font_name_maven_pro_light"/>
```

use `font` attribute to specify the required font. You can write it with its suffix (e.g MavenPro.ttf), or without it, and the library will determine it for you (supported file types - ttf, otf, ttc, pfb, dfont, fnt). If an error occurs, the default system font will be used.

<b>Font styles - </b>

Cosinder the following example - 

```xml<sample.font.custom.customfonttextviewsample.CustomFontTextView
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:layout_margin="12dp"
	android:text="Maven Pro Bold"
	android:textSize="18sp"
	app:font="MavenPro"
	app:useStyle="true"
	android:textStyle="bold"/>
```
        
We used the `useStyle` (default value - False) to specify that we'd like the library to use styles. Note, that each font should be named with regards to its style, in the following notation - [FontName]-[FontStyle].[FileType].
For example - MavenPro-Bold.ttf.

