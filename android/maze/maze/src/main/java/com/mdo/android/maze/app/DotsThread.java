package com.mdo.android.maze.app;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Random;

/**
 * The DotsThread object.
 * User: mdo
 * Date: 4/14/14
 * Time: 3:39 PM
 */
public class DotsThread extends Thread {
	private int mCanvasWidth;
	private int mCanvasHeight;
	private ArrayList<Dot> dots = new ArrayList<Dot>(); // Dynamic array with dots
	private SurfaceHolder holder;
	private boolean running = false;
	private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private final int refresh_rate=50;      // How often we update the screen, in ms

	public DotsThread(SurfaceHolder holder) {
		this.holder = holder;
	}

	@Override
	public void run() {
		int x,y,radius;
		float[] color=new float[3]; // HSV (0..360,0..1,0..1)
		long previousTime, currentTime;
		previousTime = System.currentTimeMillis();
		Canvas canvas = null;
		int upperBound = 100;
		while(running && dots.size() < upperBound) {
			// Look if time has past
			currentTime=System.currentTimeMillis();
			while ((currentTime-previousTime)<refresh_rate){
				currentTime=System.currentTimeMillis();
			}
			previousTime=currentTime;
			// ADD ONE MORE DOT TO THE SCREEN
			x=100 + (int)(Math.random() * (mCanvasWidth-200));
			y=100 + (int)(Math.random() * (mCanvasHeight-200));
			radius=1 + (int)(Math.random() * 99);
			color[0]=(float)(Math.random()*360);
			color[1]=1;
			color[2]=1;
			Dot mdot=new Dot(x,y,radius,Color.HSVToColor(128,color));
			dots.add(mdot);
			// PAINT
			paint(canvas);
		}

		// continue animating
		while (running) {
			paint(canvas);
		}
	}

	private void paint(Canvas canvas) {
		try {
			canvas = holder.lockCanvas();
			synchronized (holder) {
				draw(canvas);
			}
		}
		finally {
			if (canvas != null) {
				holder.unlockCanvasAndPost(canvas);
			}
		}
		// WAIT
		try {
			Thread.sleep(refresh_rate-5); // Wait some time till I need to display again
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// The actual drawing in the Canvas (not the update to the screen).
	private void draw(Canvas canvas)
	{
		Dot temp_dot;
		canvas.drawColor(Color.BLACK);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		for (int i=0;i< dots.size();i++){
			temp_dot= dots.get(i);
			paint.setColor(temp_dot.get_color());
			canvas.drawCircle((float)temp_dot.get_x(),
					(float)temp_dot.get_y(),
					(float)temp_dot.get_radius(),
					paint);
			temp_dot.move();

		}
	}

	public void setRunning(boolean b) {
		running = b;
	}

	public void setSurfaceSize(int width, int height) {
		synchronized (holder){
			mCanvasWidth = width;
			mCanvasHeight = height;
		}
	}

	private class Dot{
		private int x,y,radius,color;
		private int xdirection;
		private int ydirection;
		private int speed;


		Dot(int x, int y, int radius, int color){
			this.x=x;
			this.y=y;
			this.radius=radius;
			this.color=color;

			Random rand = new Random();
			xdirection = rand.nextBoolean()? -1 : 1;
			ydirection = rand.nextBoolean() ? -1 : 1;
			speed = rand.nextInt(5)+5;
		}

		public int get_x(){
			return this.x;
		}

		public int get_y(){
			return this.y;
		}

		public int get_radius(){
			return this.radius;
		}

		public int get_color(){
			return this.color;
		}

		public void move() {
			x+= xdirection*speed;
			y+= ydirection*speed;

			if (x < 0 || x > mCanvasWidth) {
				xdirection*=-1;
			}


			if (y < 0 || y > mCanvasHeight) {
				ydirection*=-1;
			}
		}
	}
}