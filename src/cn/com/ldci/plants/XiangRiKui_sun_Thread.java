package cn.com.ldci.plants;

import android.content.Context;

public class XiangRiKui_sun_Thread extends Thread {

	private boolean flag = true;
	private int sleepSpan = 10000;//˯�ߵĺ�����
	GameView gameView;
	XiangRiKui_sun xiangRiKuiSun;
	float orientX;
	float orientY;
	Context context;
	
	public XiangRiKui_sun_Thread(float x, float y, Context context){
		this.orientX = x;
		this.orientY = y;
		this.context = context;
	}
	
	public void run(){
		while(flag){
			try {
				Thread.sleep(sleepSpan);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			xiangRiKuiSun = new XiangRiKui_sun(orientX, orientY, context);
			gameView.suns.add(xiangRiKuiSun);                    //ÿ10s��XiangRiKui����һ��sun
			
		}
	}
}
