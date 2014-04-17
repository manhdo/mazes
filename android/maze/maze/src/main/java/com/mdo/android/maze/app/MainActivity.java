package com.mdo.android.maze.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;


public class MainActivity extends Activity {
	static final String TAG = MainActivity.class.getCanonicalName();
	GLRenderSurfaceView glRenderView;
	RenderSurfaceView renderView;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		startRenderView();
		//startGLRenderView();
    }

	private void startGLRenderView() {
		glRenderView = new GLRenderSurfaceView(this);
		setContentView(glRenderView);
	}

	private void startRenderView() {
		renderView = new RenderSurfaceView(this);
		setContentView(renderView);
	}


	@Override
	protected void onResume() {
		super.onResume();
		//glRenderView.resume();
		if (renderView != null) {
			//renderView.resume();
		}
		if (glRenderView != null) {
			glRenderView.onResume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (renderView != null) {
			//renderView.pause();
		}
		if (glRenderView != null) {
			glRenderView.onPause();
		}
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
