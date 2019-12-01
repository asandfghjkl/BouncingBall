package bouncingball;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ball extends Object implements KeyListener {
	private double vX,vY;
	private boolean leftPressed,rightPressed;
	
	public Ball() {
		super();
		
		leftPressed = rightPressed = false;
		x = TheGame.WIDTH / 2;
		y = -50;
		vX = vY = 0.0;
		
		initBall();
	}
	
	public void initBall() {
		loadImage("src/resources/ball.png");
	} 
	
	public void paint(Graphics g){
		initBall();
		g.drawImage(getImage(), (int)x, (int)y, null);
	}
	
	public void bounce(){ 
		vY = -5; 
	}

	public void move(){
		x += vX;
		y += vY;
		vY += 0.3;
		vX *= 0.95;
		
		if(leftPressed)
			vX -= 1;
		if(rightPressed)
			vX +=1;
		if(x<0 && vX<0) 
			vX *= -1;
		if(x + WIDTH > TheGame.WIDTH && vX > 0) 
			vX *= -1;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==37) leftPressed = true;
		if(e.getKeyCode()==39) rightPressed = true;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==37) leftPressed = false;
		if(e.getKeyCode()==39) rightPressed = false;
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
