import java.util.Random;

public class SudokuSolutionGrid {
	
	private static final int GRID_SIZE=9;
	private static int[][] sudokuGrid = new int[GRID_SIZE][GRID_SIZE];
	
	
	//COSTRUTTORE
	public SudokuSolutionGrid() {
		for(int i=0; i<GRID_SIZE; i++) {
			for(int j=0; j<GRID_SIZE; j++) {
				sudokuGrid[i][j]=0;													//inizializza a zero tutti gli elementi della matrice
			}
		}
		
		makeSudokuSolutionGrid();													//crea una matrice che soddisfa le condizioni del Sudoku
	}
	
	
	//CREA LA MATRICE RISOLUTIVA DEL SUDOKU
	private void makeSudokuSolutionGrid() {
		
		int[] row = new int[9];
		
		for(int i=0; i<GRID_SIZE; i++) {
			int flagC=-1, flagG=-1;													//forzo l'entrata nel ciclo while ad ogni nuova riga della matrice Sudoku
			
			while(flagC==-1 || flagG==-1) {											//finchè non trova una riga random che soddisfi entrambe le condizioni del Sudoku
				row=makeSudokuRow();												//genera una nuova riga random
				flagC=isValidCol(row);
				flagG=isValidSubGrid(i, row);
			}
			
			for(int j=0; j<GRID_SIZE; j++) {
				sudokuGrid[i][j]=row[j];											//una volta trovata la giusta riga random, la copio all'interno della riga i della matrice Sudoku
			}
		}
		
	}
	
	
	//RESTITUISCE UNA RIGA RANDOM CONTENENTE I NUMERI DA 1 A 9 DA INSERIRE NELLA MATRICE SUDOKU
	private int[] makeSudokuRow() {
		
		int[] row = new int[9];
		Random rand = new Random();
		int val=0, flag=-1;
		
		for(int i=0; i<GRID_SIZE; i++) {
			while(flag==-1) {
				val=rand.nextInt(9)+1;
				flag=newNumb(row, i, val);
			}
			row[i]=val;
			flag=-1;
		}
		
		return row;
	}
	
	
	//CONTROLLA CHE I NUMERI PRESENTI NELL'ARRAY SIANO TUTTI DISTINTI TRA LORO
	private static int newNumb(int[] row, int index, int val) {
		
		for(int i=0; i<index; i++) {
			if(row[i]==val) {
				return -1;
			}
		}
		return 0;
	}
	
	
	//CONTROLLA CHE ALL'INTERNO DELLE COLONNE DELLA MATRICE SUDOKU NON SIANO GIA' PRESENTI I NUMERI CONTENUTI NELLA RIGA RANDOM
	public static int isValidCol(int[] row) {
		
		for(int j=0; j<GRID_SIZE; j++) {											//fisso una colonna
			for(int i=0; i<GRID_SIZE; i++) {										//scorro le righe (elemnti della colonna)
				if(sudokuGrid[i][j]==row[j]) {
					return -1;
				}
			}
		}
		
		return 0;
	}
	
	
	//CONTROLLA CHE NON SI RIPETANO I NUMERI ALL'INTERNO DELLE SOTTOMATRICI
	public static int isValidSubGrid(int r, int[] row) {
		int h=0, k;
		
		switch(r) {																	//le sottomatrici hanno righe 3h-3, 3h-2, 3h-1, con h=1,2,3
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
		
		
		for(int index=0; index<GRID_SIZE; index++) {								//scorro tutti gli elementi della nuova riga random da inserire
			
			for(int i=(3*h)-3; i<3*h; i++) {										//scorro le 3 righe che mi interessano
				
				if(index<3) {
					k=1;															//k=1 per le prime 3 colonne
				}
				else if(index>=3 && index<6) {										//k=2 per le 3 colonne centrali
					k=2;
				}
				else {
					k=3;															//k=3 per le ultime 3 colonne
				}
				
				for(int j=(3*k)-3; j<3*k; j++) {									//scorro tutti gli elementi di una singola sottomatrice
					if(sudokuGrid[i][j]==row[index]) {
						return -1;
					}
				}
			}
		}
		
		return 0;
	}
	
	
	public int[][] getGrid(){														//ho reso la variabile di istanza privata, quindi uso il metodo get per aver accesso ad essa
		return sudokuGrid;
	}
	
}
