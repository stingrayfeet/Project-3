import java.util.Scanner;
public class LucyTattooParlor {
	
	
	/**
	 * Computes minutes of work time for any given technician. Returns an int with the specified tech's minutes of work.
	 * 
	 * @param a
	 * @return counter
	 */
	public static int computeMinutesOfWork (TattooCustomer[] a) {
		int minutesOfWork = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != null) {
				minutesOfWork += a[i].getMinutes();
			}
		}
		return minutesOfWork;
	}

	/** 
	 * Adds a customer with name, tattoo, and estimated tattoo time to specified artist. Returns true if customer was added successfully, false if otherwise.
	 * 
	 * @param a
	 * @param c
	 * @param artistNum
	 * @return boolean
	 */
	public static boolean addCustomer (TattooCustomer[][] a, TattooCustomer c, int artistNum) {
		//checks if artist at index artistNum can take more clients, returns false if artist can NOT
		if (computeMinutesOfWork(a[artistNum]) > 480) {
			return false;
		}
		//assigns TattooCustomer c to first null position found in a[artistNum], returns true if null position is found
		for (int i = 0; i < a[artistNum].length; i++){
			if (a[artistNum][i] == null) {
				a[artistNum][i] = c;
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds a customer with name, tattoo, and estimated tattoo time to first available artist. Returns true if customer was added successfully, false if otherwise.
	 * 
	 * @param a
	 * @param c 
	 * @return boolean 
	 */
	public static boolean addCustomer (TattooCustomer[][] a, TattooCustomer c) {
		int[] techsWithSlots = new int[a.length];
		int slotNum = 0;
		boolean hasNull = false;
		for(int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == null) {
					hasNull = true;
				}
			}
			if (hasNull == true) {
				techsWithSlots[slotNum] = i;
				slotNum++;
			}
			hasNull = false;
			
		}
		//instantiation of variables for addCustomer
		int lowestMin = computeMinutesOfWork(a[0]);
		int lowestArtist = 0;
		int tempMinutes = 0;
		//Compute minutes of work for each artist and assigns lowestArtist as artist with the least amount of work minutes
		for (int i = 0; i < a.length; i++) {
			tempMinutes = computeMinutesOfWork(a[i]);
			if (tempMinutes < lowestMin) {
				lowestMin = tempMinutes;
				for (int value: techsWithSlots) {
					if (techsWithSlots[value] == i && tempMinutes < 480) {
						lowestArtist = i;
					}
				}
			}
			tempMinutes = 0;
		}

		//assigns TattooCustomer c to first null position found in a[lowestArtist], returns true if null position is found
		for (int i = 0; i < a[lowestArtist].length; i++) {
			if (a[lowestArtist][i] == null) {
				a[lowestArtist][i] = c;
				return true;
			}
		}
		return false;
	}


	/** Main Method**/
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		boolean $continue = true;
		int numArtists;
		int artistSlots;
		System.out.println("--Lucy's Tattoo Parlor--");
		System.out.println("How many artists will be working?");
		numArtists = Integer.parseInt(args[0]);
		System.out.println("... " + numArtists + " technicians selected");
		System.out.println("How many shifts will the artists take?");
		artistSlots = Integer.parseInt(args[1]);
		System.out.println("... " + artistSlots + " shifts per technician");

		//creates artists and slots with 2d array of TattooCustomer objects 
		TattooCustomer[][] parlor = new TattooCustomer[numArtists][artistSlots];

		do {
			//Begin while loop to use as menu
			String name;
			String tattoo;
			int minutes;
			String artistRequest;

			System.out.println("Customer name:");
			name = input.nextLine();
			if (name.equals("Print Waitlist")) {
				break;
			}
			System.out.println("Choice of tattoo: ");
			tattoo = input.nextLine();
			System.out.println("Who would you like as a tattoo artist? (enter r for artist with shortest wait)");
			artistRequest = input.next();
			System.out.println("Minutes required: ");
			minutes = input.nextInt();
			input.nextLine();

			if (artistRequest.equals("r")) {
				boolean success = addCustomer(parlor, new TattooCustomer(name, tattoo, minutes));
				if (!success) {
					System.out.println("-----Could not add customer, all slots are reserved for all artists-----");
				}
				else {
					System.out.println("---Customer added successfully---");
				}
			}

			else {
				boolean success = addCustomer(parlor, new TattooCustomer(name, tattoo, minutes), Integer.parseInt(artistRequest));
				if (!success) {
					System.out.println("-----Could not add customer, all slots for this artist are reserved-----");
				}
				else {
					System.out.println("---Customer added successfully---");
				}
			}


		} while ($continue);

		input.close();

		for (int i = 0; i < parlor.length; i++) {
			System.out.println("---Artist " + (i) + "---");
			for (int j = 0; j < parlor[i].length; j++) {
				if (parlor[i][j] != null) {
					System.out.print("-Time Slot " + (j + 1) + "-\nName: " + parlor[i][j].getName() + "\nTattoo: " + parlor[i][j].getTattoo() + "\nEstimated time: " + parlor[i][j].getMinutes() + "\n\n");
				}

				else {
					System.out.println("-Time Slot " + (j + 1) + "-\nN/A");
				}
			}
			System.out.println();
		}

	}


}
