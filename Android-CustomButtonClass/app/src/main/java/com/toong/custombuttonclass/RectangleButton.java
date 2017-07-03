package com.toong.custombuttonclass;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class RectangleButton extends AppCompatButton {
    private final static float DEFAULT_ALPHA = 1f;
    private final static float DEFAULT_ALPHA_WHEN_PRESS = 0.5f;
    private final static float DEFAULT_TEXT_SIZE = 14;
    private boolean mPressed;

    public RectangleButton(Context context) {
        this(context, null);
    }

    public RectangleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectangleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        configDefault();
    }

    private void configDefault() {
        setTextColor(Color.WHITE);
        setTextSize(DEFAULT_TEXT_SIZE);
        setGravity(Gravity.CENTER);
    }

    private void refresh(){
        if(mPressed){
            setAlpha(DEFAULT_ALPHA_WHEN_PRESS);
            invalidate();
            return;
        }
        setAlpha(DEFAULT_ALPHA);
        invalidate();
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        mPressed = pressed;
        refresh();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
            Log.d("TouchTest", "Touch down");
            setPressed(true);
        } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
            Log.d("TouchTest", "Touch up");
            setPressed(false);
            float x = event.getX();
            float y = event.getY();
            boolean isInside = (x > 0 && x < getWidth() && y > 0 && y < getHeight());
            if(isInside){
                performClick();
            }
        }
        return true;
    }
}