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
		Card toDeal;

		while(this.isPlayable()) {
			int choice = turnMenu();

			switch (choice) {
				case 1: //hit
					toDeal = Table.deck.deal();    //grab card from deck
					System.out.println(getName() + ", you were dealt " + toDeal.toString());
					hit(toDeal);    //hit
					System.out.println("Your hand is now " + getHand().toString());
					break;

				case 2: //stand - do nothing
					this.setPlayable(false);
					break;

				case 3: //double
					if (this.getMoney() > this.getBetAmount()*2){
						toDeal = Table.deck.deal();
					System.out.println(getName() + ", you were dealt " + toDeal.toString());

					hit(toDeal);

					int previousBetAmount = this.getBetAmount();
					this.addMoney(previousBetAmount);  //reset bet to allow for doubling bet amount
					this.setBetAmount(2 * previousBetAmount);

					System.out.println("Your bet is now $" + this.getBetAmount());
					System.out.println("Your hand is now " + getHand().toString());

					this.setPlayable(false);
					}
					break;

				case 4: //surrender
					this.setPlayable(false); //set playable to false, skip further turns
					Table.deck.add(this.dump());
			}

			if(this.getHand().getValue() > 21){
				this.setPlayable(false);
				System.out.println("Bust! No payout.");
			}

		}

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
