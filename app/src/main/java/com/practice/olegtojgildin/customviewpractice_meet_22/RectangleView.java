package com.practice.olegtojgildin.customviewpractice_meet_22;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by olegtojgildin on 25/02/2019.
 */

public class RectangleView extends View {
    public static float widthRectangle;
    public static float higthRectangle;

    private Paint mPaint;
    private RectF mRect;
    private int mColorFigure;
    private TextView mXrectangle;
    private TextView mYrectangle;

    public RectangleView(Context context) {
        super(context);
        initViews(null);
    }

    public RectangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    public RectangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(attrs);
    }

    public RectangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(attrs);
    }


    public void initViews(@Nullable AttributeSet attrs) {
        mRect = new RectF();
        mPaint = new Paint();
        mPaint.setTextSize(60);
        mXrectangle = new TextView(getContext());
        mYrectangle = new TextView(getContext());
        initAttrs(attrs);

        mRect.left = 300;
        mRect.right = mRect.left + higthRectangle;
        mRect.top = 300;
        mRect.bottom = mRect.top + widthRectangle;
        mPaint.setColor(mColorFigure);
    }

    private void initAttrs(@Nullable AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RectangleView);
            mColorFigure = typedArray.getColor(R.styleable.RectangleView_color_figure, Color.RED);
            higthRectangle = typedArray.getDimension(R.styleable.RectangleView_higth_figure, 300);
            widthRectangle = typedArray.getDimension(R.styleable.RectangleView_width_figure, 200);
            typedArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(mRect, mPaint);
        mXrectangle.draw(canvas);
        mYrectangle.draw(canvas);
        canvas.drawText("X: " + Float.toString(mRect.centerX()), 50, 100, mPaint);
        canvas.drawText("Y: " + Float.toString(mRect.centerY()), 50, 200, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = event.getX();
            float y = event.getY();
            if (x > mRect.left && x < mRect.left + higthRectangle && y > mRect.top && y < mRect.top + widthRectangle) {
                double dx = Math.pow(x - mRect.centerX(), 2);
                double dy = Math.pow(y - mRect.centerY(), 2);
                if (dx + dy < Math.pow((widthRectangle+higthRectangle)/ 2, 2)) ;
                {
                    mRect.set(x - higthRectangle / 2, y - widthRectangle / 2, x + higthRectangle / 2, y + widthRectangle / 2);
                    changeColor();
                    invalidate();
                    return true;
                }
            }
        }
        return true;
    }

    private void changeColor() {
        Random rnd = new Random();
        mPaint.setColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
    }
}
