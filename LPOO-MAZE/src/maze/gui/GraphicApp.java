package maze.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class GraphicApp 
{
	private JFrame frmgraphic;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					GraphicApp window = new GraphicApp();
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	public GraphicApp()
	{
		frmgraphic = new JFrame ();
		frmgraphic.setTitle("Jogo do Labirinto");
		frmgraphic.setBounds(100,100,450,450);
		frmgraphic.setPreferredSize(new Dimension(450, 450));
		frmgraphic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GraphicPanel panel = new GraphicPanel();
		frmgraphic.getContentPane().add(panel);
		
		frmgraphic.pack(); //dimensões redimensionadas automaticamente
		
		frmgraphic.setVisible(true);
		
		panel.requestFocus();
	}
}
