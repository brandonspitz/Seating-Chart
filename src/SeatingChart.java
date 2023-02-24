import java.util.Scanner;
import java.io.*;

public class SeatingChart
{ 	public static void main(String[] args)throws IOException
	{
		Auditorium t = new Auditorium();
		char command;
		Scanner keyboard = new Scanner(System.in);
		// print the menu
		printMenu();
		do
		{
			// ask a user to choose a command
			System.out.println("\nPlease enter a command or type ?");
			command = keyboard.next().toLowerCase().charAt(0);
			switch (command)
			{
				case 'a': // display remaining seats
					System.out.println();
					t.displayChart();
					break;
				case 'b': // Print total from sales so far
					System.out.println("\nTotal: " + t.getTotal());
					break;
				case 'c': // sell ticket
					if (t.soldOut())
						System.out.println("\nSorry we are sold out.");
					else
					{
						System.out.print("\nEnter the row you want: ");
						int row = keyboard.nextInt();
						System.out.print("Enter the column you want: ");
						int col = keyboard.nextInt();
						if (t.sellTicket(row, col))
							System.out.println("\nEnjoy the show!");
						else
							System.out.println("\nThe seat is sold and/or is invalid seat!");
					}
					break;
				case 'd': // print number of seats sold
					System.out.println("\nNumber of seats sold: " + t.numSold());
					break;
				case '?':  // display menu
					printMenu();
					break;
				case 'q': // quit
					break;
				default:
					System.out.println("Invalid input!");
			}
		} while (command != 'q');
	}  //end of the main method
	// this method prints out the menu to a user
	public static void printMenu()
	{
		System.out.print("\nCommand Options\n"
				+ "-----------------------------------\n"
				+ "a: print seating chart\n"
				+ "b: display total sales\n"
				+ "c: sell ticket\n"
				+ "d: display number of seats sold\n"
				+ "?: display the menu again\n"
				+ "q: quit this program\n\n");
	} // end of the printMenu method
}