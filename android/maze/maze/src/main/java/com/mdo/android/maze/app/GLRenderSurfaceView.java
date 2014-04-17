package com.mdo.android.maze.app;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * The GLRenderSurfaceView object.
 * User: mdo
 * Date: 4/14/14
 * Time: 3:36 PM
 */
public class GLRenderSurfaceView extends GLSurfaceView {
	static final String TAG = GLRenderSurfaceView.class.getCanonicalName();
	SurfaceHolder surfaceHolder;
	volatile boolean running = false;
	private DotsThread dotsThread;// The thread that displays the dots
	private int touchY;
	private int touchX;
	private boolean isTouched;

	ClearRenderer mRenderer;
	public GLRenderSurfaceView(Context context) {
		super(context);
		mRenderer = new ClearRenderer();
		setRenderer(mRenderer);
	}



	@Override
	public boolean onTouchEvent(final MotionEvent event) {
		isTouched = super.onTouchEvent(event);
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				isTouched = true;
				break;
			case MotionEvent.ACTION_MOVE:
				//isTouched = true;
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				isTouched = false;
				break;
		}
		queueEvent(new Runnable(){
			public void run() {
				mRenderer.setColor(event.getX() / getWidth(),
						event.getY() / getHeight(), 1.0f);
			}});

		touchX = (int) (event.getX());
		touchY = (int) (event.getY());
		if (isTouched) {
			Log.i(TAG, "TouchX: " + touchX + "    TouchY: " + touchY);
		}
		return true;
	}

	class ClearRenderer implements GLSurfaceView.Renderer {
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			// Do nothing special.
		}

		public void onSurfaceChanged(GL10 gl, int w, int h) {
			gl.glViewport(0, 0, w, h);
		}

		public void onDrawFrame(GL10 gl) {
			gl.glClearColor(mRed, mGreen, mBlue, 1.0f);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		}

		public void setColor(float r, float g, float b) {
			mRed = r;
			mGreen = g;
			mBlue = b;
		}

		private float mRed;
		private float mGreen;
		private float mBlue;
	}
}
