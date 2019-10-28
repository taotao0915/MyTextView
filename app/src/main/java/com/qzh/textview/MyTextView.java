package com.qzh.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class MyTextView extends View {
    private String mText;
    private int mTextColor;
    private int mTextSize;

    private Paint mPaint;

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);

        mText = typedArray.getString(R.styleable.MyTextView_mText);
        mTextColor = typedArray.getInt(R.styleable.MyTextView_mTextColor,mTextColor);
        mTextSize = typedArray.getInt(R.styleable.MyTextView_mTextSize,mTextSize);

        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST){
            //计算宽度，与字体的长度、大小有关，用画笔来测量
            Rect bounds = new Rect();
            mPaint.getTextBounds(mText,0,mText.length(),bounds);
            width = bounds.width();
        }


        int height = MeasureSpec.getSize(heightMeasureSpec);
        if(heightMode == MeasureSpec.AT_MOST){
            //计算宽度，与字体的长度、大小有关，用画笔来测量
            Rect bounds = new Rect();
            mPaint.getTextBounds(mText,0,mText.length(),bounds);
            height = bounds.height();
        }

        //设置控件的宽高
        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
