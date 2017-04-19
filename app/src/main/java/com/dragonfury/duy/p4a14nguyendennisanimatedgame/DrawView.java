package com.dragonfury.duy.p4a14nguyendennisanimatedgame;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by 1383504 on 4/19/2017.
 */
public class DrawView extends View {

    private Bitmap heroBmp; // Declare space fir Bitmap called heroBmp, global scope
    public DrawView(Context context) { // Constructor because it has the same name as the class
        super(context); //Calls View(context), Parent's constructor
        //Instantiate heroBmp - assign to heroBmp for the first time
        heroBmp = BitmapFactory.decodeResource(getResources(), R.drawable.bluejeans);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(heroBmp, 10 * 1440 / getWidth(), 10 * 2560 / getHeight(), null); // Draw heroBmp at (10, 10)
    }
}
