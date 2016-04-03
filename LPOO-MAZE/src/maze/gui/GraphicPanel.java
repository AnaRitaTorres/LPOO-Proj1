package maze.gui;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import maze.logic.MovementType.movementType;
import maze.logic.Play;
import maze.logic.CharacterState.characterState;
import maze.logic.GameState.gameState;
import java.awt.event.KeyAdapter;

public class GraphicPanel extends JPanel  
{
	private Image hero;
	private Image heroArmed;
	private Image dragon;
	private Image dragonAsleep;
	private Image exit;
	private Image wall;
	//private Image floor;
	private Image dragonWeapon;
	private Image sword;
	
	private Play p;
	
	public static final int SIDE = 30;
	
	
	
	public GraphicPanel(Play p, JFrame frame) 
	{
		this.p=p;
		
		addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent arg0) 
			{
				switch(arg0.getKeyCode())
				{
				case KeyEvent.VK_UP:
					p.getLab().move(movementType.UP,p.getHero());
					break;
				case KeyEvent.VK_DOWN:
					p.getLab().move(movementType.DOWN,p.getHero());
					break;
				case KeyEvent.VK_RIGHT:
					p.getLab().move(movementType.RIGHT,p.getHero());
					break;
				case KeyEvent.VK_LEFT:
					p.getLab().move(movementType.LEFT,p.getHero());
					break;
					
				}
				
				p.gamePlayGui();
				if(p.getHero().getState() == characterState.DEAD)
				{
					p.setState(gameState.LOST);
					JOptionPane.showMessageDialog(frame, "Perdeu o Jogo", "Estado do Jogo", JOptionPane.INFORMATION_MESSAGE);
					removeKeyListener(this);
				}
				if(!p.getLab().aliveDragon() && p.pointEquals(p.getLab().getOut(), p.getHero().getCharacterPosition()))
				{
					p.setState(gameState.WON);
					JOptionPane.showMessageDialog(frame, "Ganhou o Jogo", "Estado do Jogo", JOptionPane.INFORMATION_MESSAGE);
					removeKeyListener(this);
				}
			}
			
			@Override
			public void keyReleased(KeyEvent k) {}

			@Override
			public void keyTyped(KeyEvent k) {}

		});
		
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
		char[][] lab =  p.getLab().getMaze();
		
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
