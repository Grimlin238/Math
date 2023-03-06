/**
The MathStore class stores static methods for displaying different problems to the user
@author Tyion Lashley
@version 12/12/22
*/
import java.util.Random;
import java.util.ArrayList;

public class MathStore
	
{
	private static ArrayList<String> list = new ArrayList<>();
	
	/**
		The addToList method adds a given problem,
		if it's correct, to the ArrayList
		@param str the paramater (type string)
		*/
		
		public static void addToList(String str)
			
	{
		
		list.add(str);
		
	}
	
	/**
		The printContents method prints the contents of the list
		We need this for printing the answers to a file
		@return the contents of the list
		*/
		
		public static String printContents()
			
	{
	String contents = "";
		
		for (int i = 0; i < list.size(); i++)
			
		{
			
			contents += list.get(i) + "\n";
			
		}
		
		return contents;
		
	}
	
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