package com.mdo.android.maze.app;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * The RenderSurfaceView object.
 * User: mdo
 * Date: 4/16/14
 * Time: 11:37 PM
 */
public class RenderSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
	private SurfaceHolder holder;     // This is no instantiation. Just saying that the holder
	// will be of a class implementing SurfaceHolder
	private DotsThread dotsThread;// The thread that displays the dots

	public RenderSurfaceView(Context context) {
		super(context);
		holder = getHolder();          // Holder is now the internal/private mSurfaceHolder inherit
		// from the SurfaceView class, which is from an anonymous
		// class implementing SurfaceHolder interface.
		holder.addCallback(this);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	}

	// This is always called at least once, after surfaceCreated
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		if (dotsThread ==null){
			dotsThread = new DotsThread(holder);
			dotsThread.setRunning(true);
			dotsThread.setSurfaceSize(width, height);
			dotsThread.start();
		} else {
			resume();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		pause();
	}

	public Thread getThread() {
		return dotsThread;
	}

	public void resume() {
		if (dotsThread != null){
			dotsThread.setRunning(true);
			dotsThread.start();
		}
	}

	public void pause() {
		if (dotsThread != null) {
			dotsThread.setRunning(false);
			boolean retry = true;
			while (retry) {
				try {
					dotsThread.join();
					retry = false;
				} catch (InterruptedException e) {}
			}
		}
	}
}
