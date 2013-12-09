package cn.com.ldci.plants;

public class WuDouSheShou extends Plants {

	public WuDouSheShou(int type, float x, float y, GameView gameView) {
		super(x, y, 8);
		
		this.life = 10;
		this.gameView = gameView;
		
		loadImage();
	}

	protected void loadImage(){
		frameImage = gameView.Plant1;
	}
	
	public void fire(){
		gameView.goodBollets1.add(new Bullets(x+15, y+5, 1, gameView));
	}
	
}
