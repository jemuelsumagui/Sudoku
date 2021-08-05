import java.util.Random;

public class SudokuOutputGrid {
	
	private static final int GRID_SIZE=9;
	public static int[][] outputGrid = new int[GRID_SIZE][GRID_SIZE];
	
	private static SudokuSolutionGrid solution = new SudokuSolutionGrid();
	private static int[][] solutionGrid = solution.getGrid();
	
	
	//COSTRUTTORE
	public SudokuOutputGrid() {
		
		for(int i=0; i<GRID_SIZE; i++) {
			for(int j=0; j<GRID_SIZE; j++) {
				outputGrid[i][j]=0;													//inizializza a zero tutti gli elementi della matrice
			}
		}
		
		makeSudokuOutputGrid();
	}
	
	
	
	private void makeSudokuOutputGrid() {
		Random rand = new Random();
		int cont=0, i, j;
		
		while(cont<35) {
			i=rand.nextInt(9);													//estraggo casualmente gli indici della casella da mostrare all'utente
			j=rand.nextInt(9);
			
			while(outputGrid[i][j]!=0) {
				i=rand.nextInt(9);													//estraggo casualmente gli indici della casella da mostrare all'utente
				j=rand.nextInt(9);
			}
			
			outputGrid[i][j]=solutionGrid[i][j];									//riempio la casella con quella della matrice risolutiva; le altre rimangono uguali a 0
			
			cont++;
		}
	}
	
	
	//RESTITUISCE LA MATRICE DA COMPLETARE
	public int[][] getOutputGrid(){
		return outputGrid;
	}
	
	
	//RESTITUISCE LA MATRICE CHE RISOLVE IL SUDOKU
	public int[][] getSolutionGrid(){
		return solutionGrid;
	}
}
