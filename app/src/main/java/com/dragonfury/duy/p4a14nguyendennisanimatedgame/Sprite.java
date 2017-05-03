package com.dragonfury.duy.p4a14nguyendennisanimatedgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by 1383504 on 4/25/2017.
 */
public class Sprite extends RectF {

    /**
     *
     * @param canvasWidth
     * @param canvasHeight
     * @param heroBMP
     */
    public Sprite (float canvasWidth, float canvasHeight, Bitmap heroBMP) {
        super(0, 0, .1f * canvasWidth, .1f * canvasHeight);
        this.heroBMP = heroBMP;
        iWidth = heroBMP.getWidth() / BMP_COLS; //Calculate width of 1 icon
        iHeight = heroBMP.getHeight() / BMP_ROWS; //Calculate height of 1 icon
        offsetTo((int) (Math.random() * (canvasWidth - width())), (int) (Math.random() * (canvasHeight - height())));
    }
    public static final Creator <RectF> CREATOR = null;
    Bitmap heroBMP; //Received bitmap stores instance heroBMP
    private static final int BMP_ROWS = 4; //Number of rows on sprite sheet
    private static final int BMP_COLS = 4; //Number of columns on sprite sheet
    private static final int MAX_SPEED = 20;
    private int currentFrame = 0; //Frame to be drawn
    private int iWidth, iHeight; //Dimensions of 1 icon on sprite sheet
    private int xSpeed = (int)(Math.random() * (MAX_SPEED * 2 + 1) - MAX_SPEED), ySpeed = (int)(Math.random() * (MAX_SPEED * 2 - 1) - MAX_SPEED); //Random intSpeed from -25 to 25


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
        int srcY = getAnimationRow() * iHeight; //Set y to row based on direction
        Rect src = new Rect(srcX, srcY, srcX + iWidth, srcY + iHeight); //Define rectangle to be drawn (1 icon)

        update(c); //Modify Sprite
        c.drawBitmap(heroBMP, src, this, null);
    }

    public int getAnimationRow() {
        if (Math.abs(xSpeed) > Math.abs(ySpeed)) { //If magnitude x is greater than magnitude y
            if (Math.abs(xSpeed) == xSpeed) { //xSpeed is positive
                return 2; //Return 2 - right
            } else return 1; //Return 1 - left
        } else if (Math.abs(ySpeed) == ySpeed) { //ySpeed is positive
            return 0; //Return 0 - down
        } else return 3; //Return 3 - up
    }
}

