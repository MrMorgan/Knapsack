import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
/**
 * 
 * @author Jimmy Morgan
 *
 */
public class Knapsack{
	private ArrayList<Item> avaliable;
	private PriorityQueue<node> tree;
	private float maxProfit, load;
	int numNodes;
	private node bestNode;
	
/**
 * Knapsack takes a list of items and maximum weight carried. Sets all initial values and sets head to first node with its children. 
 * @param pool
 * @param encumber
 */
	Knapsack(ArrayList<Item> pool, float encumber){
		this.avaliable = pool;
		this.maxProfit = 0;
		this.load = encumber;
		tree = new PriorityQueue<node>(11, new nodeSorter());
		node head = new node(1,0,0,0,findBound( new ArrayList<Integer>() ), new ArrayList<Item>(), new ArrayList<Integer>());
		numNodes = 1;
		bestNode = head;
		if( load != 0)
			head.setExplore(true);
		else
			head.setExplore(false);
		tree.add(head);
		nodeChildren(head);
	}
	
/**
 * Finds the children of current node
 * Calls NextNode() to find what the next node for children to deal with will be. 
 * Left Child excludes next item
 * Right Child includes next item
 * @param current
 */
	private void nodeChildren(node current)
	{
		int nextlev = current.getLevel()+1;
		ArrayList<Item> base = current.getItems();
		ArrayList<Integer> excluded = current.getEx();
		float weight = current.getWeight();
		float profit = current.getProfit();
		int after = nextNode(current);
		if(after == -1)
			return;
		
		Item next = avaliable.get(after);
		excluded.add(after);
		numNodes++;
		node left = new node(numNodes, nextlev, profit, weight, findBound(excluded), base, excluded);
		
		weight += next.getWeight();
		profit += next.getValue();
		excluded.remove(excluded.size()-1);
		base.add(next);
		numNodes++;
		node right = new node(numNodes, nextlev, profit, weight, current.getBound(), base, excluded);
		current.setLeft(left);
		current.setRight(right);
		base.remove(next);
		tree.add(left);
		tree.add(right);
	}
	
/**
 * Prints required output per guidelines (A bit messy using manual whitespace rather than tabs)
 * Always begins exploring top Node of priority queue, if that node has no children, it calls find children method.
 * If bound is too small, prunes before making children, removes from tree and loops again. 
 * @return
 */
	public node Explore()
	{
		
		while(tree.size() > 0) {
		System.out.print("Exploring ");
		node current = tree.peek();
		current.printNode();
		if(current.getBound() < maxProfit)
		{
			System.out.printf("      pruned, don't explore children because bound %.1f is smaller thank known achevable profit %.0f %n",current.getBound(), maxProfit);
			System.out.println();
			tree.remove(current);
			current.setExplore(false);
			continue;
		}
		if(current.getLeft() == null)
		{
			nodeChildren(current);
		}
			
		node left = current.getLeft();
		node right = current.getRight();
		if(left != null) {
		System.out.print("      Left child is ");
		left.printNode();
		keep(left);
		}
		else
			System.out.println("No Left Child");
		
		if(right != null) {
		System.out.print("      Right child is ");
		right.printNode();
		keep(right);
		}
		else
			System.out.println("No Right Child");

		System.out.println();
		tree.remove();
		}
		return bestNode;
	}
	
/**
 * Finds the next node of the current node by excluding items in used and excluded items
 * Brute force checks both lists. 
 * Returns Item location in list of avaliable items
 * @param current
 * @return
 */
	private int nextNode(node current)
	{
		ArrayList<Item> base = current.getItems();
		ArrayList<Integer> excluded = current.getEx();
		int next;
		if(base.size() == 0)
			next = 0;
		else
			next = base.get(base.size()-1).getnumber();
		while(excluded.contains(next)) {
			next++;
		}
		if(next >= avaliable.size())
			next = -1;
		
		return next;
	}

/**
 * Finds Bound of current node given excluded items
 * Adds item value up until max weight is exceeded
 * Then adds fraction of final item to reach max weight. 
 * @param exclude
 * @return
 */
	
	private float findBound(ArrayList<Integer> exclude)
	{
		float maxVal = 0; 
		float heavy = 0; 
		for(int i = 0; i < avaliable.size(); i++)
		{
			if(!exclude.contains(i))
			{
				Item current = avaliable.get(i);
				float temp = heavy + current.getWeight();
				if(temp > load)
				{
					float diff = (load - heavy);
					diff = diff / current.getWeight();
					maxVal += current.getValue() * diff;
					break;
				}
				else
				{
					maxVal += current.getValue();
					heavy = temp;
				}
					
			}			
		}		
		return maxVal;
	}
	

/**
 * Checks child weights 
 * removes from queue if capacity is reached or too heavy. 
 * Makes note if child has higher max profit. 
 * @param check
 */
	private void keep(node check)
	{
		System.out.print("         ");
		if(check.getWeight() > load)
		{
			System.out.println("pruned because too heavy");
			check.setExplore(false);
			tree.remove(check);
		}
		else if(check.getWeight() == load)
		{
			System.out.println("hit capacity exactly so don't explore further");
			if(check.getProfit() > maxProfit)
			{
				bestNode = check;
				maxProfit = check.getProfit();
				System.out.printf("         note acheivable profit of %.0f%n",maxProfit);
			}
			tree.remove(check);
		}
		else
		{
			System.out.println("explore further");
			if(check.getProfit() > maxProfit)
			{
				bestNode = check;
				maxProfit = check.getProfit();
				System.out.printf("         note acheivable profit of %.0f%n",maxProfit);
			}
		}
	}



	
}
