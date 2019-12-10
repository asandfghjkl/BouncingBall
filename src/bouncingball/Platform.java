package bouncingball;

import java.awt.Graphics;

public class Platform extends Object{
	private static double dropSpeed;
	private static boolean firsttime = true;
	private boolean monster;
	
	public Platform(int score) {
		super();
		double r = Math.random();
		
		if(r < 0.2 && score%5==0 && score > 10) this.monster = true;
		else if(r < 0.3) this.monster = false;
		else this.monster = false;
		
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
			if(this.x < TheGame.WIDTH/2) 
				loadImage("src/resources/leftmonster.png");
			else 
				loadImage("src/resources/rightmonster.png");
		}
		else
			loadImage("src/resources/ice-platform.png");
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
}
