import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A program that solves 0-1 Knapsack problem using Branch and Bound algorithm
 * Uses input file of format:
 * 
 * Max Weight
 * Number of Items
 * Value Weight
 * Value Weight
 * ...
 * 
 * Items must be in order of descending value. 
 * @author Jimmy Morgan
 *
 */
public class Main {
/**
 * Takes File Input by either command line argument or prompt
 * Calls Knapsack to build do tree work, Knapsack Explore returns the best node. 
 * @param args
 */
	
	public static void main(String[] args) {
		
		File input; 
		Scanner in = null; 
		float max, numItems;
		
		ArrayList<Item> avaliable = new ArrayList<Item>();
		
		if(args.length != 0)
		{
			input = new File(args[0]);
		}
		else
		{
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Input File Name: ");
			input = new File(keyboard.nextLine());
			keyboard.close();
			System.out.println();
		}
		
		try {
			in = new Scanner(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		max = in.nextFloat();
		numItems = in.nextFloat();
		System.out.println("Capacity of knapsack is "+max);
		System.out.println("Items Are:");

		for(int i=0; i<numItems ; i++)
		{
			float val = in.nextFloat();
			float weight = in.nextFloat();
			System.out.println(i+1+": "+val+" "+weight);
			Item current = new Item(val, weight, i+1);
			avaliable.add(current);
		}
		System.out.println();
		
		Knapsack game = new Knapsack(avaliable, max);
		node best = game.Explore();
		System.out.print("Best node: ");
		best.printNode();
	}

}
