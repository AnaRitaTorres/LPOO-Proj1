package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
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
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class mouseMaze 
{
	private JFrame frm;
	private JPanel contentPane;
	private mouseMazePanel mousePanel;
	private Image hero;
	private Image heroArmed;
	private Image dragon;
	private Image dragonAsleep;
	private Image exit;
	private Image wall;
	private Image dragonWeapon;
	private Image sword;
	
	public static final int SIDE = 30;

	public mouseMaze(int size, int numDragoes) 
	{
		frm = new JFrame();
		frm.setBounds(100, 100, 707, 743);
		//frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frm.setContentPane(contentPane);
		contentPane.setLayout(null);
		frm.setVisible(true);
		
		JComboBox<String> typeBox = new JComboBox<String>();
		typeBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Parede", "Chão", "Herói", "Dragão","Espada", "Saída"}));
		typeBox.setToolTipText("");
		typeBox.setBounds(10, 11, 152, 23);
		contentPane.add(typeBox);
		
		JButton endButtom = new JButton("");
		endButtom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//frm.dispatchEvent(new WindowEvent(frm, WindowEvent.WINDOW_CLOSING));
			}
		});
		endButtom.setBounds(194, 11, 152, 23);
		contentPane.add(endButtom);
		
		JPanel panel = new mouseMazePanel(size, typeBox, numDragoes);
		panel.setBounds(10, 60, 630, 630);
		contentPane.add(panel);
		
	}
}
