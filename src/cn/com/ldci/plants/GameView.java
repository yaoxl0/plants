package cn.com.ldci.plants;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	MainActivity activity;
	ApahaThread ap ;
	static int suncount = 500;
	private TutorialThread thread;// ˢ֡���߳�
	MoveThread moveThread;// �ƶ�������߳�
	BulletMoveThread bulletThread;
	float currentx;
	float currenty;
	boolean isdown;
	boolean plantmoveFlag = false;

	int status = 1;// ��Ϸ��״̬1��ʾ��Ϸ��,2��ʾ��Ϸʧ��
	Bitmap[] BestZomebieBitmap;// ��ʬ1
	Bitmap[] MidlerZomebieBitmap;;// ��ʬ2
	Bitmap[] LessMidZomebieBitmap;// ��ʬ3
	Bitmap[] BadestZomebieBitmap;// ��ʬ4
	static Bitmap[][] P = new Bitmap[2][8];
	
	static Bitmap[] xiangRiKui;
	static Bitmap[] Plant1;// ֲ��1����
	static Bitmap[] Plant2;//ֲ��2˫��
	Bitmap background1, background;// ����ͼƬ
	Bitmap seedbank;
	Bitmap[] seedImage = new Bitmap[6];
	Bitmap[] seedPlants = new Bitmap[4];
	int[] image = { R.drawable.seed_00, R.drawable.seed_01, R.drawable.seed_02,
			R.drawable.seed_03, R.drawable.seed_04, R.drawable.seed_05 };
	int[] seedPs ={R.drawable.plant1, R.drawable.plant2, R.drawable.plant3, R.drawable.plant4};
	ArrayList<Bullets> goodBollets1 = new ArrayList<Bullets>();// ֲ�﷢�����ӵ�
	ArrayList<Bullets> goodBollets2 = new ArrayList<Bullets>();
	ArrayList<Bullets> goodBollets3 = new ArrayList<Bullets>();
	ArrayList<BestZomebie> zombies;// ��ʬȺ
	static ArrayList<Plants> plants;// ֲ��Ⱥ
	List<XiangRiKui_sun> suns;
	
	sun sun;
	SeedBank sb;
	Paint paint;// ����
	private Matrix martic;

	public GameView(MainActivity context) {
		super(context);
		this.activity = context;
		martic = new Matrix();
		martic.setScale(0.55f, 0.55f);
		getHolder().addCallback(this);// ע��ӿ�
		this.thread = new TutorialThread(getHolder(), this);// ��ʼ��ˢ֡�߳�
		this.moveThread = new MoveThread(this);
		this.bulletThread = new BulletMoveThread(this);
		plants = new ArrayList<Plants>();
		suns = new ArrayList<XiangRiKui_sun>();
		if (activity.processView != null) {
			activity.processView.process += 20;
		}
		zombies = Maps.getFirst();// ȡ��һ�صĽ�ʬ
		// plants = Maps.getPlants();
		sb = new SeedBank(this.activity);
		initBitmap();
		ap = new ApahaThread(sb);
		sun = new sun(this.activity);
	}

	public static Bitmap zoomImage(Bitmap bgimage, int newWidth, int newHeight) {
		// ��ȡ���ͼƬ�Ŀ��͸�
		int width = bgimage.getWidth();
		int height = bgimage.getHeight();
		// ��������ͼƬ�õ�matrix����
		Matrix matrix = new Matrix();
		// ���������ʣ��³ߴ��ԭʼ�ߴ�
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// ����ͼƬ����
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, width, height,
				matrix, true);
		return bitmap;

	}

	/**
	 * ��ʼ������ͼƬ
	 */
	public void initBitmap() {

		/**
		 * ��ʼ������ͼ
		 */
		background1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.bg);
		background = zoomImage(background1, 480, 320);
		if (activity.processView != null) {
			activity.processView.process += 20;
		}

		/**
		 * ��ʼ����ʬ1��
		 */
		BestZomebieBitmap = new Bitmap[] {
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_00_01),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_00_02),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_00_03),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_00_04),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_00_05),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_00_06),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_00_07) };
		
		for (int i = 0; i < BestZomebieBitmap.length; i++) {
			BestZomebieBitmap[i] = zoomImage(BestZomebieBitmap[i], 40, 58);
			// BestZomebieBitmap[i] = Bitmap.createBitmap(BestZomebieBitmap[i],
			// 0, 0, BestZomebieBitmap[i].getWidth(),
			// BestZomebieBitmap[i].getHeight(), martic, true);
		}
		if (activity.processView != null) {
			activity.processView.process += 20;
		}

		/**
		 * ��ʼ����ʬ3��
		 */
		MidlerZomebieBitmap = new Bitmap[] {
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_02_01),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_02_02),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_02_03),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_02_04),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_02_05),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_02_06),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_02_07) };
		for (int i = 0; i < MidlerZomebieBitmap.length; i++) {
			MidlerZomebieBitmap[i] = zoomImage(MidlerZomebieBitmap[i], 40, 58);
		}
		if (activity.processView != null) {
			activity.processView.process += 20;
		}

		/**
		 * ��ʼ����ʬ2��
		 */
		LessMidZomebieBitmap = new Bitmap[] {
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_01_01),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_01_01),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_01_01),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_01_01),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_01_01),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_01_01),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.z_01_01) };

		/**
		 * ʵ�����е�ͼƬ�����ʼ��
		 */
		for (BestZomebie eep : zombies) {
			eep.nomalBitmap = BestZomebieBitmap;

		}

		/**
		 * ��ʼ��ֲ��
		 */
		xiangRiKui = new Bitmap[]{    //���տ�
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.plant_xiangrikui_01),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.plant_xiangrikui_02),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.plant_xiangrikui_03),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.plant_xiangrikui_04),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.plant_xiangrikui_05),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.plant_xiangrikui_06),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.plant_xiangrikui_07),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.plant_xiangrikui_08) 
		};
		
		Plant1 = new Bitmap[] {   //����
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_01_01),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_01_02),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_01_03),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_01_04),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_01_05),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_01_06),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_01_07),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_01_08) };
		for (int i = 0; i < Plant1.length; i++) {
			Plant1[i] = Bitmap.createBitmap(Plant1[i], 0, 0, Plant1[i]
					.getWidth(), Plant1[i].getHeight(), martic, true);
			// Plant1[i] = zoomImage(Plant1[i],40,40);
		}
		P[0] = Plant1;
		
		//˫��
		Plant2 = new Bitmap[] {
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_02_01),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_02_02),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_02_03),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_02_04),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_02_05),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_02_06),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_02_07),
				BitmapFactory.decodeResource(this.activity.getResources(),
						R.drawable.p_02_08) };
		for (int i = 0; i < Plant2.length; i++) {
			Plant2[i] = Bitmap.createBitmap(Plant2[i], 0, 0, Plant2[i]
					.getWidth(), Plant2[i].getHeight(), martic, true);
		}
		P[1] = Plant2;

		/**
		 * ��ʼ��������
		 */
		seedbank = BitmapFactory.decodeResource(getResources(),
				R.drawable.seedbank);
		martic.setScale(0.78f, 0.66f);
		seedbank = Bitmap.createBitmap(seedbank, 0, 0, seedbank.getWidth(),
				seedbank.getHeight(), martic, true);
		martic.setScale(0.55f, 0.55f);
		for(int i=0;i<seedPlants.length;i++){
			seedPlants[i] =BitmapFactory.decodeResource(getResources(), seedPs[i]);
		}
		for (int i = 0; i < seedImage.length; i++) {
			seedImage[i] = BitmapFactory.decodeResource(getResources(),
					image[i]);
			seedImage[i] = zoomImage(seedImage[i],40,50 );//27, 38
//			 seedImage[i] = Bitmap.createBitmap(seedImage[i], 0, 0,
//			 seedImage[i].getWidth(), seedImage[i].getHeight(), martic, true);
		}
		sb.seedImage = seedImage;
		sb.seedbank = seedbank;
		//sb.seedPlants = seedPlants;
		if (activity.processView != null) {
			activity.processView.process += 20;
		}

	}
	
	public void onDraw(Canvas canvas) {// �Լ�д�Ļ��Ʒ���,������д��
		canvas.drawBitmap(background, 0, 0, paint);// ���Ʊ���
		sb.draw(canvas);// ���ƹ�����
		if(status == 1 || status == 3){//��Ϸ��ʱ,�ؿ���ʱ
//		if (plantmoveFlag) {
//			sb.drawMoveIma(canvas, currentx, currenty, isdown);// ��ֲ�������궯
//		}
			try {// �����ҷ��ӵ�
				for (Bullets b : goodBollets1) {
					b.draw(canvas);
				}
			} catch (Exception e) {
			}
			try {// �����ҷ��ӵ�
				for (Bullets b : goodBollets2) {
					b.draw(canvas);
				}
			} catch (Exception e) {
			}
			try {// �����ҷ��ӵ�
				for (Bullets b : goodBollets3) {
					b.draw(canvas);
				}
			} catch (Exception e) {
			}
			try {// ���ƽ�ʬ
				for (BestZomebie ep : zombies) {
					ep.draw(canvas);
				}
			} catch (Exception e) {
	
			}
			try {// ����ֲ��
				for (Plants ps : plants) {
					ps.draw(canvas);
				}
			} catch (Exception e) {
				
			}
			try {
				for(XiangRiKui_sun XRK_sun : suns){
					XRK_sun.move();
				}
			} catch (Exception e) {
				
			}
			try{
				sun.Draw(canvas);    //�����ڰ����Զ�������sun
			}catch(Exception e){
				
			}
		
		 }
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		sb.touchEvent(event);
		sun.onTouchEvent(event);
		
		return true;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	public void surfaceCreated(SurfaceHolder holder) {
		this.thread.setFlag(true);// ����ˢ֡�߳�
		this.thread.start();
		this.moveThread.setFlag(true);
		this.moveThread.start();// ���������ƶ�����ƶ��߳�
		this.bulletThread.setFlag(true);
		this.bulletThread.start();
		sun.start();           //����ÿ30�����һ��sun���߳�
		ap.start();

	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;// ѭ�����
		thread.setFlag(false);
		while (retry) {
			try {
				thread.join();// �ȴ��̵߳Ľ���
				retry = false;// ����ѭ�����ֹͣѭ��
			} catch (InterruptedException e) {
			}// ���ϵ�ѭ����ֱ��ˢ֡�߳̽���
		}
		ap.flag = false ;
	}

	class TutorialThread extends Thread {// ˢ֡�߳�
		private int sleepSpan = 70;// ˯�ߵĺ�����
		private SurfaceHolder surfaceHolder;
		private GameView gameView;
		private boolean flag = false;

		public TutorialThread(SurfaceHolder surfaceHolder, GameView gameView) {// ������
			this.surfaceHolder = surfaceHolder;
			this.gameView = gameView;
		}

		public void setFlag(boolean flag) {// ����ѭ�����λ
			this.flag = flag;
		}

		@Override
		public void run() {
			if(zombies.size()<=0){
				activity.myHandler.sendEmptyMessage(5);
				Log.v("Message", "55555555555555");
			}
			Canvas c;
			while (this.flag) {
				c = null;
				try {
					// �����������������ڴ�Ҫ��Ƚϸߵ�����£����������ҪΪnull
					c = this.surfaceHolder.lockCanvas(null);
					synchronized (this.surfaceHolder) {
						gameView.onDraw(c);// ����
					}
				} finally {
					if (c != null) {
						// ������Ļ��ʾ����
						this.surfaceHolder.unlockCanvasAndPost(c);
					}
					if(zombies.size()<=0){
						activity.myHandler.sendEmptyMessage(5);
						Log.v("Message", "55555555555555");
					}
				}
				try {
					Thread.sleep(sleepSpan);// ˯��ָ��������
				} catch (Exception e) {
					e.printStackTrace();// ��ӡ��ջ��Ϣ
				}
			}
		}
	}

}