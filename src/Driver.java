import java.util.Scanner;

/**
 * Main class to run the initial game logic.
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
		do{
			table.playRound();
			table.printStats();

			try{
				System.out.print("\n\nPlay another round? Y/n\t");
				String userIn = scan.nextLine();
				if(userIn.toLowerCase().trim().charAt(0) == 'n'){
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

		System.out.print("How many players are at the table? ");
		int numPlayers;
		while(true) {
			try {
				numPlayers = Integer.parseInt(scan.nextLine());
				if(numPlayers <= 0){
					throw new ArithmeticException("Please enter a positive number.  ");
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

		table.addPlayers(numPlayers);
	}


}
