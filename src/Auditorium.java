import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Currency; //importing packages to help with currency formatting
import java.util.Locale;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Auditorium {

	Locale usa = new Locale("en", "US"); //formatting currency to usa standards
	Currency dollars = Currency.getInstance(usa);
	NumberFormat fmt = NumberFormat.getCurrencyInstance(usa);
	
	private double[][] seats; //variables used
	private double totalSales;
	private int totalSeats;
	
	private double[][] readFile() throws IOException { //read file helper method
		File file = new File ("seatPrices.txt");
		if (!file.exists()) {
			System.out.println("NO EXISTO"); //debug
		}
		Scanner scan = new Scanner(new BufferedReader(new FileReader(file))); //scans txt file
		double[][] seats = new double[3][4]; //instantiates 2d array
		int i = 0;
		while (scan.hasNextLine() && i < 3) { //while loop facilitates transfer
			String s = scan.nextLine();
			if (s == null || s.isEmpty() || s == "\n") { //makes sure 2d array doesn't take any blank spaces
				break;
			}
				for (int j = 0; j < 4; j++) {
					try {
						seats[i][j] = Double.parseDouble(s); //add numbers to array
					} catch (NumberFormatException ignored) { //try/catch facilitates errors
						System.out.println("exception");
					}
				}
			this.totalSeats++;
			if (totalSeats%3 == 0) { //sets seat number with file info
				i++;
			}
		}
		return seats;
	}
	
	public Auditorium() throws IOException {
		this.seats = readFile(); //instantiates 2d array with file
	}
	
 	public String getTotal() {
		return fmt.format(totalSales); //formats total that was sold
	}
	
	public void displayChart() {
		for (int i = 0; i < seats.length; i++) { //nested for loops run through array
			for (int j = 0; j < seats[i].length; j++) {
				System.out.printf("%-7s", seats[i][j]); //prints with formatting
			}
		System.out.println();
		}
	}
	
	public boolean sellTicket(int i, int j) {
		if (i >= 3 || j >= 4) { //makes sure input isn't out of bounds
			return false;
		}
		if (seats[i][j] == 0.0) { //makes sure seat isn't already sold
			return false;
		} 
			totalSales += seats[i][j]; //sells seat and adds to total sales variable
			seats[i][j] = 0.0;
			return true;
	}
	
	public int numSold() {
		int count = 0;
		for (int i = 0; i < seats.length; i++) { //loops move through array
			for (int j = 0; j < seats[i].length; j++) {
				if (seats[i][j] == 0.0) //every 0.0 the count increments
					count++;
			}
		}
		return count;
	}
	
	public boolean soldOut() {
		int available = 0;
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) { //loops move through array
				if (seats[i][j] != 0.0) //looks to see if any seats are not 0.0
					available++;
			}
		}
		if (available > 0) { //if there's a seat that's not 0.0 then it is not sold out
			return false;
		} else {
			return true;
		}
	}
}
