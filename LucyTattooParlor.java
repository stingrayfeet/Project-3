import java.lang.reflect.Array;
import java.util.Scanner;
public class LucyTattooParlor {

	public static int computeMinutesOfWork (TattooCustomer[] a) {
		int counter = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != null) {
				counter += a[i].getMintues();
			}
		}
		return counter;
	}

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

	public static boolean addCustomer (TattooCustomer[][] a, TattooCustomer c) {
		//instantiation of variables for addCustomer
		int lowestMin = computeMinutesOfWork(a[0]);
		int lowestArtist = 0;
		int tempMinutes = 0;
		//Compute minutes of work for each artist and assigns lowestArtist as artist with the least amount of work minutes
		for (int i = 0; i < a.length; i++) {
			tempMinutes += computeMinutesOfWork(a[i]);
			if (tempMinutes < lowestMin) {
				lowestMin = tempMinutes;
				if (a[lowestArtist][a[lowestArtist].length - 1] == null) {
				lowestArtist = i;
				System.out.println("DEBUG: " + (i + 1) + " run of for loop-- lowestArtist = " + lowestArtist);
				}
			}
			tempMinutes = 0;
		}

		//checks if TattooCustomer a at index lowestArtist can take more clients, returns false if artist can NOT
		if (computeMinutesOfWork(a[lowestArtist]) > 480) {
			return false;
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
		numArtists = input.nextInt();
		System.out.println("How many slots will the artists take?");
		artistSlots = input.nextInt();

		//creates artists and slots with 2d array of TattooCustomer objects 
		TattooCustomer[][] parlor = new TattooCustomer[numArtists][artistSlots];

		do {
			input.nextLine();
			//Begin while loop to use as menu
			String name;
			String tattoo;
			int minutes;
			String artistRequest;

			System.out.println("Customer name: \n");
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

			if (artistRequest.equals("r")) {
				boolean success = addCustomer(parlor, new TattooCustomer(name, tattoo, minutes));
				if (!success) {
					System.out.println("-----Could not add customer, all slots are reserved for all artists-----");
				}
			}

			else {
				boolean success = addCustomer(parlor, new TattooCustomer(name, tattoo, minutes), Integer.parseInt(artistRequest));
				if (!success) {
					System.out.println("-----Could not add customer, all slots for this artist are reserved-----");
				}
			}


			//DEBUG RUNS EMPTY PARLOR 2D ARRAY
			for (int i = 0; i < parlor.length; i++) {
				for (int j = 0; j < parlor[i].length; j++) {
					System.out.print(parlor[i][j] + " ");
				}
				System.out.println();
			}


		} while ($continue);


		for (int i = 0; i < parlor.length; i++) {
			System.out.println("---Artist " + (i) + "---");
			for (int j = 0; j < parlor[i].length; j++) {
				if (parlor[i][j] != null) {
					System.out.print("-Time Slot " + (j + 1) + "-\nName: " + parlor[i][j].getName() + "\nTattoo: " + parlor[i][j].getTattoo() + "\nEstimated time: " + parlor[i][j].getMintues() + "\n\n");
				}
				
				else {
					System.out.println("-Time Slot " + (j + 1) + "-\nN/A");
				}
			}
			System.out.println();
		}

	}


}
