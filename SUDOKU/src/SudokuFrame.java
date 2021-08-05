import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SpringLayout;

import java.awt.Image;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;


public class SudokuFrame extends JFrame {
	
	private static final int HEIGHT=550;
	private static final int WIDTH=520;
	private static JPanel panel;
	private static JPanel coloredPanel;
	private static Chronometer chrono;
	public static int sec;
	public static int min;
	
	
	//COSTRUTTORE
	public SudokuFrame(){
		
		//impostazioni finestra
		super("Sudoku");
		
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		
		URL iconGame_url = getClass().getResource("sudoku1.png");
        Image iconGame = new ImageIcon(iconGame_url).getImage();
        setIconImage(iconGame);
        
        panel = new JPanel();
        
        //pannello colorato
        coloredPanel = new JPanel();
        SpringLayout layout = new SpringLayout();
        coloredPanel.setLayout(layout);
        
        Color myColor = new Color(219, 198, 135);
        coloredPanel.setBackground(myColor);
        coloredPanel.setPreferredSize(new Dimension(500, 500));
        
        
        //griglia del sudoku
		ButtonGridPanel panelGrid = new ButtonGridPanel();
		
		
		//bottone reset
		JButton resetButton = new JButton("Reset");
		ResetButtonListener resetListener = new ResetButtonListener(this);
		resetButton.addActionListener(resetListener);
		
		
		//bottone end game
		JButton endGameButton = new JButton("Fine");
		EndGameButtonListener endGameListener = new EndGameButtonListener();
		endGameButton.addActionListener(endGameListener);
		
		
		//label cronometro
		JLabel chronometer = new JLabel("00:00");
		chronometer.setFont(new Font("Arial", Font.PLAIN, 20));
		chronometer.setPreferredSize(new Dimension(80, 50));
		URL iconChrono_url = getClass().getResource("chrono.png");
		chronometer.setIcon(new ImageIcon(iconChrono_url));
		chrono = new Chronometer(chronometer);
		chrono.startChronometer();
		
		
		//layout
		layout.putConstraint(SpringLayout.WEST, panelGrid, 50, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, panelGrid, 40, SpringLayout.NORTH, panel);
		
		layout.putConstraint(SpringLayout.WEST, resetButton, 0, SpringLayout.WEST, panelGrid);
		layout.putConstraint(SpringLayout.NORTH, resetButton, 10, SpringLayout.SOUTH, panelGrid);
		
		layout.putConstraint(SpringLayout.EAST, endGameButton, 0, SpringLayout.EAST, panelGrid);
		layout.putConstraint(SpringLayout.NORTH, endGameButton, 10, SpringLayout.SOUTH, panelGrid);
		
		layout.putConstraint(SpringLayout.SOUTH, chronometer, 10, SpringLayout.NORTH, panelGrid);
		layout.putConstraint(SpringLayout.EAST, chronometer, 0, SpringLayout.EAST, panelGrid);
		
		
		//aggiungo tutto alla finestra
		coloredPanel.add(panelGrid);
		coloredPanel.add(resetButton);
		coloredPanel.add(endGameButton);
		coloredPanel.add(chronometer);
		
		panel.add(coloredPanel);
		add(panel);
		
		
		//opzioni di chiusura
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	
	//ACTION LISTENER PER IL PUNSANTE DI END GAME
	private class EndGameButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			EndGameFrame window = new EndGameFrame();
			window.setVisible(true);
			chrono.stopChronometer();
			min=chrono.getMinutes();
			sec=chrono.getSeconds();
		}
	}
	

	//ACTION LISTENER PER IL PULSANTE DI RESET
	private class ResetButtonListener implements ActionListener{
		
		private JFrame window;
		
		public ResetButtonListener (JFrame w) {
			window=w;
		}
		
		public void actionPerformed(ActionEvent e) {
			window.dispose();
			SudokuFrame newSudokuFrame = new SudokuFrame();
			newSudokuFrame.setVisible(true);
		}
	}
	
}

