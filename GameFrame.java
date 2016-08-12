import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class GameFrame extends JFrame implements ActionListener{

	private  GamePanel contentPane;
	
int counter=0;
	int a[] ={0,50,120,170,220,290,350,417};
	Random r=new Random();
	Timer timer;
	public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException, ClassNotFoundException {
		
					GameFrame frame = new GameFrame();
					frame.setVisible(true);
					frame.timer=new Timer(frame.contentPane.toughness,(ActionListener) frame);
					frame.timer.start();
					while(true)
					{
						
						frame.move();
						
						frame.repaint();
						Thread.sleep(20);
					}
			
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int alienX=0;
		int limit=r.nextInt(6);
		int arraySelection;
		int prev=0;
		if(contentPane.aliens.isEmpty())
		{
			counter++;
			if(counter>=5)
			{
				counter=0;
				contentPane.toughness =contentPane.toughness-5;
				if(contentPane.toughness<=20)
				{
					contentPane.toughness=20;
				}
				timer.setDelay(contentPane.toughness);
			}
		for(int i=0;i<limit;i++)
		{
			Alien alien = null;
			for(;;)
			{
				arraySelection=r.nextInt(8);
				if(arraySelection!=prev)
				{
				alienX=a[arraySelection];
				prev=arraySelection;
				break;
				}
				
			}
			try {
				alien=new Alien(contentPane);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			alien.setX(alienX);
			contentPane.aliens.add(alien);
		}
		}
		for(int i=0;i<contentPane.aliens.size();i++)
		{
			try {
				contentPane.aliens.get(i).move();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		contentPane.repaint();
	}
	public void move()
	{
		contentPane.move();
	}


	public GameFrame() throws IOException, URISyntaxException, ClassNotFoundException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 325);
		contentPane = new GamePanel(this);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane);
	}



	

}
