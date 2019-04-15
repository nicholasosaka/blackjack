import java.util.ArrayList;
import java.util.Scanner;

/**
 * Player abstract class.
 */
public abstract class Player {
	private Scanner scan = new Scanner(System.in);
	private String name;
	private int bank;
	private Hand hand;
	private int betAmount;

	/**
	 * Constructor for name
	 * @param name player's name
	 */
	Player(String name){
		this.name = name;
		bank = 500;
	}

	/**
	 * Constructor for name and money
	 * @param name player's name
	 * @param money player's starting wallet
	 */
	Player(String name, int money){
		hand = new Hand();
		this.name = name;
		this.bank = money;
	}

	/**
	 * getter for player hand
	 * @return player Hand object
	 */
	public Hand getHand() {
		return hand;
	}

	/**
	 * getter for name
	 * @return String with name
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter for money in wallet
	 * @return int with wallet value
	 */
	public int getMoney() {
		return bank;
	}

	/**
	 * add money to wallet
	 * @param money money to add to wallet
	 */
	public void addMoney(int money) {
		this.bank += money;
	}

	/**
	 * remove money from wallet
	 * @param money money to remove from wallet
	 */
	public void removeMoney(int money){
		this.bank -= money;
	}

	/**
	 * Method to add card to hand
	 * @param card card to add
	 */
	public void hit(Card card){
		hand.hit(card);
	}

	/**
	 * Method to return cards from hand
	 * @return list of Card objects that were in hand
	 */
	public ArrayList<Card> dump(){
		return hand.dump();
	}

	/**
	 * logic for turn gameplay
	 * @return boolean representing if a winning gamestate was reached
	 */
	public abstract boolean playTurn();


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

				betAmount = bet;
				return bet;

			} catch (ArithmeticException ae){
				System.out.print(ae.getMessage());

			} catch (Exception e){
				System.out.print("Please enter a bet.");
			}
		}
	}
}
