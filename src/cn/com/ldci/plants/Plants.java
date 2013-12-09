package cn.com.ldci.plants;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Plants {
	float type;
	float x,y;
	// ֲ�������
	protected int	life = 10;
	// ����ֲ�ﶯ����֡����
	protected int frameIndex = 0;
	// ֲ�ﶯ����֡��
	protected Bitmap[] frameImage = null;
	// ֲ�ﶯ����֡����
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
		//TODO ���������д�˺��������ڼ���ֲ���༰����ͼƬ
	}
	
	public void draw(Canvas canvas){
		Paint paint = new Paint();
		canvas.drawBitmap(frameImage[frameIndex], x, y, paint);
		frameIndex = (frameIndex + 1) % frameAmount;
	}
	
	public void fire(){
		
	}
	
	public boolean contain(Zomebie zb){
		if(isContain(zb.currentX, zb.currentY, zb.nomalBitmap[0].getWidth(), zb.nomalBitmap[0].getHeight())){//���ɹ�
			this.life--;//�Լ���������1
			return true;
		}
		return false;
	}
	
	public boolean isContain(float otherX, float otherY, int otherWidth, int otherHeight){//�ж����������Ƿ���ײ
		float xd = 0;//���x
		float yd = 0;//���y
		float xx = 0;//С��x
		float yx = 0;//С��y
		int width = 0;
		int height = 0;
		boolean xFlag = true;//��ҷɻ�x�Ƿ���ǰ
		boolean yFlag = true;//��ҷɻ�y�Ƿ���ǰ
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
				yd>=yx&&yd<=yx+height-1){//�����ж����������з��ص�
		    double Dwidth=width-xd+xx;   //�ص�������		
			double Dheight=height-yd+yx; //�ص�����߶�
			if(Dwidth*Dheight/(otherWidth*otherHeight)>=0.20){//�ص������20%���ж�Ϊ��ײ
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
