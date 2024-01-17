/*This file contains the polynomial class with six methods: The constructor, add, eval, toString, and solve. All of these methods are used to create, manage, 
 * interpret, and solve (using Newton's method) different polynomials. 
*/

public class Polynomial{
	//Initialize a new OrderedLinkedList named "Polynomial" to hold and sort monomials.
	private OrderedLinkedList<Monomial> Polynomial = new OrderedLinkedList<>();
	
	//Constructor method, creates an empty polynomial object with no monomials. 
	public Polynomial()
	{
		Polynomial emptyPolynomial = null; 
	}
	
	//Void add method, used to insert a new monomial to the OrderedLinkedList object. 
	public void add(int coefficient, int degree) //@param the monomials coefficient and degree. 
	{
		//initialize and insert a new monomial object  named "newMonomial" that holds the coefficient and degree. 
		Monomial newMonomial = new Monomial(coefficient,degree);
		Polynomial.insert(newMonomial);
		
	}
	
	/*Derivative method, returns a Polynomial object, used to obtain a new polynomial containing the derivative of 
	 * the polynomial stored as monomials in the OrderedLinkedList object. 
	*/
	public Polynomial derivative()
	{
		Polynomial newPolynomial = new Polynomial();
		
		/*for loop that takes the coefficient and degree contained in each monomial, creates a new monomial containing
		 * the updated derivative values, and adds it's derivative to the new polynomial object. 
		 */
		for (int i = 0; i < Polynomial.getSize();i++) 
		{
			Monomial monomial = Polynomial.get(i);
			int degree = monomial.getDegree();
			int coefficient = monomial.getCoefficient();
			
			if (degree > 0)
			{
				int dCoefficient = coefficient * (degree);
				int dDegree = degree - 1;
				newPolynomial.add(dCoefficient,dDegree);
			}
		}
		return  newPolynomial; //@return the new polynomial object containing the derivative. 
	}
	
	//eval method, returns a double, used to evaluate the polynomial at a given double value. 
	public double eval(double x) //@param double value which the polynomial will be evaluated at
	{
		double evaluation = 0.0;
		
		/* for loop that takes the coefficient and degree in each monomial, evaluates it with the 
		 * given value, and adds it to the double "evaluation".
		*/
		for (int i = 0; i < Polynomial.getSize();i++) 
		{
			Monomial monomial = Polynomial.get(i);
			int degree = monomial.getDegree();
			
			int coefficient = monomial.getCoefficient();
			
			
			double result = coefficient * Math.pow(x , degree); 
			evaluation += result;
		}

		return evaluation; //@return new double obtained after evaluating polynomial. 
	}
	
	//toString method, returns a string, used to return the polynomial as a string
	public String toString() 
	{
		String polyAsString = "";
		String operator = "";
		
		//for loop that gets the coefficient and degree from each monomial, adds the correct operator, and adds it to a string
		for (int i = 0; i < Polynomial.getSize();i++)
		{
			Monomial monomial = Polynomial.get(i);
			int degree = monomial.getDegree();
			int coefficient = monomial.getCoefficient();
			
			if (coefficient > 0 && i != 0)
			{
				operator = " + ";
			}
			
			else if (coefficient < 0)
			{
				operator = " - ";
			}
			
			if (degree >= 0)
			{
				polyAsString += (operator + Math.abs(coefficient) + "*x^" + degree);
			}
		}

		return (polyAsString); //@return new string describing the polynomial 
	}
	
	//solve method, returns a double, used to find the solution of the polynomial using Newton's method. 
	public double solve (double x0, double e, int T) throws SolutionNotFound //@param initial value "x0", the tolerance value "e", and max number of iterations "T".
	{
		double current = 0.0;
		double previous = x0;
		
		//values for the evaluation of previous at the polynomial and it's derivative. using derivative and eval methods. 
		double atPrevious = eval(previous);
		double atPreviousDerivative = derivative().eval(previous);
		
		
		//if the previous value at the derivative is not already 0, using Newton's formula update the current value.
		if (atPreviousDerivative != 0) 
		{
			current = previous - (atPrevious / atPreviousDerivative);
		}
		
		//throw SolutionNotFound exception as the divisor (previous at derivative) will be 0.
		else
		{
			throw new SolutionNotFound("divide by zero error");
		}
		
		int count = 0;
		
		/*while the maximum number of iterations and tolerance value is not reached, keep going through newton's formula until the solution is found. If at any point the divisor
		 * is 0, raise an exception. 
		 */
		while (count < T && Math.abs(previous - current) > e)
		{
			previous = current;
			atPrevious = (eval(previous));
			atPreviousDerivative = derivative().eval(previous);
			
			if (atPreviousDerivative != 0) 
			{
				current = previous - (atPrevious / atPreviousDerivative);
			}
			
			else
			{
				throw new SolutionNotFound("divide by zero error");
			}
			 
			++count;
		}
		
		//if the tolerance was surpassed, raise an exception 
		if (count >= T) 
		{
			throw new SolutionNotFound("maximum iteration exceeded");
		}
		//If the tolerance was not surpassed, return the solution. 
		else
		{
			return current; //@return the value of the solution
		}
	}
}

//End of class. 