import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Node contains Item information, information about children as well as achieved and and upper bound of current list. 
 * @author Jimmy Morgan
 *
 */
public class node{
	int number, level;
	float bound, profit, weight;
	node left, right;
	ArrayList<Item> items;
	ArrayList<Integer> exclude;
	boolean explore; 
// Not sure if explore is ever used, but don't want to remove it. 	
	public boolean isExplore() {
		return explore;
	}
	public void setExplore(boolean explore) {
		this.explore = explore;
	}
	public void setLeft(node target)
	{
		this.left = target;
	}
	public node getLeft()
	{
		return this.left;
	}
	public void setRight(node target)
	{
		this.right = target;
	}
	public node getRight()
	{
		return this.right;
	}
	public float getBound()
	{
		return this.bound;
	}
	public int getNumber()
	{
		return this.number;
	}
	public int getLevel()
	{
		return this.level;
	}
	public float getWeight()
	{
		return this.weight;
	}
	public float getProfit()
	{
		return this.profit;
	}
	public ArrayList<Item> getItems()
	{
		return this.items;
	}
	public ArrayList<Integer> getEx()
	{
		return this.exclude;
	}
	public void addItem(Item next)
	{
		items.add(next);
	}
	public void addEx(int next)
	{
		exclude.add(next);
	}

/**
 * Node number, Node Level on tree, current profit, current weight, bounded profit and list of used and excluded items. 
 * left and right child default to null, found in Knapsack.java if needed. 
 * Creates new ArrayList of both used and excluded items to prevent pointer stupidity. 
 * @param num
 * @param lev
 * @param prof
 * @param weigh
 * @param max
 * @param list
 * @param not
 */
	node(int num, int lev, float prof, float weigh, float max, ArrayList<Item> list, ArrayList<Integer> not)
	{
		this.number = num;
		this.level = lev;
		this.profit = prof;
		this.weight = weigh;
		this.bound = max; 
		this.items = new ArrayList<Item>();
		for(int i = 0; i < list.size(); i++)
		{
			items.add(list.get(i));
		}
		this.exclude = new ArrayList<Integer>();
		for(int j = 0; j < not.size(); j++)
		{
			exclude.add(not.get(j));
		}
		this.left = null;
		this.right = null;
	}

/**
 * Prints node in specified format.
 */
	public void printNode()
	{
		System.out.print("<Node "+number+":   ");
		System.out.print("items: "+Arrays.toString(items.toArray())+" ");
		System.out.print("level: "+level+" ");
		System.out.printf("profit: %.0f ",profit);
		System.out.printf("weight: %.0f ",weight);
		System.out.printf("bound: %.1f>%n",bound);
	}
	

}
