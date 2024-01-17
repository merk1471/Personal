/* This file contains the monomial class which implements the generic comparable class in this case with other monomials. This class contains 4 methods; the constructor, 
 * getCoefficient, getDegree, and compareTo. It is used to create, retrieve data from, and compare new monomial objects which store a coefficient and degree. 
 */

public class Monomial implements Comparable<Monomial>
{
	private int coefficient;
	private int degree;
	
	//Monomial constructor method, sets the new monomial object's coefficient and degree values. 
	public Monomial(int coefficient, int degree) //@param 2 integers, the monomial's coefficient and it's degree. 
	{
		this.coefficient = coefficient;
		this.degree = degree; 
	}
	
	//getCoefficient method, returns an int, used to get the monomial object's coefficient. 
	public int getCoefficient() 
	{
		return this.coefficient; //@return the monomial's coefficient. 
	}
	
	//getDegree method, returns an int, used to get the monomial object's degree.
	public int getDegree() 
	{
		return this.degree; //@return the monomial's degree. 
	}
	
	//compareTo method, returns an int, used to compare the degrees of 2 monomials. 
	public int compareTo(Monomial m) 
	{
		return this.degree - m.degree; //@return the difference in the degrees between the 2 monomial objects. 
	}
}

//End of class. 