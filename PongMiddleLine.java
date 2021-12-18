import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
/***
 * 
 * @author Leo Li
 * PONG Middle Line Class
 *
 */
public class PongMiddleLine extends JComponent
{
	private Rectangle middleLine;
	
	public PongMiddleLine(int x, int y)
	{
		middleLine = new Rectangle(0,0,10,600);
		setBounds(x,y,10,600);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.YELLOW);
		g2.fill(middleLine);
	}
	
}
