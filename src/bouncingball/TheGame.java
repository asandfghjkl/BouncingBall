package bouncingball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TheGame extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 800;
	private int pmax;
	private int tmp;
	private BufferedImage theBuffer;
	private Graphics offScreen;
	private ArrayList<Platform> allPlatforms;
	private ArrayList<Fire> allFires;
	private Ball ball;
	private int score;
	
	public TheGame() {
		super();
		ImageIcon img = new ImageIcon("src/resources/BouncingBall.png");
		this.setIconImage(img.getImage());
		score = 0;
		setTitle(score);
		
		ball = new Ball();
		addKeyListener(ball);
		
	    Platform p = new Platform(score);
	    p.setX(WIDTH/2);
		allPlatforms = new ArrayList<Platform>();
		allPlatforms.add(p);
		
		allFires = new ArrayList<Fire>();
		
		pmax = 20;
		tmp = 0;
		theBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
		offScreen = theBuffer.getGraphics();
		
		new Thread(this).start();
	}

	public void setIcon() {
		ImageIcon img = new ImageIcon("src/resources/ball.png");
		this.setIconImage(img.getImage());
	}
	
	public void setTitle(int score) {
		this.setTitle("Bouncing Ball by SunMoon - " + score + " points");
	}
	
	public static void main(String[] args) {
		start();
		TheGame bouncingball = new TheGame();
		bouncingball.setSize(WIDTH, HEIGHT);
		bouncingball.setLocationRelativeTo(null);
		bouncingball.setResizable(false);
		bouncingball.setVisible(true);
	}
	
	public static void start() {
		JOptionPane.showOptionDialog(null,
									"READY?",
									"START", 
									JOptionPane.OK_OPTION, 
									JOptionPane.WARNING_MESSAGE, 
									null, new String[] {"GO!"}, 0);
	}
	
	 public void paint(Graphics g) {
		if(offScreen == null) return;
		offScreen.setColor(Color.PINK);
		offScreen.fillRect(0, 0, WIDTH, HEIGHT);

		for(Platform p : allPlatforms) {
			p.paint(offScreen);
		}
		
		for(Fire f : allFires) {
			f.paint(offScreen);
		}
		
		ball.paint(offScreen);
		g.drawImage(theBuffer, 0, 0, (ImageObserver) this);
	}
	 
	public void fireMonster() {
		for(int i=0; i<allFires.size(); i++) {
			Fire f = allFires.get(i);
			Rectangle rf = f.getBounds();
			for(int j=0; j<allPlatforms.size(); j++) {
				Platform p = allPlatforms.get(j);
				if(p.isMonster()) {
					Rectangle rp = p.getBounds();
					if(rf.intersects(rp)) {
						allPlatforms.remove(j);
						allFires.remove(i);
					}
				}
			}
		}
	}

	@Override
	public void run() {
		boolean attacked = false;
		while(ball.getY() < HEIGHT) {
			if(attacked) break;
			
			ball.move();
			tmp++;
			if(tmp == pmax) {
				allPlatforms.add(new Platform(score));
				tmp = 0;
			}
			
			if(ball.isSpace()) {
				allFires.add(new Fire(ball.x, ball.y));
			}
			
			fireMonster();
			
			for(int i=0; i < allPlatforms.size(); i++) {
				Platform p = allPlatforms.get(i);
				p.update();
				
				if(p.hitTest(ball.getX(), ball.getY())) {
					if(p.isMonster()) {
						attacked = true;
					} else if(p.isCoin()) {
						p.changePlatform();
						ball.bounce();
						
						this.score+=10;
						setTitle(score);
					} else {
						ball.bounce();
					
						this.score++;
						setTitle(score);
					}
				}
				if(!p.isVisible()) {
					allPlatforms.remove(i);
				}
			}
			for (int i=0; i < allFires.size(); i++) {
	            Fire f = allFires.get(i);
	            f.update();
	            
	            if (!f.isVisible()) {
	            	allFires.remove(i);
	            }
	        }
			
			repaint();
			try {
				Thread.sleep(30);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		JOptionPane.showMessageDialog(null,  
					"SCORE: " + score, 
					"GAME OVER", 
					JOptionPane.OK_OPTION);
		System.exit(0);
	}	
}