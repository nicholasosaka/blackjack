import java.util.Scanner;

/**
 * Human player subclass. Runs game logic for human player turns.
 */
public class Human extends Player{

	private Scanner scan = new Scanner(System.in);


	/**
	 * Constructor for name and money
	 * @param name player's name
	 * @param money starting wallet
	 */
	Human(String name, int money){
		super(name, money);
	}

	/**
	 * Human player turn logic.
	 * @return boolean declaring whether a winstate has been reached.
	 */
	@Override
	public boolean playTurn(){
		//TODO implement turn logic
		turnMenu();

		return false;
	}

	/**
	 * Method to display turn menu
	 * @return integer 1 - 4 (inclusive) corresponding to menu choice.
	 */
	public int turnMenu(){
		int choice;

		while(true) {
			try {
				System.out.println(this.getName() + ", please select a choice.");
				System.out.print("1. Hit\n" +
						"2. Stand\n" +
						"3. Double\n" +
						"4. Surrender\n" +
						"> ");

				choice = Integer.parseInt(scan.nextLine()); //grab user input

				if( choice < 1 || choice > 4){
					throw new ArithmeticException("Please enter a valid option.");
				}

				return choice;

			} catch (ArithmeticException ae) {
				System.out.print(ae.getMessage() +
						"\n>");

			} catch (NumberFormatException nfe){
				System.out.println("Please enter an integer.");

			} catch (Exception e){
				System.out.println("Please select an option." +
						"\n>");
			}
		}

	}



}
