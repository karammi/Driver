package ir.brn.driver.util.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatCheckBox;
import ir.brn.driver.R;
import ir.brn.driver.util.TypefaceHelper;


public class PersianCheckBox extends AppCompatCheckBox {
    public PersianCheckBox(Context context) {
        super(context);
        setPersianTypeface(context,null,0);
    }

    public PersianCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPersianTypeface(context,attrs,0);
    }

    public PersianCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setPersianTypeface(context,attrs,defStyleAttr);
    }

    private void setPersianTypeface(Context context, AttributeSet attrs, int defStyleAttr) {

    }
}
