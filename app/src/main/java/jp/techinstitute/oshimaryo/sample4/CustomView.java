package jp.techinstitute.oshimaryo.sample4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by oshimaryo on 2018/07/05.
 */

public class CustomView extends View {



    public CustomView(Context context) {
        super(context);
    }


    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);

        initPaint();
    }

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
   public  Paint mPaint;

    // 描画用Paintの初期化
    private void initPaint() {


        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
// 画面サイズ変更時の通知
        Log.v("View", "onSizeChanged Width:" + w + ",Height:" + h);
// Canvasを作成
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
// Viewの描画関数で、パスを描画する


        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:


                touch_start(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                break;
        }
        invalidate();//Viewの再描画
        return true;
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {
//タッチ開始時
        Log.v("View", "touch_start");
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;


    }

     void setcolorred() {
       mPaint.setColor(Color.RED);


    }
    void setcolorblue() {
        mPaint.setColor(Color.BLUE);



    }

    private void touch_move(float x, float y) {
//指が移動している間の処理
        Log.d("View", "touch_move");
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
//閾値より移動量が多ければ線をつなぐ
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_up() {
//タッチ終了時
        Log.v("View", "touch_up");
//線を描く
        mPath.lineTo(mX, mY);
        mCanvas.drawPath(mPath, mPaint);
        mPath.reset();
    }


}
