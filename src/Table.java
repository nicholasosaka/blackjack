import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Table Object to hold players and deck, as well as run blackjack game logic.
 */
public class Table {
	//fields
	private Scanner scan = new Scanner(System.in);

	private int roundNumber;
	private ArrayList<Player> players;
	public static Deck deck;    //static to allow for access from Player subclasses
	private Dealer dealer;

	/**
	 * Default Constructor
	 * Sets round number to 0, initializes deck with 52 cards
	 * initializes players to an empty list.
	 */
	Table() {
		roundNumber = 0;

		deck = new Deck(true, 104); //deal 104 cards from a fresh deck
		deck.shuffle();

		players = new ArrayList<>();
		dealer = new Dealer();

	}

	/**
	 * Method to add players to the table. Allows for creation of AI Players
	 * @param numPlayers Integer number of players to add.
	 */
	public void addPlayers(int numPlayers){
		int aiPlayerNum = 0;

		for(int i = 0; i < numPlayers; i++){    //create n number of players, provided n as argument
			System.out.print("Please enter player " + (i+1) + "'s name: (Enter for AI player) ");   //prompt
			String playerName;

			try{
				playerName = scan.nextLine();   //grab input
				if(playerName.toLowerCase().trim().equals("")){ //if enter, then create an AI player
					players.add(new AIPlayer(++aiPlayerNum, 500));  //pass in new AIPlayer object with a pre-increment so that AI's are numbered sequentially from 1.

				} else {
					players.add(new Human(playerName, 500));    //pass a new human with the string provided if the input as anything other than an enter

				}

			}catch(NoSuchElementException nsee){ //catches
				System.out.println("Enter a name.");
				i--;    //i-- to loop back 1 player

			}catch(IllegalStateException ise){
				scan = new Scanner(System.in); //ISE is when scanner is closed, so make a new scanner.
				System.out.println("Please reenter player name.");
				i--;

			} catch (Exception e){  //just in case something happens
				System.out.println("Please reenter player name.");
				i--;
			}

		}

	}

	/**
	 * Method to play a round of blackjack.
	 */
	public void playRound() {
		System.out.println("Round Number " + (++roundNumber));

		boolean winState;   //used in case there's an instant win

		tableBet();     //the whole table bets
		System.out.println("\n\n"); //new lines

		try {
			Thread.sleep(2000); //sleep for 2 seconds to allow for user to absord information
		}catch(InterruptedException ignored) {

		}

		winState = tableDeal(); //deal for the whole table. if the dealer get blackjack, then a winstate was reached
		System.out.println("\n\n");

		if(winState){   //if the winstate was reached, then everybody loses
			System.out.println("Sorry, everybody loses.");
		} else {    //if not,

			for(Player p : players) {   //loop through each player for their turn
				if (p.isPlayable()) {   //check if the player is eligible to play

					System.out.println(p.getName() + ", it's your turn.");  //print name & hand
					System.out.println("Hand: " + p.getHand().toString());
					p.playTurn();   //play turn

					System.out.println();   //new line

					try {
						Thread.sleep(1500); //sleep for 1.5 seconds to allow for user to absord information
					}catch(InterruptedException ignored) {

					}

				}
			}

			dealer.playTurn();  //dealer goes last

			int dealerValue = dealer.getHand().getValue();  //store dealer value in a variable (reduces method calls)

			if (dealerValue > 21){  //if a bust,
				System.out.println("The dealer busts!");
				dealer.setBust(true);
			} else {    //if not a bust
				System.out.println("The dealer's hand is worth " + dealerValue);

			}

			for(Player p : players){    //loop through all players for comparison against dealer's hand
				if(dealer.isBust() || (p.getHand().getValue() > dealerValue && p.getHand().getValue() <= 21 && !p.getHand().isBlackjack())){    //if the dealer busted, OR if they got a hand higher than the dealer without busting, AND they did not get a blackjack,
					p.payout(2);    //pay their bet and refund them the original bet
					System.out.println(p.getName() + " beat the dealer. 1:1 payout.");
				} else if (p.getHand().getValue() == dealerValue && p.getHand().getValue() <= 21){  //if they tied with the dealer without busting, refund the bet.
					p.payout(1);
					System.out.println(p.getName() + " has a tie. The bet was returned.");
				}
			}

		}

		for(Player p : players){    //reset after a round and check if DQ

			if(p.getMoney() < 0){   //if they went broke
				System.out.println(p.getName() + " has no money left.");
				p.setPlayable(false);   //don't let them play next round
			}

			if(p.getMoney() > 0) {  //if the player has any money, allow them to play the next round
				p.setPlayable(true);
			}

			deck.add(p.dump()); //dump cards back into deck
		}

		dealer.setBust(false);  //reset dealer bust state
		deck.add(dealer.dump()); //dump dealer cards

	}

	/**
	 * Method to allow for all players to bet
	 */
	public void tableBet(){
		for(Player p : players){    //for each player
			if(!p.getClass().equals(Dealer.class) && p.isPlayable()) {  //as long as they're not the dealer and they're eligible to play
				int betAmount = p.bet();    //bet
				System.out.println(p.getName() + " has bet $" + betAmount);
			}
		}
	}

	/**
	 * Deal a hand to all players
	 */
	public boolean tableDeal(){
		for(Player p : players){    //loop through each player

			try{
				Thread.sleep(500); //sleep for half a second to allow user to absorb information
			}catch(InterruptedException ignored){

			}

			ArrayList<Card> toDeal = deck.deal(2);  //draw two cards

			for(Card card : toDeal){    //hit for each card
				p.hit(card);
			}

			System.out.println(p.getName() + " was dealt " + toDeal.get(0) + ", " + toDeal.get(1)); //show each card that was dealt

			if(p.getHand().isBlackjack()){  //if it's a blackjack,
				System.out.println("Blackjack! 3:2 payout.");
				p.payout(2.5);  //payout 3:2 ratio and then prevent them from playing the round
				p.setPlayable(false);
			}
		}

		Card dealerFirst = deck.deal(); //grab the first card for the dealer
		dealer.hit(dealerFirst);

		Card dealerSecond = deck.deal();    //grab the second card for the dealer
		dealerSecond.setFaceUp(false);  //the second card is hidden
		dealer.hit(dealerSecond);

		if(dealer.getHand().isBlackjack()){ //if the second card was a blackjack
			dealerSecond.setFaceUp(true);   //reveal the card
			System.out.println("The dealer has a blackjack with cards " + dealerFirst.toString() + " and " + dealerSecond.toString());  //show the cards
			return true;    //return that a winstate was reached (prevents the round from continuing)
		} else {
			System.out.println("The dealer was dealt " + dealerFirst.toString() + " and " + dealerSecond.toString());   //otherwise, show the first card normally and the second card obfuscated
		}

		return false;
	}

	/**
	 * Method that prints statistic after a round.
	 */
	public void printStats() {
		System.out.println("Round stats: Round " + roundNumber);
		for(Player p : players){    //loop through each player
			System.out.format("%-15s %7s %n", p.getName(), "$" + p.getMoney()); //format their name and bet in a table
		}
	}
}
