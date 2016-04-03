package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Panel;

public class GraphicPanel extends JPanel implements ActionListener 
{
	private BufferedImage hero;
	private BufferedImage heroArmed;
	private BufferedImage dragon;
	private BufferedImage dragonAsleep;
	private BufferedImage exit;
	private BufferedImage rock;
	private BufferedImage floor;
	
	public GraphicPanel() 
	{
		try 
		{
			hero =  ImageIO.read(new File("images/hero.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(hero, 0, 0, 100, 100, 0, 0, hero.getWidth(), hero.getHeight(), null);
		
	}
	
	
}
