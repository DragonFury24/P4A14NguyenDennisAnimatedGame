package com.dragonfury.duy.p4a14nguyendennisanimatedgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by 1383504 on 4/25/2017.
 */
public class Sprite extends RectF {

    public Sprite (float left, float top, float right, float bottom, Bitmap heroBMP) {
        super(left, top, right, bottom);
        this.heroBMP = heroBMP;
    }
    public static final Creator <RectF> CREATOR = null;
    private int xSpeed = 5, ySpeed = 10; //Declare and instantiate
    Bitmap heroBMP; //Received bitmap stores instance heroBMP

    public void update(Canvas c) {
        if (right + xSpeed >= c.getWidth()) {xSpeed *=-1;} //Invert xSpeed and ySpeed when boundaries reached (bounce off walls)
        else if (left + xSpeed <= 0) {xSpeed *= -1;}
        if (bottom + ySpeed >= c.getHeight()) {ySpeed *= -1;}
        else if (top + ySpeed <= 0) {ySpeed *= -1;}
        offset(xSpeed, ySpeed); //Increment x and y directions
    }

    public void draw(Canvas c) {
        update(c); //Modify Sprite
        c.drawBitmap(heroBMP, null, this, null);
    }

}
