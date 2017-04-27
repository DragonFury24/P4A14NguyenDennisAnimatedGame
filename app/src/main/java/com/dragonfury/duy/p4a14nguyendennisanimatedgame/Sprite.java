package com.dragonfury.duy.p4a14nguyendennisanimatedgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by 1383504 on 4/25/2017.
 */
public class Sprite extends RectF {

    public Sprite (float left, float top, float right, float bottom, Bitmap heroBMP) {
        super(left, top, right, bottom);
        this.heroBMP = heroBMP;
        iWidth = heroBMP.getWidth() / BMP_COLS; //Calculate width of 1 icon
        iHeight = heroBMP.getHeight() / BMP_ROWS; //Calculate height of 1 icon
    }
    public static final Creator <RectF> CREATOR = null;
    private int xSpeed = 5, ySpeed = 10; //Declare and instantiate
    Bitmap heroBMP; //Received bitmap stores instance heroBMP
    private static final int BMP_ROWS = 4; //Number of rows on sprite sheet
    private static int BMP_COLS = 4; //Number of columns on sprite sheet
    private int currentFrame = 0; //Frame to be drawn
    private int iWidth, iHeight; //Dimensions of 1 icon on sprite sheet

    public void update(Canvas c) {
        if (right + xSpeed >= c.getWidth()) {xSpeed *=-1;} //Invert xSpeed and ySpeed when boundaries reached (bounce off walls)
        else if (left + xSpeed <= 0) {xSpeed *= -1;}
        if (bottom + ySpeed >= c.getHeight()) {ySpeed *= -1;}
        else if (top + ySpeed <= 0) {ySpeed *= -1;}
        offset(xSpeed, ySpeed); //Increment x and y directions
        currentFrame = ++currentFrame % BMP_COLS; //Advanced to next frame, returns to 0 when past max
    }

    public void draw(Canvas c) {
        int srcX = currentFrame * iWidth; //Set x of current icon
        int srcY = 1 * iHeight; //Set y to 2 rows of icons
        Rect src = new Rect(srcX, srcY, srcX + iWidth, srcY + iHeight); //Define rectangle to be drawn (1 icon)

        update(c); //Modify Sprite
        c.drawBitmap(heroBMP, src, this, null);
    }

}
