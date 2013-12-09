package cn.com.ldci.plants;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public class XiangRiKui extends Plants{

	public Context context;
	
	public XiangRiKui(int type, float x, float y) {
		super(type, x, y);
		this.x = x;
		this.y = y;
		new XiangRiKui_sun_Thread(x, y, context).start();
		
	}

	public void draw(Canvas canvas){
		Paint paint = new Paint();
		canvas.drawBitmap(plantsBitmap[i], x, y, paint);
		i = (i+1)%8;
	}
	
	
	
}
