package com.example.duxinwu.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class CustomTextView extends View {

    private String mTitleText;

    private int mTitleTextColor;

    private int mTitleTextSize;
    /**
     * 绘制时控制文本大小
     */
    private Rect mBound;
    private Paint mPaint;


    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获取我们所定义的自定义属性
         */
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomTitleView, defStyleAttr, 0);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView_titleText:
                    mTitleText = array.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextColor:
                    mTitleTextColor=array.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_titleTextSize:
                    mTitleTextSize=array.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,
                                    getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
        }
        array.recycle();
        initView();
    }

    private void initView() {
        /**
         * 获取绘制文本的宽和高
         */
        mPaint=new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound=new Rect();
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitleText=randomText();
                postInvalidate();
            }
        });
    }

    private String randomText(){
        Random random=new Random();
        Set<Integer> set=new HashSet<>();
        while (set.size()<4){
            int randomInt=random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer stringBuffer=new StringBuffer();
        for (Integer i :
                set) {
            stringBuffer.append(""+i);
        }
        return stringBuffer.toString();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText,getWidth()/2-mBound.width()/2,getHeight()/2+mBound.height()/2,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        if (widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }else {
            float textWidth=mBound.width();
            int desired= (int) (getPaddingLeft()+mBound.width()+getPaddingRight());
            width=desired;

        }
        if (heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else{
            float textHeight=mBound.height();
            int desired= (int) (getPaddingTop()+mBound.height()+getPaddingBottom());
            height=desired;
        }
        setMeasuredDimension(width,height);
    }
}
