import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

public class Alien {
	private GamePanel game;
	
	int x=0,y=0;
	public Alien(GamePanel game) throws IOException, URISyntaxException
	{
		this.game=game;
	}
	public void paint(Graphics g)
	{
		ImageObserver observer = null;
		g.drawImage(game.img, x, y, observer);
	}
	public void move() throws FileNotFoundException, URISyntaxException, IOException
	{
		if(collision())
		{
			game.gameOver();
		}
		if(isAtEnd())
		{
			
			game.gameOver();
		}
		else{
		y=y+1;
		}
		
	}
	public void setX(int x)
	{
		this.x=x;
	}
	public boolean isAtEnd()
	{
		if(y+game.img.getHeight()>=295)
		{
			return true;
		}
		return false;
	}
	public boolean collision()
	{
		if(getBounds().intersects(new Rectangle(game.x,game.y,35,51)))
		{
			return true;
		}
		return false;
	}
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,game.img.getWidth(),game.img.getHeight());
	}
}
