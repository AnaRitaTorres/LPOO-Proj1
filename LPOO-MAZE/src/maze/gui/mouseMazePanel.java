package maze.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JPanel;


public class mouseMazePanel extends JPanel implements MouseListener
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
	
	int size;
	int numDragoes;
	
	JComboBox<String> typeBox;
	
	public static final int SIDE = 30;

	/**
	 * Create the panel.
	 */
	public mouseMazePanel(int size, JComboBox<String> typeBox, int numDragoes) 
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
		
		this.size = size;
		this.numDragoes = numDragoes;
		this.typeBox = typeBox;
		
		
		hero = hero.getScaledInstance(SIDE, SIDE, 1);
		heroArmed =  heroArmed.getScaledInstance(SIDE, SIDE, 1);
		sword =  sword.getScaledInstance(SIDE, SIDE, 1);
		wall = wall.getScaledInstance(SIDE, SIDE, 1);
		dragonAsleep = dragonAsleep.getScaledInstance(SIDE, SIDE, 1);
		dragonWeapon = dragonWeapon.getScaledInstance(SIDE, SIDE, 1);
		dragon =  dragon.getScaledInstance(SIDE, SIDE, 1);
		exit =  exit.getScaledInstance(SIDE, SIDE, 1);
		
		addMouseListener(this);
		
		lab = new char [size][size];
		
		for ( int i =0; i < size; i++)
		{
			for(int j=0; j < size; j++)
			{
				if(i == 0 || i == size-1 || j == 0|| j == size-1)
					lab[i][j]= 'X';
				else
					lab[i][j]= ' ';
			}
		}
		
	}
	
	public int findInMaze(char c)
	{
		int sum = 0;
		for ( int i =0; i < size; i++)
		{
			for(int j=0; j < size; j++)
				if(lab[i][j] == c)
					sum++;
		}
		return sum;
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
	
	@Override
	public void mousePressed(MouseEvent e) 
	{	
		float mouseX = e.getX();
		float mouseY = e.getY();
		
		System.out.print(mouseX);
		System.out.print('\n');
		System.out.print(mouseY);
		System.out.print('\n');

		int labX = (int) mouseX / SIDE;
		int labY = (int) mouseY / SIDE;
		
		System.out.print(labX);
		System.out.print('\n');
		System.out.print(labY);
		System.out.print('\n');
		
		
		String selected = (String) typeBox.getSelectedItem();
		
		switch(selected)
		{
		case "Parede":
			lab[labY][labX] = 'X';
			break;
			
		case "Chão":
			lab[labY][labX] = ' ';
			break;
			
		case "Herói":
			if(findInMaze('H') != 1)
				lab[labY][labX] = 'H';
			break;
			
		case "Espada":
			if(findInMaze('E') != 1)
				lab[labY][labX] = 'E';
			break;
			
		case "Dragão":
			if(findInMaze('D') != numDragoes)
				lab[labY][labX] = 'D';
			break;
				
		case "Saída":
			if(findInMaze('S') != 1 && labX == size-1 && labY != 0 && labY != size-1)
				lab[labY][labX] = 'S';
			break;
		}
	
		repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{	
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		
	}
	
	public char[][] getCraftedMaze()
	{
		return lab;
	}
	
}
