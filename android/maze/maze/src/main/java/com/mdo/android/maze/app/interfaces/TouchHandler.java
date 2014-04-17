package com.mdo.android.maze.app.interfaces;

import android.view.View;

import java.util.List;

/**
 * The TouchHandler object.
 * User: mdo
 * Date: 4/14/14
 * Time: 3:02 PM
 */
public interface TouchHandler extends View.OnTouchListener {
	public boolean isTouchDown(int pointer);

	public int getTouchX(int pointer);

	public int getTouchY(int pointer);

	public List<TouchEvent> getTouchEvents();

	public static class TouchEvent {
		public static final int TOUCH_DOWN = 0;
		public static final int TOUCH_UP = 1;
		public static final int TOUCH_DRAGGED = 2;

		public int type;
		public int x, y;
		public int pointer;
	}
}
