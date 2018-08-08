package com.example.czydevp.forensics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.util.Log;
import android.util.TypedValue;

public class Corner {
    public static Bitmap getRoundedCornerBitmap12(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
       // paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


    //try with magics

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int color,Context context) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        int borderDips=5;
        int cornerDips=5;
        final int borderSizePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) borderDips,
                context.getResources().getDisplayMetrics());
        final int cornerSizePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) cornerDips,
                context.getResources().getDisplayMetrics());
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        // prepare canvas for transfer
        paint.setAntiAlias(true);
        paint.setColor(0xFFFFFFFF);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawARGB(0, 0, 0, 0);
        // canvas.drawRoundRect(rectF, cornerSizePx, cornerSizePx, paint);
        canvas.drawRoundRect(rectF,20,20, paint);
        // draw bitmap
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        // draw border
        int  color1=0Xff3fb8e5;
        paint.setColor(color1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawRoundRect(rectF, cornerSizePx, cornerSizePx, paint);
        return output;
    }



//for delete user
    public static Bitmap getRoundedCornerBitmapdel(Bitmap bitmap,Context context) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        int borderDips=5;
        int cornerDips=5;
        final int borderSizePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) borderDips,
                context.getResources().getDisplayMetrics());
        final int cornerSizePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) cornerDips,
                context.getResources().getDisplayMetrics());
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        // prepare canvas for transfer
        paint.setAntiAlias(true);
        paint.setColor(0xFFFFFFFF);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawARGB(0, 0, 0, 0);
        // canvas.drawRoundRect(rectF, cornerSizePx, cornerSizePx, paint);
        canvas.drawRoundRect(rectF,20,20, paint);
        // draw bitmap
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        // draw border
        int  color1=0Xff3fb8e5;
        paint.setColor(color1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawRoundRect(rectF, cornerSizePx, cornerSizePx, paint);
        return output;
    }

//for search
         public static Bitmap getRoundedCornerBitmap1(Bitmap bitmap) {
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            int radius = Math.min(h / 2, w / 2);
            Bitmap output = Bitmap.createBitmap(w + 8, h + 8, Config.ARGB_8888);
            Paint p = new Paint();
            p.setAntiAlias(true);
            Canvas c = new Canvas(output);
            c.drawARGB(0, 0, 0, 0);
            p.setStyle(Paint.Style.FILL);
            c.drawCircle((w / 2) + 4, (h / 2) + 4, radius, p);
            p.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            c.drawBitmap(bitmap, 4, 4, p);
            p.setXfermode(null);
            p.setStyle(Paint.Style.STROKE);
           // p.setColor(Color.argb(100,0,210,255));
             int  color1=0Xff3fb8e5;
             p.setColor(color1);
            p.setStrokeWidth(3);
            c.drawCircle((w / 2) + 4, (h / 2) + 4, radius, p);
             Log.d("width",""+output.getWidth());
             Log.d("width",""+output.getHeight());
            return output;

        }
}