/**
 * This is the sudoku class which checks if a sudoku puzzle is valid according to it being a 9x9 puzzle with rows, boxes, and columns all containing numbers 1 through 9 without duplicates. This class uses two
 * private variables, size and grid. Size stores the length of the 2d array: numbers, grid stores a shallow copy of the 2d array sudoku puzzle. This class uses nine methods to verify a sudoku puzzle has a valid 
 * solution. The method getSize returns the size variable of type int. The getGrid method returns the grid variable of type int[][]. The getDigitAt method takes in two int parameters (row and col), checks if the 
 * rows and columns are valid, and if they are, returns the digit located at the indexes given. The isValidRow method takes in 1 parameter (int row) and returns a boolean to indicate whether its a valid row or not. 
 * The isValidCol method takes in 1 parameter (int col) and returns a boolean to indicate whether the column is valid or not. The isValidBox method takes in 2 parameters (int row and int col) and returns a boolean 
 * indicating whether the 3x3 box inside of the sudoku puzzle is valid. The isValidSolution method checks if the soduku puzzle has valid rows, columns, and boxes by testing the puzzle using the previously mentioned
 * methods and returns a boolean to indicate whether it is valid or not. The equals method checks if 2 sudoku puzzles are equal and returns a boolean indicating if they are or not. Finally, the toString method returns
 * the sudoku puzzle as a string.
 * @author Mark Samwaiel
 * CS1027 Assignment #1
 */

class Sudoku{

private int size; 

private int[][] grid; 

public Sudoku (int[][] numbers) { //@param 2d array containing sudoku digits

size = numbers.length;
grid = new int[size][size];
grid = numbers; 

}

public int getSize() {

return this.size; //@return grid's size

}

public int[][] getGrid(){

return this.grid; //@ return 2d array grid

}

public int getDigitAt (int row, int col) { //@param row and column indexes 

if (row < 0 || col < 0 || row>=size || col>=size) {

return -1; //@return -1 if row or column is not in the sudoku puzzle

}

else {

return grid[row][col]; //@return the digit found on the grid at the indexes given 

}

}

public boolean isValidRow (int row) { //@ param the row index that we are checking
	
	boolean[] duplicateCheck = new boolean[size]; //create a boolean array containing numbers 1-9, this will allow us to verify if all the numbers are present and not repeated by using a boolean value to represent each number. 
	
	int size = this.getSize();
	
	if (row < 0 || row>= size ) {
		return false; //@return false if the row is out of range
	}
	

for (int i = 0;i < size; i++) {
	int number = (grid[row][i]);
	if (number < 1 || number > size || duplicateCheck[number -1] ) {
		return false; //@return false if the value in the row is invalid or has already been seen
	}
	
	duplicateCheck[number -1] = true; //change the value of the digit seen to true, indicating it has been seen

}
for (int i = 0; i < size; i++) {
	if (!duplicateCheck[i]) {
		return false; //@return false if we cannot verify all the numbers have been seen
	}
}
return true; //@return true if we do not encounter any issues meaning the row is valid 
}

public boolean isValidCol(int col) { //@param column index
	
boolean[] duplicateCheck = new boolean[size]; 
	
	int size = this.getSize();
	
	if (col < 0 || col>= size) {
		return false; //@return false if column is out of range
	}
	

for (int i = 0;i < size; i++) {
	int number = (grid[i][col]);
	if (number < 1 || number > size || duplicateCheck[number -1] ) {
		return false; //@return false if digit found at index is invalid or has already been seen
	}
	
	duplicateCheck[number -1] = true; // switch the boolean value in the array to true indicating we have seen the number

}
for (int i = 0; i < size; i++) {
	if (!duplicateCheck[i]) {
		return false; //@return false if we cannot verify all the numbers are present in the row 
	}
}
return true; //@return true if we do not encounter any issues meaning the column is valid 
}


public boolean isValidBox (int row, int col) { //@param row and column index indicating the top left of a 3x3 box 
	int size = this.getSize();
	boolean[] duplicateCheck = new boolean[size]; 
	
	
	if (col < 0 || col>= size || row < 0 || row >= size || (col + 2) >= size || (row + 2) >= size) { // verify the rows and columns are not invalid
		return false; //@return false if row or column is out of range
	}
	
	for (int i = row;i < row + 3; i++) { 
		for (int y = col; y < col + 3; y++) { //loop over all 9 values in the 3x3 box
	
	int number = (grid[i][y]);
	if (number <1 || number > size || duplicateCheck[number -1]) {
		return false; //@return false if the number is invalid
	

		}
	duplicateCheck[number -1] = true; //verify we have seen the number

	}
	}
	for (int i = 0; i < size; i++) {
		if (!duplicateCheck[i]) {
			return false; //@return false if not all digits have been seen in our boolean array
		}
	}

return true; //@return true if we do not encounter any issues meaning the box is valid
}

	


public boolean isValidSolution() {
	
	
	for (int i=0;i<size;i++) //check if all 9 rows and columns are valid
	{
		if (!isValidRow(i) || !isValidCol(i))
		{
				return false; //@return false if the rows or columns are not valid
			}
			
	}
	if(size == 9) { //check if the puzzle is 9 digits long and check every box present in the sudoku puzzle
	for (int i=0;i<size;i+=3) {
		for (int y=0;y<size;y+=3) {
		if (!isValidBox(i,y)) {
			return false; //@return false if the box is not valid
		}
	}
	}
	}
	 
		return true; //@return true if we do not encounter any issues meaning the solution is valid 
	}
//


public boolean equals (Sudoku other) { //@param 2 parameters which are ideally twp different or equal sudoku puzzles
if (other == null || !(other instanceof Sudoku)) {
	return false; //@return false if the other paramter is not of type sudoku or if it is null 
}
if (this.getSize() != other.getSize()) {
	return false; //@return false if the sizes are not equal
}
int[][] otherSudoku = other.getGrid();
for (int x = 0; x < size; x++) {
	for (int y = 0; y < size; y++) {
		if (this.grid[x][y] != otherSudoku[x][y]) {
			return false; //@return false if the digits at every index are not all equal in both
		}
	}
}
return true; //@return true if we do not encounter any issues meaning the two sudoku puzzles are equal
}

public String toString() {
	String SudokuString = "";

    for (int row = 0; row < size; row++) {
        for (int col = 0; col < size; col++) {
            SudokuString += (grid[row][col] + " "); //adds every digit to the string
        }
        SudokuString += "\n"; //starts a new row 
    }

    return SudokuString; //@return sudoku grid

}
}