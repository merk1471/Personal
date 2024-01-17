/**
 * The UniqueDiagonalSudoku class is a child class of the sudoku class. Its purpose is to verify if the sudoku puzzle is valid according
 * to the rule that both diagonals (top left to bottom left, top right to bottom right) contain each number from 1 through 9 without any duplicates. 
 * It has a constructor that uses the super method to construct a sudoku object using the 2d array that it receives using the constructor belonging to
 * class Sudoku. It has one public method isValidSolution which uses a private helper method to check both diagonals and determine if the sudoku puzzle is 
 * valid diagonally.
 * @author Mark Samwaiel
 */

public class UniqueDiagonalSudoku extends Sudoku {

    public UniqueDiagonalSudoku(int[][] numbers) { //@param 2d array numbers
        super(numbers); //call the parent class's constructor by passing the 2d array
    }

    public boolean isValidSolution() {

       boolean validSolution = super.isValidSolution(); // variable containing true or false depending on if the sudoku puzzle has a valid solution, uses the parent class's isValidSolution method
       boolean validDiagonal = checkDiagonal(); // variable containing true or false depending on if the sudoku puzzle is valid diagonally
       
       return (validSolution && validDiagonal); //return the result (if the sudoku puzzle has an invalid solution or both of the diagonals are invalid, the method will return false. Both of the above conditions must be met to return true. 
       }
       
       private boolean checkDiagonal() {
   
    	   int size = getSize();
           int[][] grid = getGrid();
           
    	   boolean[] getFirst = new boolean[size]; //first diagonal (top left to bottom right)
    	   boolean[] getSecond = new boolean[size]; //second diagonal (bottom left to top right) 
       	
    
    	   boolean firstDiag = true; //initiate and set the first diagonal's value to true
    	   
    	   for (int i = 0;i < size; i++) {
    		  int firstCheck = grid[i][i];
    		  if (firstCheck <1 || firstCheck > size || getFirst[firstCheck-1]) {
    			  firstDiag = false; //if the digit is invalid or has already been seen in the first diagonal, set its value to false indicating the diagonal is invalid
    		  }
    		  getFirst[firstCheck - 1] = true;
    		   
    	   }
    	   
    	  int counter = size - 1;	//initiate a counter for the second loop to enumerate values properly starting from 8
    	  boolean secondDiag = true; //initiate and set the second diagonal's value to true
    	   
    	   for (int i = 0;i < size; i++) {
    		  int secondCheck = grid[counter][i];
    		  if (secondCheck <1 || secondCheck > size || getSecond[secondCheck-1]) {
    			  secondDiag = false; //if the digit is invalid or has already been seen in the second diagonal, set it s value to false indicating the diagonal is invalid
    		  }
    		  getSecond[secondCheck - 1] = true; 
    		  counter -= 1; //decrease the value from 8 to 0 in order for the loop to go from the bottom left to top right (0-8, 1-7, 2-6... 8-0)
    	   }
    	   return firstDiag || secondDiag; //@return either one of the diagonals because if one of the diagonals is valid, the sudoku puzzle counts as a valid diagonal solution. 
       }
       
       }
   