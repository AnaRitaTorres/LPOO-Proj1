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

import maze.logic.Play;

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
	private mouseMazePanel panel;
	private Image hero;
	private Image heroArmed;
	private Image dragon;
	private Image dragonAsleep;
	private Image exit;
	private Image wall;
	private Image dragonWeapon;
	private Image sword;
	private WindowBuilder wb;
	
	public static final int SIDE = 30;

	public mouseMaze(int size, int numDragoes, WindowBuilder wb) 
	{
		this.wb = wb;
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
		
		mouseMazePanel panel = new mouseMazePanel(size, typeBox, numDragoes);
		panel.setBounds(10, 60, 630, 630);
		contentPane.add(panel);
		
		JButton endButtom = new JButton("Jogar");
		endButtom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(panel.findInMaze('S') == 1 && panel.findInMaze('H') == 1 
						&& panel.findInMaze('E') == 1 && panel.findInMaze('D') == numDragoes)
				{
					String selected = (String) wb.typeDrag.getSelectedItem();
					
					wb.warning.setText("Pode Jogar!");
					wb.setPlay(new Play(panel.getCraftedMaze(), wb.typeDragon(selected)));
					wb.lab.setText(wb.getPlay().getLab().toString());
					
					wb.enableMovement();
				}
				
				frm.dispatchEvent(new WindowEvent(frm, WindowEvent.WINDOW_CLOSING));
			}
		});
		endButtom.setBounds(194, 11, 152, 23);
		contentPane.add(endButtom);
		
		
		
	}
	
	public mouseMazePanel getPanel()
	{
		return panel;
	}
}
