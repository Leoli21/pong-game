import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
/***
 * 
 * @author Leo Li
 * PONG Ball Class
 *
 */
public class Ball extends JComponent
{
	private Ellipse2D.Double circle;
	private int dx, dy;
	
	public Ball(int x, int y)
	{
		circle = new Ellipse2D.Double(0,0,15,15);
		setBounds(x,y,16,16);
		dx = 0;
		dy = 0;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fill(circle);
	}
	
	public void update()
	{
		setLocation(getX()+dx, getY()+dy);
	}
	
	public void setDx(int x)
	{
		dx = x;
	}
	
	public void setDy(int y)
	{
		dy = y;
	}
	public int getDx()
	{
		return dx;
	}	
	public int getDy()
	{
		return dy;
	}
	
}
