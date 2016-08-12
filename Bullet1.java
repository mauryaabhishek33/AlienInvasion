import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Bullet1 {

	GamePanel game;
	int y;
	int x;
	
	public Bullet1(GamePanel game)
	{
		this.game=game;
		x=game.x;
		y=game.y;
		
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(x+17,y, 3, 8);
		
	}
	public void move()
	{
		if(isAtTop())
		{
			game.bullets.remove(this);
		}
		if(collision())
		{
			 
			 
			 
		}
		else
		{
		y=y-5;
		}
	}
	public boolean collision()
	{
		for(int i=0;i<game.aliens.size();i++)
		{
			if(getBounds().intersects(game.aliens.get(i).getBounds()))
					{
				
				        game.bullets.remove(this);
				       
				          game.aliens.remove(i);
				          game.score++;
				           return true;
					}
		}
		return false;
	}
	public boolean isAtTop()
	{
		if(y<=-8)
		{
			return true;
			
		}
		return false;
	}
	public Rectangle getBounds()
	{
		return new Rectangle(x+17,y,3,8);
	}
}
