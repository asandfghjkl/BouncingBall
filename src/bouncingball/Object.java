package bouncingball;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Object {
	protected int x, y;
	protected static int WIDTH;
	protected static int HEIGHT;
	protected Image image;
	protected boolean visible;
	
	public Object (){
		
	}
	
	protected void loadImage(String loc) {
		ImageIcon img = new ImageIcon(loc);
		image = img.getImage();
		HEIGHT = image.getHeight(null);
		WIDTH = image.getWidth(null);
	}
	
	public Image getImage() {
        return image;
    }
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = (int) x;
	}	
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = (int) y;
	}
	
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
