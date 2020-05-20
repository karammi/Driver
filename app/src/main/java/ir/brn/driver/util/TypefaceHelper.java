package ir.brn.driver.util;

import android.content.Context;
import android.graphics.Typeface;

import androidx.collection.SimpleArrayMap;

public class TypefaceHelper {

    private static final SimpleArrayMap<String, Typeface> cache = new SimpleArrayMap<>();

    public static Typeface get(Context context, String name) {
        synchronized (cache) {
            if (!cache.containsKey(name)) {
                try {
                    Typeface typeface = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s", name));
                    cache.put(name, typeface);
                    return typeface;
                } catch (RuntimeException ex) {
                    return null;
                }
            }
            return cache.get(name);
        }
    }
}
