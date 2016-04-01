package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import maze.cli.Interface;
import maze.logic.GameState.gameState;
import maze.logic.MazeBuilder;
import maze.logic.MovementType.movementType;
import maze.logic.Play;

import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;

public class WindowBuilder {

	private JFrame frmJodo;
	private JTextField labDim;
	private JTextField numDrag;
	private static MazeBuilder m;
	private Play play;
	private Interface i;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					WindowBuilder window = new WindowBuilder();
					window.frmJodo.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public gameState typeDragon(String type)
	{
		switch(type)
		{
		case "Static":
			return gameState.STATIC;
		case "Sleep":
			return gameState.SLEEP;
		case "Random":
			return gameState.RANDOM;
		default:
			return gameState.STATIC;	
		}
	}

	/**
	 * Create the application.
	 */
	public WindowBuilder() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frmJodo = new JFrame();
		frmJodo.setResizable(false);
		frmJodo.setTitle("Jogo do Labirinto");
		frmJodo.setBounds(10,10,700,700);
		frmJodo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJodo.getContentPane().setLayout(null);
		
		JLabel lblDimensoDoLabirinto = new JLabel("Dimens\u00E3o do Labirinto");
		lblDimensoDoLabirinto.setBounds(25, 14, 143, 14);
		frmJodo.getContentPane().add(lblDimensoDoLabirinto);
		
		labDim = new JTextField();
		labDim.setBounds(164, 11, 46, 20);
		frmJodo.getContentPane().add(labDim);
		labDim.setColumns(10);
		
		JLabel lblNmeroDeDrages = new JLabel("N\u00FAmero de Drag\u00F5es");
		lblNmeroDeDrages.setBounds(25, 42, 129, 14);
		frmJodo.getContentPane().add(lblNmeroDeDrages);
		
		numDrag = new JTextField();
		numDrag.setColumns(10);
		numDrag.setBounds(164, 39, 46, 20);
		frmJodo.getContentPane().add(numDrag);
		
		JLabel lblTipoDeDrages = new JLabel("Tipo de Drag\u00F5es");
		lblTipoDeDrages.setBounds(25, 73, 95, 14);
		frmJodo.getContentPane().add(lblTipoDeDrages);
		
		JComboBox<String> typeDrag = new JComboBox<String>();
		typeDrag.setModel(new DefaultComboBoxModel<String>(new String[] {"Static", "Sleep", "Random"}));
		typeDrag.setToolTipText("");
		typeDrag.setBounds(164, 70, 95, 20);
		frmJodo.getContentPane().add(typeDrag);
		
		JTextArea lab = new JTextArea();
		lab.setEditable(false);
		lab.setFont(new Font("Courier New", Font.PLAIN, 13));
		lab.setBounds(16, 215, 661, 445);
		frmJodo.getContentPane().add(lab);
		
		JButton btnGerarNovoLabirinto = new JButton("Gerar Novo Labirinto");
		btnGerarNovoLabirinto.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				int dragoes = Integer.parseInt(numDrag.getText());
				int tamanho = Integer.parseInt(labDim.getText());
				String selected = (String) typeDrag.getSelectedItem();
				
				m = new MazeBuilder(tamanho);
				m.buildMaze(tamanho);
				
				for (int i=0;i < dragoes-1;i++)
				{
					m.addDragon(tamanho);
				}
				
				play = new Play(m.getMaze(), typeDragon(selected));
				lab.setText(play.getLab().toString());
				
				
				
			}
		});
		btnGerarNovoLabirinto.setBounds(441, 27, 191, 23);
		frmJodo.getContentPane().add(btnGerarNovoLabirinto);
		
		JButton btnTerminarPrograma = new JButton("Terminar Programa");
		btnTerminarPrograma.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);
			}
		});
		btnTerminarPrograma.setBounds(441, 55, 191, 23);
		frmJodo.getContentPane().add(btnTerminarPrograma);
		
	
		
		JButton btnCima = new JButton("Cima");
		btnCima.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				
				play.getLab().move(movementType.UP, play.getHero());
				lab.setText(play.getLab().toString());
			}
		});
		btnCima.setBounds(496, 89, 89, 23);
		frmJodo.getContentPane().add(btnCima);
		
		JButton btnEsquerda = new JButton("Esquerda");
		btnEsquerda.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				play.getLab().move(movementType.LEFT, play.getHero());
				lab.setText(play.getLab().toString());
			}
		});
		btnEsquerda.setBounds(444, 123, 89, 23);
		frmJodo.getContentPane().add(btnEsquerda);
		
		JButton btnDireita = new JButton("Direita");
		btnDireita.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				play.getLab().move(movementType.RIGHT, play.getHero());
				lab.setText(play.getLab().toString());
			}
		});
		btnDireita.setBounds(543, 123, 89, 23);
		frmJodo.getContentPane().add(btnDireita);
		
		JButton btnBaixo = new JButton("Baixo");
		btnBaixo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				play.getLab().move(movementType.DOWN, play.getHero());
				lab.setText(play.getLab().toString());
			}
		});
		btnBaixo.setBounds(496, 157, 89, 23);
		frmJodo.getContentPane().add(btnBaixo);
		
		JLabel warning = new JLabel("");
		warning.setBounds(37, 161, 269, 19);
		frmJodo.getContentPane().add(warning);
		
		
	}
}
