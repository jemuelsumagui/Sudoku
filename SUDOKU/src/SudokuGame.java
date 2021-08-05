import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Color;

import java.net.URL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SudokuGame extends JFrame {
	
	private static final int HEIGHT=570;
	private static final int WIDTH=1000;
	private SudokuFrame newSudokuFrame;
	private static JPanel backgroundPanel;
	private static JLabel background;
	private static JButton playButton;
	private static JButton infoButton;
	private static JButton scoreButton;
	
	//COSTRUTTORE
	public SudokuGame() {
		
		//impostazioni finestra
		super("Sudoku Game");
		
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		
		URL iconSudoku_url = getClass().getResource("puzzle.png");
        Image iconSudoku = new ImageIcon(iconSudoku_url).getImage();
        setIconImage(iconSudoku);
        
        
        //pannello colorato
        Color myColor = new Color(219, 198, 135);
        this.getContentPane().setBackground(myColor);
        
        
        //sfondo
        backgroundPanel = new JPanel();
        
        URL writtenSudoku_url = getClass().getResource("sudokuWritten.jpg");
        ImageIcon writtenSudoku = new ImageIcon(writtenSudoku_url);
        
        background = new JLabel(writtenSudoku);
        
        backgroundPanel.add(background);
        backgroundPanel.setBounds(0, -5, writtenSudoku.getIconWidth(), writtenSudoku.getIconHeight());
        
        
        //bottone play
        URL iconPlay_url = getClass().getResource("play.png");
        playButton = new JButton("Nuova Partita", new ImageIcon(iconPlay_url));
        playButton.setBounds(300, 400, 150, 35);
        
        PlayGameActionListener playGameAL = new PlayGameActionListener();
        playButton.addActionListener(playGameAL);
        
        
        //bottone info
        URL iconInfo_url = getClass().getResource("info.png");
        infoButton = new JButton("Regole", new ImageIcon(iconInfo_url));
        infoButton.setBounds(550, 400, 150, 35);
        
        InstructionsActionListener instructionAL = new InstructionsActionListener();
        infoButton.addActionListener(instructionAL);
        
        
        //bottone scores
        URL iconRank_url = getClass().getResource("ranking.png");
        scoreButton = new JButton("Classifica", new ImageIcon(iconRank_url));
        scoreButton.setBounds(425, 450, 150, 35);
        
        ScoresActionListener scoresAL = new ScoresActionListener();
        scoreButton.addActionListener(scoresAL);
        
        
        //layout
        JLayeredPane layeredPane = new JLayeredPane();
        
        
        //aggiungo tutto alla finestra
        layeredPane.add(backgroundPanel, new Integer(0));
        layeredPane.add(playButton, new Integer(1));
        layeredPane.add(infoButton, new Integer(1));
        layeredPane.add(scoreButton, new Integer(1));
           
        add(layeredPane);
        
        
        //opzioni
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	//FA INIZIARE UNA NUOVA PARTITA A SUDOKU
	private class PlayGameActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			newSudokuFrame = new SudokuFrame();
			newSudokuFrame.setVisible(true);
		}
	}
	
	
	//MOSTRA LE ISTRUZIONI DEL GIOCO
	private class InstructionsActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			InfoFrame newInfoFrame = new InfoFrame();
			newInfoFrame.setVisible(true);
		}
	}
	
	
	//MOSTRA LA CLASSIFICA
	private class ScoresActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Scores newScoresFrame = new Scores();
			newScoresFrame.setVisible(true);
		}
	}
	
	
	
	public static void main(String[] args) {
		SudokuGame window = new SudokuGame();
		
		window.setVisible(true);
	}
}
