import java.util.Comparator;
/**
 * 
 * @author Jimmy Morgan
 *
 */
public class nodeSorter implements Comparator<node> {
/**
 * Compares bound first, if bound is the same, compares level
 * Higher bound is higher priority, higher level is higher priority.
 */
	@Override
	public int compare(node first, node second) {
		// TODO Auto-generated method stub
		if(first.bound < second.bound)
			return 1;
		else if (first.bound > second.bound)
			return -1;
		else {
			//return 0;
			if(first.getLevel() > second.getLevel())
				return 1;
			else
				return -1;				
		}
	}
}
