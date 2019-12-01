package bouncingball;

import java.awt.Graphics;

public class Platform extends Object{
	private static double dropSpeed;
	private static boolean firsttime = true;
	
	public Platform() {
		super();
		if(firsttime) {
			dropSpeed = 2;
			firsttime = false;
		}
		x = (int)(Math.random()*(TheGame.WIDTH-WIDTH));
		y = HEIGHT;
		
		initPlatform();
	}
	
	public void initPlatform() {
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

}
