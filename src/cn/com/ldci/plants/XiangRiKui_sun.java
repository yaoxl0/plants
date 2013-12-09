package cn.com.ldci.plants;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class XiangRiKui_sun {

	private boolean flag = true;
	public float centx;
	public float centy;
	public static final int ONE = 0;// Í¼Æ¬ÏÂÂä×´Ì¬
	public static final int TWO = 1;//Í¼Æ¬µã»÷·µ»Ø×´Ì¬
	public static int current = 1;//Í¼Æ¬µ±Ç°×´Ì¬
	Bitmap sun;// Ì«Ñô
	Paint paint;// »­±Ê
	Context context;
	private  int luodijuli_ = 0;
	private  int[][] direct_ = {{0,0},{2,-2},{4,-4},{6,-6},{8,-8},{10,-6},{12,-4},{14,-2},{16,0},{16,5},{16,10},{16,15},{16,20},{16,25}};
	
	public XiangRiKui_sun(float x, float y, Context context) {// ¹¹ÔìÆ÷
		this.centx = x;
		this.centy = y;
		this.context=context;
		initBitmap();
	}
/**
 * Í¼Æ¬µã»÷ÊÂ¼þ
 * @param event
 * @return
 */
	protected boolean onTouchEvent(MotionEvent event) {
		boolean flag = true;
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			float x = event.getX();
			float y = event.getY();

			if ( (x - centx) < 55 && (x - centx) > 0
					&& (y - centy) < 55 && (y - centy) > 0) {
				flag = true;
				current = 2;
				GameView.suncount+=25;
			} 
		}
		return true;
	}
/**
 * »­Í¼
 * @param canvas
 */
	protected void Draw(Canvas canvas) {
		paint = new Paint();
		canvas.drawBitmap(sun, centx, centy, paint);

	}
/**
 * ³õÊ¼»¯Í¼Æ¬×ÊÔ´
 */
	protected void initBitmap() {
		sun = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.sun);
		sun = GameView.zoomImage(sun, 40, 40);

	}
	
	protected void move(){
		if(current == 1){
			if(luodijuli_<13) 
			{
				centx = centx + direct_[luodijuli_][0];
				centy = centy + direct_[luodijuli_][1];
				luodijuli_++;
			}
		}else if(current == 2){
			if(centy>-50){
				float yleft = centx;
				float ytop = 200;
				centy -= 6;
				centx -= yleft / ytop * 5;
			}
		}
		
	}
	
}
