package maze.gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import maze.logic.Play;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;

public class GraphicApp 
{
	private JFrame frmgraphic;
	
//	public static void main(String[] args) 
//	{
//		EventQueue.invokeLater(new Runnable() 
//		{
//			public void run() 
//			{
//				try 
//				{
//					GraphicApp window = new GraphicApp();
//				} catch (Exception e) 
//				{
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//	
	public GraphicApp(Play play)
	{
		frmgraphic = new JFrame ();
		frmgraphic.setTitle("Jogo do Labirinto");
		frmgraphic.setBounds(100,100,500,500);
		frmgraphic.setPreferredSize(new Dimension(500, 500));
		//frmgraphic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GraphicPanel panel = new GraphicPanel(play, frmgraphic);
		panel.setBackground(Color.WHITE);
		frmgraphic.getContentPane().add(panel);
		
		frmgraphic.pack(); //dimensões redimensionadas automaticamente
		
		frmgraphic.setVisible(true);
		
		panel.requestFocus();
	}
}
