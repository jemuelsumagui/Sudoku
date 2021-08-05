import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonGridPanel extends JPanel{										//Griglia del Sudoku creata attraverso un JPanel contenente 81 JButton
	
	private static final int GRID_SIZE=9;
	private static final int SUB_GRID_SIZE=3;
	public static JButton[][] numbers;
	private static JPanel innerGrids[];
	private static JPanel selectedInnerGrid;
	private static SudokuOutputGrid sudokuGrid = new SudokuOutputGrid();
	public int[][] outputGrid = sudokuGrid.getOutputGrid();
	//private int[][] sudokuSolution = sudokuGrid.getSolutionGrid();
	

	
	//COSTRUTTORE
	public ButtonGridPanel() {
		
		//impostazioni del pannello
		this.setPreferredSize(new Dimension (400, 400));
		setLayout(new GridLayout(SUB_GRID_SIZE, SUB_GRID_SIZE, 2, 2));
		setBackground(Color.BLACK);
		
		
		//font
		Font editable = new Font("Arial", Font.PLAIN, 15);							//font utilizzato per i numeri modificabili dall'utente
		Font notEditable = new Font("Arial", Font.BOLD, 15);						//font utilizzato per i numeri fissi iniziali
		
		
		//creazione griglia di bottoni con inserimento dati
		//sottogriglie della matrice 
		
		numbers = new JButton[GRID_SIZE][GRID_SIZE];
		innerGrids = new JPanel[GRID_SIZE];
		for(int i=0; i<GRID_SIZE; i++) {
			innerGrids[i] = new JPanel();
			innerGrids[i].setPreferredSize(new Dimension(396/3, 396/3));
			innerGrids[i].setLayout(new GridLayout(SUB_GRID_SIZE, SUB_GRID_SIZE));
			this.add(innerGrids[i]);
		}
		
		for(int i=0; i<GRID_SIZE; i++) {
			for(int j=0; j<GRID_SIZE; j++) {
				if(outputGrid[i][j]==0) {
					numbers[i][j] = new JButton(" ");
				}
				else {
					numbers[i][j]=new JButton(Integer.toString(outputGrid[i][j]));
				}
				
				selectedInnerGrid = innerGrids[ ((i/3)*3) + (j/3)];
				numbers[i][j].setBackground(Color.WHITE);
				numbers[i][j].setFont(notEditable);
				
				selectedInnerGrid.add(numbers[i][j]);
				
				if(outputGrid[i][j]==0) {											//per quanto riguarda i numeri modificabili dall'utente
					numbers[i][j].setFont(editable); 
					
					IncrementButton incrementListener = new IncrementButton(numbers[i][j]);				//incrementa il numero ad ogni click
					numbers[i][j].addActionListener(incrementListener);
					
					ColorActionListener colorListener = new ColorActionListener(i, j, numbers[i][j]);	//verde o rosso a seconda che sia giusto o sbagliato
					numbers[i][j].addActionListener(colorListener);
				}
				
			}
		}
		
	}
	
	
	
	//CONVERTE UNA STRINGA IN INT
	public static int conversionStringInt(String s) {
		if(s.equals(" ")) {
			return 0;
		}
		else {
			Integer n = Integer.parseInt(s);
			return n.intValue();
		}
	}
	
	
	
	
	//INCREMENTA DI UNO IL NUMERO CHE APPARE SUL BOTTONE. UNA VOLTA ARRIVATO A 9, RIPARTE DA 1
	private class IncrementButton implements ActionListener{
		
		private JButton button;
		
		public IncrementButton(JButton b) {
			button=b;
		}
		
		public void actionPerformed(ActionEvent e) {
			String text=e.getActionCommand();
			int num=conversionStringInt(text);
			num++;
			if(num==10) {
				num=1;
			}
			button.setText(Integer.toString(num));
		}
	}
	
	
	
	//IL BOTTONE DIVENTA ROSSO O VERDE A SECONDA CHE IL NUMERO INSERITO SIA GIUSTO O SBAGLIATO
	private class ColorActionListener implements ActionListener{
		private int i, j;
		private JButton button;
		
		public ColorActionListener(int i, int j, JButton b) {
			this.i=i;
			this.j=j;
			button=b;
		}
		
		public void actionPerformed(ActionEvent e) {
			String text=e.getActionCommand();
			int num=conversionStringInt(text);
				
			if(validCol(j, num) && validRow(i, num) && validSubGrid(i, j, num)){
				button.setBackground(Color.GREEN);
			}
			else {
				button.setBackground(Color.RED);
			}
			
			/*	
			if(num==sudokuSolution[i][j]-1){
				button.setBackground(Color.GREEN);
			}
			else {
				button.setBackground(Color.RED);
			}
			*/
		}
	}
	
	
	private static boolean validCol(int col, int val) {
		boolean valid = true;
		
		/*if(val==1) {
			val=2;
		}*/
		
		for(int i=0; i<GRID_SIZE; i++) {
			if((val+1)==(conversionStringInt(numbers[i][col].getText()))) {
				valid = false;
			}
		}
		
		return valid;
	}
	
	
	private static boolean validRow(int row, int val) {
		boolean valid = true;
		/*if (val==1) {
			val=2;
		}*/
		for(int j=0; j<GRID_SIZE; j++) {
			if((val+1)==(conversionStringInt(numbers[row][j].getText()))) {
				valid = false;
			}
		}
		
		return valid;
	}
	
	
	private static boolean validSubGrid(int row, int col, int val) {
		boolean valid = true;
		int h=0, k=0;
		
		/*if(val==1) {
			val=2;
		}*/
		
		switch(row) {															//le sottomatrici hanno righe 3h-3, 3h-2, 3h-1, con h=1,2,3
		case 0:
		case 1:
		case 2:
			h=1;															  	//h=1 per le prime 3 righe 
			break;
			
		case 3:
		case 4:
		case 5:
			h=2;																//h=2 per le 3 righe centrali
			break;
			
		case 6:
		case 7:
		case 8:
			h=3;																//h=3 per le ultime 3 rihe
			break;
			
		default:
			System.out.println("Error: impossible number of row.");	
		}
		
		
		switch(col) {															//le sottomatrici hanno colonne 3k-3, 3k-2, 3k-1, con k=1,2,3
		case 0:
		case 1:
		case 2:
			k=1;															  	//k=1 per le prime 3 colonne 
			break;
			
		case 3:
		case 4:
		case 5:
			k=2;																//k=2 per le 3 colonne centrali
			break;
			
		case 6:
		case 7:
		case 8:
			k=3;																//k=3 per le ultime 3 colonne
			break;
			
		default:
			System.out.println("Error: impossible number of col.");	
	}
		
		for(int i=(3*h)-3; i<3*h; i++) {										
			for(int j=(3*k)-3; j<3*k; j++) {									//scorro tutti gli elementi di una singola sottomatrice
				if((val+1)==(conversionStringInt(numbers[i][j].getText()))) {
					valid = false;
				}
			}
		}
		
		return valid;
	}
	
}
