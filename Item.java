/**
 * 
 * @author Jimmy Morgan
 *
 */
public class Item {
	private float value;
	private float weight;
	private int number;
	
	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getnumber()
	{
		return number;
	}
	
	public void setNumber(int number)
	{
		this.number = number;
	}

	/**
	 * Holds information about a single possible item
	 * number is the number in the list. 
	 * @param val
	 * @param weigh
	 * @param number
	 */
	Item(float val, float weigh, int number){
		this.value = val;
		this.weight = weigh;
		this.number = number;
	}

/**
 * Not a real toString, but makes printing list of Items easier. 
 */
	@Override
	public String toString()
	{
		return Integer.toString(number);
	}

}
