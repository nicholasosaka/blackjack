import java.util.Scanner;

/**
 * Human player subclass. Runs game logic for human player turns.
 */
public class Human extends Player{

	Scanner scan = new Scanner(System.in);

	/**
	 * Constructor for name
	 * @param name player's name
	 */
	Human(String name){
		super(name);
	}

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
		int choice = -1;

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

	/**
	 * Method to bet. Doesn't allow to bet more than in player's bank and doesn't allow betting $0.
	 * @return  int representative of amount bet
	 */
	public int bet() {
		int bet;
		System.out.print("How much would you like to bet?");

		while (true) {
			try {
				System.out.print(" $");
				bet = Integer.parseInt(scan.nextLine());    //grab user input

				if(bet > this.getMoney()){  //make sure bet is valid
					throw new ArithmeticException("You can't bet more than you have");
				} else if (bet == 0){
					throw new ArithmeticException("You can't bet nothing!");
				} else if (bet < 0){
					throw new ArithmeticException("Please bet a positive number.");
				}

				return bet;

			} catch (ArithmeticException ae){
				System.out.print(ae.getMessage());

			} catch (Exception e){
				System.out.print("Please enter a bet.");
			}
		}
	}

}
