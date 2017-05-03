package com.dragonfury.duy.p4a14nguyendennisanimatedgame;

import android.content.res.Resources;
import android.graphics.*;

import java.util.List;

/**
 * Created by 1383504 on 5/3/2017.
 */
public class TempSprite extends RectF{
    public static final Creator<Rect> CREATOR = null;
    public static Bitmap bmp;
    private List<TempSprite> temps;
    private int lifeSpan = 15;

    public TempSprite(Sprite sprite, List<TempSprite> temps, Resources resources) {
        super(sprite);
        bmp = BitmapFactory.decodeResource(resources, R.drawable.blood1);
        this.temps = temps;
    }

    private void update() {
        if (--lifeSpan < 1) {
            temps.remove(this); //Remove self if lifeSpan is less than 1
        }
    }

    public void draw(Canvas canvas) {
        update();
        canvas.drawBitmap(bmp, null, this,  null);
    }
}
