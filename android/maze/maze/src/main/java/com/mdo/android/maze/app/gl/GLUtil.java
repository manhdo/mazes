package com.mdo.android.maze.app.gl;

/**
 * The GLUtil object.
 * User: mdo
 * Date: 4/17/14
 * Time: 1:02 AM
 */
public class GLUtil {
	float[] verts=MakeCircle2d(1,100,0,0);

	public static float[] MakeCircle2d(float rad,int points,float x,float y)//x,y  ofsets
	{
		float[] verts=new float[points*2+2];
		boolean first=true;
		float fx=0;
		float fy=0;
		int c=0;
		for (int i = 0; i < points; i++)
		{
			float fi = (float)(2*Math.PI*i/points);
			float xa = (float)(rad*Math.sin(fi + Math.PI)+x);
			float ya = (float)(rad*Math.cos(fi + Math.PI)+y);
			if(first)
			{
				first=false;
				fx=xa;
				fy=ya;
			}
			verts[c]=xa;
			verts[c+1]=ya;
			c+=2;
		}
		verts[c]=fx;
		verts[c+1]=fy;
		return verts;
	}
}
