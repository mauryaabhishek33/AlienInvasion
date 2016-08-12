import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

public class GamePanel extends JPanel implements Serializable{
    Icon icon;
    JLabel lblNewLabel = new JLabel("");
    int x=216;
    int y=243;
    ArrayList<Bullet1> bullets=new ArrayList<Bullet1>();
    ArrayList<Alien> aliens=new ArrayList<Alien>();
    BufferedImage img;
    int score=0;
    GameFrame frame;
    HighScore highscore;
    int highScore1;
    File file=new File(getClass().getResource("highscore.txt").toURI());
    int toughness=60;
	public GamePanel(GameFrame gameFrame) throws IOException, URISyntaxException, ClassNotFoundException {
		try{
			  ObjectInputStream oi=new ObjectInputStream(new FileInputStream(file));
		 highscore=(HighScore) oi.readObject();
	       oi.close();
	       if(highscore!=null)
	           highScore1=highscore.hS;
	           else
	           {
	        	   highscore=new HighScore();
	        	   highScore1=0;
	           }
		}
		catch(EOFException e)
		{
			 highscore=new HighScore();
			highScore1=0;
		}
		 catch(InvalidClassException e )
        {
			 highscore=new HighScore();
        	highScore1=0;
        }
       
		frame=gameFrame;
		setBackground(Color.WHITE);
        this.setLayout(null); 
        img=ImageIO.read(new File(GamePanel.class.getResource("alien.png").toURI()));
       
       
      
        lblNewLabel.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		int keycode=e.getKeyCode();
        		if(keycode==KeyEvent.VK_RIGHT)
        		{
        			x=x+20;
        			if(x+35>=458)
        			{
        				x=458-35;
        			}
        			lblNewLabel.setBounds(x, y, 35, 51);
        		}
        		else if(keycode==KeyEvent.VK_LEFT)
        		{
        			x=x-20;
        			if(x<=0)
        			{
        				x=0;
        			}
        			lblNewLabel.setBounds(x, y, 35, 51);
        		}
        		else if(keycode==KeyEvent.VK_SPACE)
        		{
        			Bullet1 bullet=new Bullet1(GamePanel.this);
        			bullets.add(bullet);
        		}
        		
        	}
        });
        lblNewLabel.setBounds(x, y, 35, 51);
        icon=new ImageIcon(GamePanel.class.getResource("spaceship.png"));
        lblNewLabel.setIcon(icon);
        lblNewLabel.setFocusable(true);
        add(lblNewLabel);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Verdana", Font.ITALIC, 20));
		g.drawString("Your Score", 0, 20);
		g.drawString(String.valueOf(score), 0, 35);
		g.drawString("High Score", 350, 20);
		g.drawString(String.valueOf(highScore1), 415, 35);
		for(int i=0;i<bullets.size();i++)
		{
			bullets.get(i).paint(g);
		}
		for(int i=0;i<aliens.size();i++)
		{
			aliens.get(i).paint(g);
		}
	}
	public void move()
	{
		
		for(int i=0;i<bullets.size();i++)
		{
			
			bullets.get(i).move();
		}
	}
	public void gameOver() throws FileNotFoundException, URISyntaxException, IOException
	{
		int value;
		value=JOptionPane.showConfirmDialog(this, "YOUR SCORE WAS "+score+"\n DO YOU WANT TO RESTART", "GAME OVER", 0, 1);
		if(score>this.highScore1)
		{
			highScore1=score;
			highscore.setHighScore(highScore1);
			
			 ObjectOutputStream oo=new ObjectOutputStream(new FileOutputStream(file));
			oo.writeObject(highscore);
			oo.writeObject(null);
			oo.close();
		}
		if(value==0)
		{
			reset();
		}
		else if(value==1)
		{
			System.exit(0);
		}
	}
	private void reset() {
		aliens.clear();
		bullets.clear();
		x=216;
		y=243;
		lblNewLabel.setBounds(x, y, 35, 51);
		score=0;
		toughness=60;
		
	}
	
	
}
