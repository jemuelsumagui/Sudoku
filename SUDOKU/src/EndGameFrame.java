import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.net.URL;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EndGameFrame extends JFrame {
	
	private static final int GRID_SIZE=9;
	private static JPanel panel;
	private static JPanel coloredPanel;
	private static JLabel txt;
	private static JButton[][] numbers = ButtonGridPanel.numbers;
	private static SudokuOutputGrid sudokuGrid = new SudokuOutputGrid();
	private static int[][] sudokuSolution = sudokuGrid.getSolutionGrid();
	private static JButton save;
	private Player player;
	private ArrayList<Player> players;
	private String nome;
	
	
	//COSTRUTTORE
	public EndGameFrame() {
		
		//impostazioni finestra
		super("Fine del gioco");
		
		URL iconEndGame_url = getClass().getResource("finish.png");
        Image iconEndGame = new ImageIcon(iconEndGame_url).getImage();
        setIconImage(iconEndGame);
        
        panel = new JPanel();
        
        //layout
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        
        
        //pannello colorato
        Color myColor = new Color(219, 198, 135);
        coloredPanel = new JPanel();
        coloredPanel.setLayout(layout);
        coloredPanel.setBackground(myColor);
        
        
        //label diverse a seconda dello scenario
        txt = new JLabel();
        
        boolean full=isFull();
        boolean right=isRight();
        if(full) {																			//se il sudoku è stato risolto
        	
        	if(right) {																		//controlla se la soluzione è giusta
        		coloredPanel.setPreferredSize(new Dimension(350, 100));
        		txt.setText("Complimenti! Hai risolto correttamente il Sudoku.");
        		URL iconWin_url = getClass().getResource("win.png");
        		txt.setIcon(new ImageIcon(iconWin_url));
                
                //aggiungo al pannello
                coloredPanel.add(txt);
                
                //salvataggio nome utente per punteggio
        		JTextField name = new JTextField("Inserisci il tuo nome", 25);;
                save = new JButton("Salva");
                save.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		nome = name.getText();
                		player = new Player(nome, SudokuFrame.min, SudokuFrame.sec);
                		creaClassifica();													//creo la classifica
                        aggiungiInOrdineClassifica(player);									//inserisco in ordine di punteggio il giocatore
                        scriviFileClassifica();												//salvo le modifiche sul file
                        
                		dispose();															//chiudo la finestra del salvataggio
                	}
                });
                
                
                //aggiungo al pannello
                coloredPanel.add(name);
                coloredPanel.add(save);
        	}
        	
        	
        	
        	else {																			//o sbagliata
        		coloredPanel.setPreferredSize(new Dimension(250, 50));
        		txt.setText("Mi dispiace, hai perso.");
        		URL iconGameOver_url = getClass().getResource("gameOver.png");
        		txt.setIcon(new ImageIcon(iconGameOver_url));
        		//aggiungo al pannello
        		coloredPanel.add(txt);
        	}
        }
        
        else {																				//se il sudoku non è stato risolto
    		coloredPanel.setPreferredSize(new Dimension(350, 50));
        	txt.setText("Ops, non sei riuscito a risolvere il Sudoku");
        	URL iconIncomplete = getClass().getResource("incomplete.png");
        	txt.setIcon(new ImageIcon(iconIncomplete));
        	//aggiungo al pannello
        	coloredPanel.add(txt);
        }
        
        
        //posiziono la finestra al centro
        Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int x=(screenSize.width/2)-190;
		int y=(screenSize.height/2)-150;
		setLocation(x, y);
        
        
        //aggiungo tutto alla finestra
        panel.add(coloredPanel);
        add(panel);
        
        
        pack();
        
        //opzione di chiusura
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	//RESTITUISCE VERO SE LA SOLUZIONE è GIUSTA, FALSO ALTRIMENTI
	private static boolean isRight() {
		boolean right=true;
		
		for(int i=0; i<GRID_SIZE; i++) {
			for(int j=0; j<GRID_SIZE; j++) {
				String text = numbers[i][j].getText();
				int num = ButtonGridPanel.conversionStringInt(text);
				if(num!=sudokuSolution[i][j]) {
					right=false;
				}
			}
		}
		
		return right;
	}
	
	
	//RESTITUISCE VERO SE LA MATRICE è PIENA, FALSO ALTRIMENTI
	private static boolean isFull() {
		boolean full=true;
		
		for(int i=0; i<GRID_SIZE; i++) {
        	for(int j=0; j<GRID_SIZE; j++) {
        		String text = numbers[i][j].getText();
        		int num = ButtonGridPanel.conversionStringInt(text);
        		if(num==0) {
        			full=false;
        		}
        	}
        }
		
		return full;
	}
	
	
	//MEMORIZZA I DATI DEL GIOCATORE NELLA CLASSIFICA
	private void scriviFileClassifica() {
		PrintWriter pw = null;
		try {																			//apro il file in scrittura
			pw = new PrintWriter(new FileOutputStream("Scores.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Errore nell'apertura del file");
		}
		
		int i=0;
		while (i<players.size() && i<10) {												//inserisco fino a 10 migliori giocatori all'interno del file
			Player p = players.get(i);
			pw.println(p.getName() + " " + p.getMin() + " " + p.getSec());
			i++;
		}
		pw.close();																		//chiudo il file
	}
	
	
	
	//CARICA DAL FILE TUTTI I GIOCATORI NELL'ARRAYLIST
	private void creaClassifica() {
		Scanner sc = null;
		try {																			//apro il file in lettura
			sc = new Scanner(new File("Scores.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Errore nell'apertura del file");
		}
		
		players = new ArrayList<Player>();												//inizializzo l'ArrayList di giocatori
		
		while(sc.hasNext()) {
			String userName = sc.next();
			int userMin = sc.nextInt();
			int userSec = sc.nextInt();
			players.add(new Player(userName,userMin, userSec));							//e aggiungo un nuovo giocatore con i dati presi dal file all'interno dell'ArrayList
		}
		sc.close();																		//chiudo il file
	}
	
	
	
	//AGGIUNGE IN ORDINE GLI ELEMENTI ALL'INTERNO DELL'ARRAYLIST
	private void aggiungiInOrdineClassifica(Player p) {
		
		for(int i=0; i<players.size(); i++) {
			Player pl = players.get(i);
			
			if(p.getMin() < pl.getMin()) {
				players.add(i, p);
				break;
			}
			
			else if(p.getMin() == pl.getMin()) {
				
				if(p.getSec() < pl.getSec()) {
					players.add(i, p);
					break;
				}
			}
			
			else {
				if(i==(players.size())-1) {
					players.add(p);
					break;
				}
			}
				
		}
		
		
		if(players.size()==0) {
			players.add(p);
		}
	}
	
	
}
