package bouncingball;

import java.awt.Graphics;

public class Fire extends Object {
	private int speed = 7;
	public Fire(int x, int y) {
		super();
		setX(x);
		setY(y);
		initFire();
	}
	
	private void initFire() {
		loadImage("src/resources/fire.png");
	}
	
	public void paint(Graphics g){
		initFire();
		g.drawImage(getImage(), (int)x+2, (int)y, null);
	}
	
	public void update() {
		y -= speed;
	}
	
	public boolean isVisible() {
		return y < TheGame.HEIGHT;
	}
}
