package cn.com.ldci.plants;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Plants {
	float type;
	float x,y;
	// 植物的生命
	protected int	life = 10;
	// 播放植物动画的帧序列
	protected int frameIndex = 0;
	// 植物动画的帧数
	protected Bitmap[] frameImage = null;
	// 植物动画的帧总数
	protected final int	frameAmount;
	
	GameView gameView;
	
//	int i = 0;
//	int count=0;
	public Plants(float x, float y, int frameAmount){
		this.x = x;
		this.y = y;
		this.frameAmount = frameAmount;
	}
	
	protected void loadImage(){
		//TODO 子类必须重写此函数，用于加载植物类及动画图片
	}
	
	public void draw(Canvas canvas){
		Paint paint = new Paint();
		canvas.drawBitmap(frameImage[frameIndex], x, y, paint);
		frameIndex = (frameIndex + 1) % frameAmount;
	}
	
	public void fire(){
		
	}
	
	public boolean contain(Zomebie zb){
		if(isContain(zb.currentX, zb.currentY, zb.nomalBitmap[0].getWidth(), zb.nomalBitmap[0].getHeight())){//检测成功
			this.life--;//自己的生命减1
			return true;
		}
		return false;
	}
	
	public boolean isContain(float otherX, float otherY, int otherWidth, int otherHeight){//判断两个矩形是否碰撞
		float xd = 0;//大的x
		float yd = 0;//大大y
		float xx = 0;//小的x
		float yx = 0;//小的y
		int width = 0;
		int height = 0;
		boolean xFlag = true;//玩家飞机x是否在前
		boolean yFlag = true;//玩家飞机y是否在前
		if(this.x >= otherX){
			xd = this.x;
			xx = otherX;
			xFlag = false;
		}else{
			xd = otherX;
			xx = this.x;
			xFlag = true;
		}
		if(this.y >= otherY){
			yd = this.y;
			yx = otherY;
			yFlag = false;
		}else{
			yd = otherY;
			yx = this.y;
			yFlag = true;
		}
		if(xFlag == true){
			width = this.frameImage[0].getWidth();
		}else {
			width = otherWidth;
		}
		if(yFlag == true){
			height = this.frameImage[0].getHeight();
		}else{
			height = otherHeight;
		}
		if(xd>=xx&&xd<=xx+width-1&&
				yd>=yx&&yd<=yx+height-1){//首先判断两个矩形有否重叠
		    double Dwidth=width-xd+xx;   //重叠区域宽度		
			double Dheight=height-yd+yx; //重叠区域高度
			if(Dwidth*Dheight/(otherWidth*otherHeight)>=0.20){//重叠面积超20%则判定为碰撞
				return true;
			}
		}
		return false;
	}	
	public float getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

}
