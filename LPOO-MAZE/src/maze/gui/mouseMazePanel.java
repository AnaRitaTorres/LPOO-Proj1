package maze.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class mouseMazePanel extends JPanel 
{
	private Image hero;
	private Image heroArmed;
	private Image dragon;
	private Image dragonAsleep;
	private Image exit;
	private Image wall;
	private Image dragonWeapon;
	private Image sword;
	private char [][] lab;
	
	public static final int SIDE = 30;

	/**
	 * Create the panel.
	 */
	public mouseMazePanel() 
	{
		try 
		{
			hero =  ImageIO.read(new File("images/hero.png"));
			heroArmed =  ImageIO.read(new File("images/heroArmed.png"));
			sword =  ImageIO.read(new File("images/sword.png"));
			wall =  ImageIO.read(new File("images/wall.png"));
			dragonAsleep =  ImageIO.read(new File("images/dragonAsleep.png"));
			dragonWeapon = ImageIO.read(new File("images/dragonWeapon.png"));
			dragon =  ImageIO.read(new File("images/dragon.png"));
			exit= ImageIO.read(new File("images/chest.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		hero = hero.getScaledInstance(SIDE, SIDE, 1);
		heroArmed =  heroArmed.getScaledInstance(SIDE, SIDE, 1);
		sword =  sword.getScaledInstance(SIDE, SIDE, 1);
		wall = wall.getScaledInstance(SIDE, SIDE, 1);
		dragonAsleep = dragonAsleep.getScaledInstance(SIDE, SIDE, 1);
		dragonWeapon = dragonWeapon.getScaledInstance(SIDE, SIDE, 1);
		dragon =  dragon.getScaledInstance(SIDE, SIDE, 1);
		exit =  exit.getScaledInstance(SIDE, SIDE, 1);
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
				
		for(int i=0; i < lab.length;i++ )
		{
			for(int j=0;j <lab[i].length;j++)
			{
				if (lab[j][i]== 'X')
				{
					g.drawImage(wall, i*SIDE, j*SIDE, null);
					repaint();
				}
				
				if (lab[j][i] == 'H')
				{
					g.drawImage(hero, i*SIDE, j*SIDE, null);
					repaint();
				}
				
				if (lab[j][i] == 'E')
				{
					g.drawImage(sword, i*SIDE, j*SIDE, null);
					repaint();
				}
				
				if (lab[j][i] == 'D')
				{
					g.drawImage(dragon, i*SIDE, j*SIDE, null);
					repaint();
				}
				
				if (lab[j][i] == 'd')
				{
					g.drawImage(dragonAsleep, i*SIDE, j*SIDE, null);
					repaint();
				}
				
				if (lab[j][i] == 'A')
				{
					g.drawImage(heroArmed, i*SIDE, j*SIDE, null);
					repaint();
				}
				
				if (lab[j][i] == 'F')
				{
					g.drawImage(dragonWeapon, i*SIDE, j*SIDE, null);
					repaint();
				}
				
				if (lab[j][i] == 'S')
				{
					g.drawImage(exit, i*SIDE, j*SIDE, null);
					repaint();
				}
				
			}
		}
		
	}
}
