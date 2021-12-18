import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
/***
 * 
 * @author Leo Li
 * PONG Main Class
 *
 */
public class PongMain extends JFrame implements ActionListener
{
	private Ball ball;
	private Paddle paddle1;
	private Paddle paddle2;
	private Timer t;
	private int player1Score = 0;
	private int player2Score = 0;
	private JLabel player1Label;
	private JLabel player2Label;
	private PongMiddleLine middleLine;
	
	public static final int accelerator = 2;
	public static final int startingXSpeed = 5;
	public static final int startingYSpeed = 8;
	public PongMain()
	{
		this.setBounds(100,100,800,600);
		this.setLayout(null);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.getContentPane().setBackground(Color.BLACK);
		
		middleLine = new PongMiddleLine(400,0);
		add(middleLine);
		
		paddle1 = new Paddle(80,200);
		paddle2 = new Paddle(700,200);
		add(paddle1);
		add(paddle2);
		
		ball = new Ball(400,(int)(Math.random()*this.getHeight()));
		Random r = new Random();
		int randXDirection = r.nextInt(2);
		int randYDirection = r.nextInt(2);
		
		if(randXDirection == 0)
			randXDirection--;
		ball.setDx(randXDirection*startingXSpeed);
		
		if(randYDirection == 0)
			randYDirection--;
		ball.setDy(randYDirection*startingYSpeed);
	
		add(ball);
		
		player1Label = new JLabel("" + player1Score);
		player1Label.setFont(new Font("Serif", Font.BOLD, 55));
		player1Label.setForeground(Color.WHITE);
		player1Label.setBounds(100,5,100,200);
		add(player1Label);
		
		player2Label = new JLabel("" + player2Score);
		player2Label.setFont(new Font("Serif", Font.BOLD, 55));
		player2Label.setForeground(Color.WHITE);
		player2Label.setBounds(650,5,100,200);
		add(player2Label);
		
		t = new Timer(20,this);
		t.start();
		
		//Player 1 Movement (WASD)
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == e.VK_W)
				{
					if(paddle1.getY() < 0)
						paddle1.setDy(0);
					paddle1.setDy(-20);
				}
				else if(e.getKeyCode() == e.VK_S) {
					if(paddle1.getY()+paddle1.getHeight() > getHeight()-getInsets().top-getInsets().bottom)
						paddle1.setDy(0);
					paddle1.setDy(20);
				}
				
			}
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(e.getKeyCode() == e.VK_W) 
					paddle1.setDy(0);
				else if(e.getKeyCode() == e.VK_S)
					paddle1.setDy(0);
				
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}			
		});
			
			
		
		//Player 2 Movement (Arrow Keys)	
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == e.VK_UP)
				{
					if(paddle2.getY() < 0)
						paddle2.setDy(0);
					paddle2.setDy(-20);
				}
				else if(e.getKeyCode() == e.VK_DOWN) {
					if(paddle2.getY()+paddle2.getHeight() > getHeight()-getInsets().top-getInsets().bottom)
						paddle2.setDy(0);
					paddle2.setDy(20);
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(e.getKeyCode() == e.VK_UP) 
					paddle2.setDy(0);
				else if(e.getKeyCode() == e.VK_DOWN)
					paddle2.setDy(0);
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
				
				
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	

	public static void main(String[] args) 
	{
		new PongMain();

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{		
		Random r = new Random();
		Insets insets = this.getInsets();
		if(paddle1.getY() < 0)
		{
			paddle1.setLocation(paddle1.getX(),1);
		}
		else if(paddle1.getY() + paddle1.getHeight() > getHeight()-getInsets().top-getInsets().bottom)
		{
			paddle1.setLocation(paddle1.getX(), getHeight()-getInsets().top-getInsets().bottom-paddle1.getHeight());
		}
		paddle1.update();
		
		if(paddle2.getY() < 0)
		{
			paddle2.setLocation(paddle2.getX(),1);
		}
		else if(paddle2.getY() + paddle2.getHeight() > getHeight()-getInsets().top-getInsets().bottom) 
		{
			paddle2.setLocation(paddle2.getX(), getHeight()-getInsets().top-getInsets().bottom-paddle2.getHeight());	
		}
		paddle2.update();
		
		ball.update();
		if(ballCollisionPaddle1())
		{
			ball.setDx((Math.abs(ball.getDx())+accelerator)*1);
			ball.setDy((Math.abs(ball.getDy())+accelerator)*1);
		}
		if(ballCollisionPaddle2())
		{
			ball.setDx((Math.abs(ball.getDx())+accelerator)*-1);
			ball.setDy((Math.abs(ball.getDy())+accelerator)*-1);
		}
		if(ball.getX() < 0)
		{
			remove(ball);
			player2Score++;
			player2Label.setText("" + player2Score);
			ball = new Ball(400,(int)(Math.random()*this.getHeight()));
			int randXDirection = r.nextInt(2);
			int randYDirection = r.nextInt(2);
			if(randXDirection == 0)
				randXDirection--;
			ball.setDx(randXDirection*startingXSpeed);
			if(randYDirection == 0)
				randYDirection--;
			ball.setDy(randYDirection*startingYSpeed);
			add(ball);
		}
		else if(ball.getX() + ball.getWidth()> this.getWidth()-insets.right-insets.left)
		{
			remove(ball);
			player1Score++;
			player1Label.setText("" + player1Score);
			ball = new Ball(400,(int)(Math.random()*this.getHeight()));
			int randXDirection = r.nextInt(2);
			int randYDirection = r.nextInt(2);
			if(randXDirection == 0)
				randXDirection--;
			ball.setDx(randXDirection*startingXSpeed);
			if(randYDirection == 0)
				randYDirection--;
			ball.setDy(randYDirection*startingYSpeed);
			add(ball);
		}
		else if(ball.getY() < 0)
		{
			ball.setDy(+5);
		}
		else if(ball.getY() + ball.getHeight() > this.getHeight()-insets.top-insets.bottom)
		{
			ball.setDy(-5);
		}
		
		if(player1Score >= 10)
		{
			t.stop();
			JOptionPane.showMessageDialog(this,"GAME OVER\nPlayer 1 Wins!");
			this.dispose();
		}
		else if(player2Score >= 10)
		{
			t.stop();
			JOptionPane.showMessageDialog(this,"GAME OVER\nPlayer 2 Wins!");
			this.dispose();
		}
		repaint();
	}

	
	public boolean ballCollisionPaddle1()
	{
		Rectangle paddle1Rectangle = paddle1.getBounds();
		Rectangle ballRectangle = ball.getBounds();
		if(ballRectangle.intersects(paddle1Rectangle))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean ballCollisionPaddle2()
	{
		Rectangle paddle2Rectangle = paddle2.getBounds();
		Rectangle ballRectangle = ball.getBounds();
		if(ballRectangle.intersects(paddle2Rectangle))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
