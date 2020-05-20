package ir.brn.driver.util.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

import ir.brn.driver.R;
import ir.brn.driver.util.TypefaceHelper;

public class VerticalPersianTextView extends AppCompatTextView {

    final boolean topDown;


    public VerticalPersianTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        final int gravity = getGravity();
        if (Gravity.isVertical(gravity) && (gravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.BOTTOM) {

            setGravity((gravity & Gravity.HORIZONTAL_GRAVITY_MASK) | Gravity.TOP);
            topDown = false;
        } else
            topDown = true;
        setPersianTypeface(context, attrs, 0);

    }

    private void setPersianTypeface(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        TextPaint textPaint = getPaint();
        textPaint.setColor(getCurrentTextColor());
        textPaint.drawableState = getDrawableState();
        canvas.save();

        if (topDown) {
            canvas.translate(getWidth(), 0);
            canvas.rotate(90);
        } else {
            canvas.translate(0, getHeight());
            canvas.rotate(-90);
        }

        canvas.translate(getCompoundPaddingLeft(),getExtendedPaddingTop());

        getLayout().draw(canvas);
        canvas.restore();

    }
}
