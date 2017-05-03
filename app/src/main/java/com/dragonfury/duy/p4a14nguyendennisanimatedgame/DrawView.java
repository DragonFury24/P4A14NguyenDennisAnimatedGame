package com.dragonfury.duy.p4a14nguyendennisanimatedgame;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 1383504 on 4/19/2017.
 */
public class DrawView extends SurfaceView {

    private Bitmap heroBmp; // Declare space for Bitmap called heroBmp, global scope
    private SurfaceHolder holder; //Declares space for a SurfaceHolder called holder
    private LoopThread loopThread; //Declares space for a LoopThread called loopThread
    private List<Sprite> sprites = new ArrayList<>(); //Creates a flexible data structure
    private List<TempSprite> temps = new ArrayList<>();
    private long lastClick;

    public DrawView(Context context) { // Constructor because it has the same name as the class
        super(context); //Calls View(context), Parent's constructor

        heroBmp = BitmapFactory.decodeResource(getResources(), R.drawable.bluejeans); //Instantiate heroBmp - assign to heroBmp for the first time
        holder = getHolder();
        loopThread = new LoopThread(this); //Instantiate LoopThread with the current instance of DrawView

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                createSprites();
                loopThread.setRunning(true); //Sets the thread's running variable to true
                loopThread.start(); //Starts the thread
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                boolean retry = true; //Declares boolean called retry and instantiates to true, local variable
                loopThread.setRunning(false); //Sets the thread's running variable to false
                while (retry) {
                    try {
                        loopThread.join(); //Blocks the current thread until this instance's thread terminates
                        retry = false;
                    } catch (InterruptedException e) {

                    }
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK); //Draws black over the canvas
        for (int i = temps.size() - 1; i >= 0; i--) {
            temps.get(i).draw(canvas);
        }
        for (Sprite sprite : sprites) {
            sprite.draw(canvas);
        }

    }

    /**
     * Create an individual Sprite
     * @param image name if bitmap
     * @return send Sprite back
     */
    private Sprite createSprite(int image) { //Create an individual Sprite and sends Sprite back
        Bitmap heroBMP = BitmapFactory.decodeResource(getResources(), image);
        return new Sprite(getWidth(), getHeight(), heroBMP);
    }

    /**
     *Adds new Sprite to ArrayList of sprites
     */
    private void createSprites() { //Adds a new sprite to ArrayList of Sprites
            for (int i = 0; i < 100; i++) {
                sprites.add(createSprite(R.drawable.bluejeans));
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (System.currentTimeMillis() - lastClick > 500) { //Half second between valid clicks
            lastClick = System.currentTimeMillis(); //Records time of effective click
            synchronized (getHolder()) {
                for (int i = sprites.size()-1; i >= 0; i--) {
                    Sprite sprite = sprites.get(i);
                    if (sprite.contains(event.getX(), event.getY())) {
                        temps.add(new TempSprite(sprite, temps, getResources()));
                        sprites.remove(sprite);
                        break;
                    }
                }
            }
        }

        return super.onTouchEvent(event);
    }
}

