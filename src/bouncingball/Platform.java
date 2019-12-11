package bouncingball;

import java.awt.Graphics;

public class Platform extends Object{
	private static double dropSpeed;
	private static boolean firsttime = true;
	private boolean monster;
	private boolean coin;
	
	public Platform(int score) {
		super();
		double r = Math.random();
		
		if(r < 0.2 && score%5==0 && score > 10) {
			this.monster = true;
			this.coin = false;
		}
		else if(r < 0.3 && score > 10) {
			this.monster = false;
			this.coin = true;
		}
		else {
			this.monster = false;
			this.coin = false;
		}
		
		if(firsttime) {
			dropSpeed = 2;
			firsttime = false;
		}
		
		x = (int)(Math.random()*(TheGame.WIDTH-WIDTH));
		y = HEIGHT;
	
		initPlatform();
	}
	
	public void initPlatform() {
		if(this.monster) {
			if(x < TheGame.WIDTH/2) 
				loadImage("src/resources/leftmonster.png");
			else 
				loadImage("src/resources/rightmonster.png");
		} else if(this.coin){
			loadImage("src/resources/coin.png");
		} else {
			loadImage("src/resources/ice-platform.png");
		}
	}
	
	public void paint(Graphics g){
		initPlatform();
		g.drawImage(getImage(), (int)x, (int)y, null);
	}
	
	public boolean hitTest(double x, double y) {
		return x+Ball.WIDTH > getX() && x < getX() + Platform.WIDTH && 
			   y+Ball.HEIGHT > getY() && y < getY() + Platform.HEIGHT;
	}
	
	public void update() {
		y += dropSpeed;
	}
	
	public boolean isVisible() {
		return y < TheGame.HEIGHT;
	}
	
	public boolean isMonster() {
		return this.monster;
	}
	
	public boolean isCoin() {
		return this.coin;
	}
	
	public void changePlatform() {
		if(this.coin) {
			this.coin = false;
			initPlatform();
		}
			

	}
}
