/**
 * 
 * @author Wills Blake
 *
 */
public class TattooCustomer {
	//MEMBER VARIABLES --
	private static int numCustomers;
	private String name;
	private String tattoo;
	private int minutes;
	
	
	//CONSTRUCTOR --
	public TattooCustomer(String name, String tattoo, int minutes) {
		numCustomers++;
		this.name = name;
		this.tattoo = tattoo;
		this.minutes = minutes;
	}
	
	//BEHAVIORS --
	public String getName () {
		return name;
	}
	
	public String getTattoo () {
		return tattoo;
	}
	
	public int getMinutes () {
		return minutes;
	}
	
	public static int getNumCustomers () {
		return numCustomers;
	}
}
