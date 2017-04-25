package com.dragonfury.duy.p4a14nguyendennisanimatedgame;
import android.graphics.Canvas;

/**
 * Created by 1383504 on 4/21/2017.
 */
public class LoopThread extends Thread {

    private DrawView view; //Declares space for DrawView obj called view
    private boolean running = false; //Declare and instantiate a boolean called running to false
    public static int FPS = 10; //Declares int and instantiate to 10

    public LoopThread(DrawView view){ //Constructor, receives DrawView
        this.view = view; //Assigns and receives DrawView into the global view
    }

    public void setRunning(boolean running) { //Setter to allow public access to boolean running
        this.running = running;
    }

    public void run() { //Every thread must have a run method

        long tickPS = 1000/FPS; //Number of seconds to complete each loop
        long startTime = System.currentTimeMillis(); //Current system time in milliseconds
        long sleepTime; 

        while(running){ //Loop as long as running is true
            Canvas c = null; //Declares space for Canvas called c, local variable
            try {
                c = view.getHolder().lockCanvas(); //Locks canvas
                synchronized (view.getHolder()) {
                    view.onDraw(c); //Calls drawView's onDraw()
                }
            }finally {
                if(c != null) { //Unlocks the canvas
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
        }

    }
}
