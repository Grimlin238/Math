/**
The MathStore class stores static methods for displaying different problems to the user
@author Tyion Lashley
@version 02/12/23
*/
import java.util.Random;

public class MathStore
	
{

/**
	The showAddition method returns an addition problem
	@param num1 first number being returned
	@param num2 second number being returned
	@return the actual math problem
	*/
	 	
	public static String showAddition(int n1, int n2)
		
	{
		
		return n1 + " + " + n2;
	}
	
	/**
		The showSubtraction method shows a subtraction problem to the user
		@param num1 first number being returned
		@param num2 second number being returned
		@return the full problem being returned
		*/
		
	public static String showSubtraction(int n1, int n2)
		
		{
		
		return n1 + " - " + n2;
	}
	
	/**
		The showSubtraction method displays a subtraction problem
		@param num1 first number being returned
		@param num2 second number being returned
		@return the actual problem as a string
		*/
		
	public static String showMultiplication(int n1, int n2)
		
		{
		
		return n1 + " * " + n2;
	
	}
	
	/**
		The showDivision method displays a division problem
		@param num1 first number being reutrned
		@param num2 second number being returned
		@return the actual problem as a string
		*/
		
	public static String showDivision(int n1, int n2)
		
		{
		
		return n1 + " / " + n2;
		
	}

	/**
		The generateRandomNum method generates a random number up to 12
		@return number - the random number being returned 
		*/
		
public static int generateRandomNum()
	
	{
	
	Random rand = new Random();

int number = rand.nextInt(12);

return number;
}	
}