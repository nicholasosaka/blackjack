import java.util.Scanner;

/**
 * Main class to run the initial game logic.
 * 5 Major Functions:
 *
 * Playing multiple rounds
 * Betting/Money transfer to/from players
 * Hitting/Standing
 * "AI" Gameplay
 * ??????? -- omitted with permission
 *
 */
public class Driver {
	private static Scanner scan = new Scanner(System.in);
	private static Table table = new Table();

	public static void main(String[] args){

		initialize();
		playGame();

	}

	/**
	 * Logic to play game
	 */
	private static void playGame() {
		boolean continuePlay = true;
		do{ //as long as an exit input is not received from the user,

			table.playRound();  //play a round

			System.out.print("\n\n");

			table.printStats(); //print the stats

			try{
				System.out.print("\n\nPlay another round? Y/n\t");  //grab user input
				String userIn = scan.nextLine();
				if(userIn.toLowerCase().trim().charAt(0) == 'n'){   //if it's no, then exit loop and print exit statement
					continuePlay = false;
				}
			}catch(Exception e){
				System.out.println("Unknown response, quitting game.");
				continuePlay = false;
			}

		}while(continuePlay);

		System.out.println("\nThank you for playing!");
	}


	/**
	 * Logic to initialize gamestates and gather information
	 */
	public static void initialize(){
		System.out.println("Welcome to Blackjack");

		System.out.print("How many players are at the table? (Max 10) ");
		int numPlayers;
		while(true) {
			try {
				numPlayers = Integer.parseInt(scan.nextLine()); //grab user input for number of players
				if(numPlayers <= 0){
					throw new ArithmeticException("Please enter a positive number.  ");
				}

				if(numPlayers > 10){
					throw new ArithmeticException("Please enter a number of 10 or less. ");
				}
				break;
			}catch(ArithmeticException ae){
				System.out.print(ae.getMessage());

			}catch(NumberFormatException nfe){
				System.out.print("Please enter a valid number.  ");

			}catch(Exception e){
				System.out.print("Please enter a number.  ");
			}
		}

		table.addPlayers(numPlayers);   //add players to table
	}


}
