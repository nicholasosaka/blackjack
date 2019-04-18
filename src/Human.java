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

		while(this.isPlayable()) {  //as long as the player can keep playing their turn, continue
			int choice = turnMenu();    //grab choice from menu

			switch (choice) {
				case 1: //hit
					toDeal = Table.deck.deal();    //grab card from deck
					System.out.println(getName() + ", you were dealt " + toDeal.toString());
					hit(toDeal);    //hit
					System.out.println("Your hand is now " + getHand().toString());
					break;

				case 2: //stand - do nothing
					this.setPlayable(false);    //prevent the player from playing further (stand is the defacto exit condition in blackjack)
					break;

				case 3: //double
					if (this.getMoney() <= this.getBetAmount()*2){  //as long as the player can afford to double their bet

						toDeal = Table.deck.deal(); //deal a card
						System.out.println(getName() + ", you were dealt " + toDeal.toString());

						hit(toDeal);    //hit to player's hand

						int previousBetAmount = this.getBetAmount();    //temporarily store the bet amount as previousBetAmount
						this.addMoney(previousBetAmount);  //reset bet to allow for doubling bet amount
						this.setBetAmount(2 * previousBetAmount);   //bet the previous amount * 2

						System.out.println("Your bet is now $" + this.getBetAmount());  //sysouts for clarity
						System.out.println("Your hand is now " + getHand().toString());

						this.setPlayable(false);    //player is not allowed to move after doubling

					} else {
						System.out.println("Not enough money to double.");  //if they can't pay, tell the user
					}
					break;

				case 4: //surrender
					this.setPlayable(false); //set playable to false, skip further turns
					Table.deck.add(this.dump());    //give the deck the cards in player's hand
			}

			if(this.getHand().getValue() > 21){ //check if bust, then if it is:
				this.setPlayable(false);    //disallow player from playing further in the round
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

		while(true) {   //loop forever (until return is thrown)
			try {
				System.out.println(this.getName() + ", please select a choice.");   //print menu
				System.out.print("1. Hit\n" +
						"2. Stand\n" +
						"3. Double\n" +
						"4. Surrender\n" +
						"> ");

				choice = Integer.parseInt(scan.nextLine()); //grab user input

				if( choice < 1 || choice > 4){  //if input is not valid
					throw new ArithmeticException("Please enter a valid option.");
				}

				return choice;  //if input is valid, then we know we can return

			} catch (ArithmeticException ae) {  //catches
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
