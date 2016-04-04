package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import maze.cli.Interface;
import maze.logic.CharacterState;
import maze.logic.CharacterState.characterState;
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

	JButton btnEsquerda = new JButton("Esquerda");
	JButton btnDireita = new JButton("Direita");
	JButton btnCima = new JButton("Cima");	
	JButton btnBaixo = new JButton("Baixo");
	JLabel warning = new JLabel("");
	
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
		case "Estáticos":
			return gameState.STATIC;
		case "Adormecidos":
			return gameState.SLEEP;
		case "Aleatórios":
			return gameState.RANDOM;
		default:
			return gameState.STATIC;	
		}
	}
	
	public void enableMovement()
	{
		btnCima.setEnabled(true);
		btnEsquerda.setEnabled(true);
		btnDireita.setEnabled(true);
		btnBaixo.setEnabled(true);
	}
	
	public void disableMovement()
	{
		btnCima.setEnabled(false);
		btnEsquerda.setEnabled(false);
		btnDireita.setEnabled(false);
		btnBaixo.setEnabled(false);
	}
	
	public void changeStatus()
	{
		if(play.getHero().getState() == characterState.DEAD )
		{
			play.setState(gameState.LOST);
			disableMovement();
			warning.setText("Perdeu o Jogo. Pode gerar um novo Labirinto.");
		}

		if(!play.getLab().aliveDragon() && play.pointEquals(play.getLab().getOut(), play.getHero().getCharacterPosition()))
		{
			play.setState(gameState.WON);
			disableMovement();
			warning.setText("Ganhou o Jogo! Pode gerar um novo Labirinto.");
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
		frmJodo.setBounds(150,150,700,700);
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
		typeDrag.setModel(new DefaultComboBoxModel<String>(new String[] {"Estáticos", "Adormecidos", "Aleatórios"}));
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
			public void actionPerformed(ActionEvent arg0) throws IllegalArgumentException
			{
				
				
				int dragoes = Integer.parseInt(numDrag.getText());
				int tamanho = Integer.parseInt(labDim.getText());
				String selected = (String) typeDrag.getSelectedItem();
				
				if (tamanho%2 == 0)
				{
					warning.setText("A Dimensão do Labirinto não pode ser Par.");
					return;
				}
				
				warning.setText("Número de Dragões muito elevado.");
				
				m = new MazeBuilder(tamanho);
				m.buildMaze(tamanho);
				
				for (int i=0;i < dragoes-1;i++)
				{
					m.addDragon(tamanho);
				}
				warning.setText("Pode Jogar!");
				play = new Play(m.getMaze(), typeDragon(selected));
				lab.setText(play.getLab().toString());
				
				enableMovement();
				
				
				
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
		btnCima.setEnabled(false);
		
	
		
		//JButton btnCima = new JButton("Cima");
		btnCima.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				
				play.getLab().move(movementType.UP, play.getHero());
				play.gamePlayGui();
				changeStatus();
				lab.setText(play.getLab().toString());
			}
		});
		btnCima.setBounds(496, 89, 89, 23);
		frmJodo.getContentPane().add(btnCima);
		btnEsquerda.setEnabled(false);
		
		//JButton btnEsquerda = new JButton("Esquerda");
		btnEsquerda.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				play.getLab().move(movementType.LEFT, play.getHero());
				play.gamePlayGui();
				changeStatus();
				lab.setText(play.getLab().toString());
			}
		});
		btnEsquerda.setBounds(444, 123, 89, 23);
		frmJodo.getContentPane().add(btnEsquerda);
		btnDireita.setEnabled(false);
		
		//JButton btnDireita = new JButton("Direita");
		btnDireita.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				play.getLab().move(movementType.RIGHT, play.getHero());
				play.gamePlayGui();
				changeStatus();
				lab.setText(play.getLab().toString());
			}
		});
		btnDireita.setBounds(543, 123, 89, 23);
		frmJodo.getContentPane().add(btnDireita);
		btnBaixo.setEnabled(false);
		
		//JButton btnBaixo = new JButton("Baixo");
		btnBaixo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				play.getLab().move(movementType.DOWN, play.getHero());
				play.gamePlayGui();
				changeStatus();
				lab.setText(play.getLab().toString());
			}
		});
		btnBaixo.setBounds(496, 157, 89, 23);
		frmJodo.getContentPane().add(btnBaixo);
		
		//JLabel warning = new JLabel("");
		warning.setBounds(25, 161, 269, 19);
		frmJodo.getContentPane().add(warning);
		
		JButton btnAbrirJanela = new JButton("Modo Gráfico");
		btnAbrirJanela.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				GraphicApp app = new GraphicApp(play);
				
				play.gamePlayGui();
				changeStatus();
				lab.setText(play.getLab().toString());
			}
		});
		btnAbrirJanela.setBounds(25, 123, 146, 23);
		frmJodo.getContentPane().add(btnAbrirJanela);
		
		JButton btnModoDeDesenho = new JButton("Modo de Desenho");
		btnModoDeDesenho.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int tamanho = Integer.parseInt(labDim.getText());
				int numDragoes = Integer.parseInt(numDrag.getText());
				
				mouseMaze m = new mouseMaze(tamanho, numDragoes);
				
			}
		});
		btnModoDeDesenho.setBounds(187, 123, 146, 23);
		frmJodo.getContentPane().add(btnModoDeDesenho);
		
		
		
	}
}
