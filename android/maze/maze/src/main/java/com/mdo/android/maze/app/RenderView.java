package com.mdo.android.maze.app;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * The RenderView object.
 * User: mdo
 * Date: 4/14/14
 * Time: 3:28 PM
 */
public class RenderView extends View {
	static final String TAG = RenderView.class.getCanonicalName();
	Bitmap bob565;
	Bitmap bob4444;
	Rect dst = new Rect();

	public RenderView(Context context) {
		super(context);

		try {
			AssetManager assetManager = context.getAssets();
			InputStream inputStream = assetManager.open("bobrgb888.png");
			bob565 = BitmapFactory.decodeStream(inputStream);
			inputStream.close();
			Log.d("BitmapText",
					"bobrgb888.png format: " + bob565.getConfig());

			inputStream = assetManager.open("bobargb8888.png");
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inPreferredConfig = Bitmap.Config.ARGB_4444;
			bob4444 = BitmapFactory
					.decodeStream(inputStream, null, options);
			inputStream.close();
			Log.d("BitmapText",
					"bobargb8888.png format: " + bob4444.getConfig());

		} catch (IOException e) {
			// silently ignored, bad coder monkey, baaad!
			Log.e(TAG,e.getMessage(),e);
		} finally {
			// we should really close our input streams here.
		}
	}

	protected void onDraw(Canvas canvas) {
		dst.set(50, 50, 350, 350);
		//canvas.drawBitmap(bob565, null, dst, null);
		canvas.drawBitmap(bob4444, 100, 100, null);
		invalidate();
	}
}