import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
/***
 * 
 * @author Leo Li
 * PONG Paddle Class
 *
 */
public class Paddle extends JComponent
{
	private Rectangle paddle;
	private int dy;
	
	public Paddle(int x, int y)
	{
		paddle = new Rectangle(0,0,15,75);
		setBounds(x,y,16,76);
		dy = 0;
		
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fill(paddle);
	}
	
	public void update()
	{
		setLocation(getX(), getY()+dy);
	}
	
	public void setDy(int y)
	{
		dy = y;
	}
	
}
