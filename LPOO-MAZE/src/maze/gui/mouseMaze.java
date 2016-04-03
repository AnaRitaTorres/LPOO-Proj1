package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class mouseMaze 
{
	private JFrame frm;
	
	private JPanel contentPane;
	private Image hero;
	private Image heroArmed;
	private Image dragon;
	private Image dragonAsleep;
	private Image exit;
	private Image wall;
	private Image dragonWeapon;
	private Image sword;
	
	public static final int SIDE = 30;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					mouseMaze frame = new mouseMaze(1);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public mouseMaze(int size) 
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
		
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setBounds(100, 100, 557, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frm.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JComboBox<String> typeBox = new JComboBox<String>();
		typeBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Parede", "Herói", "Dragão","Espada","Saída"}));
		typeBox.setToolTipText("");
		typeBox.setBounds(10, 11, 152, 23);
		contentPane.add(typeBox);
		
		JButton endButtom = new JButton("");
		endButtom.setBounds(194, 11, 152, 23);
		contentPane.add(endButtom);
	}
}
