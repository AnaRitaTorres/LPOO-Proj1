package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import maze.logic.MazeBuilder;

import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WindowBuilder {

	private JFrame frmJodo;
	private JTextField labDim;
	private JTextField numDrag;
	private static MazeBuilder m = new MazeBuilder(5);

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
		frmJodo.setBounds(100, 100, 591, 339);
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
		
		JComboBox typeDrag = new JComboBox();
		typeDrag.setBounds(164, 70, 95, 20);
		frmJodo.getContentPane().add(typeDrag);
		
		JTextArea lab = new JTextArea();
		lab.setEditable(false);
		lab.setFont(new Font("Courier New", Font.PLAIN, 13));
		lab.setBounds(10, 98, 281, 201);
		frmJodo.getContentPane().add(lab);
		
		JButton btnGerarNovoLabirinto = new JButton("Gerar Novo Labirinto");
		btnGerarNovoLabirinto.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				int tamanho = Integer.parseInt(labDim.getText());
				m.buildMaze(tamanho);
				lab.setText(m.toString());
				
				
			}
		});
		btnGerarNovoLabirinto.setBounds(352, 28, 191, 23);
		frmJodo.getContentPane().add(btnGerarNovoLabirinto);
		
		JButton btnTerminarPrograma = new JButton("Terminar Programa");
		btnTerminarPrograma.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);
			}
		});
		btnTerminarPrograma.setBounds(352, 56, 191, 23);
		frmJodo.getContentPane().add(btnTerminarPrograma);
		
	
		
		JButton btnCima = new JButton("Cima");
		btnCima.setBounds(407, 129, 89, 23);
		frmJodo.getContentPane().add(btnCima);
		
		JButton btnEsquerda = new JButton("Esquerda");
		btnEsquerda.setBounds(355, 163, 89, 23);
		frmJodo.getContentPane().add(btnEsquerda);
		
		JButton btnDireita = new JButton("Direita");
		btnDireita.setBounds(454, 163, 89, 23);
		frmJodo.getContentPane().add(btnDireita);
		
		JButton btnBaixo = new JButton("Baixo");
		btnBaixo.setBounds(407, 197, 89, 23);
		frmJodo.getContentPane().add(btnBaixo);
	}
}
