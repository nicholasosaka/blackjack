import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Table Object to hold players and deck, as well as run blackjack game logic.
 */
public class Table {
	Scanner scan = new Scanner(System.in);

	private int roundNumber;
	public ArrayList<Player> players;
	public static Deck deck;
	private Dealer dealer;

	/**
	 * Default Constructor
	 * Sets round number to 0, initializes deck with 52 cards
	 * initializes players to an empty list.
	 */
	Table() {
		roundNumber = 0;

		deck = new Deck(true, 104);
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
		for(int i = 0; i < numPlayers; i++){
			System.out.print("Please enter player " + (i+1) + "'s name: (Enter for AI player) ");
			String playerName;

			try{
				playerName = scan.nextLine();
				if(playerName.toLowerCase().trim().equals("")){
					players.add(new AIPlayer(++aiPlayerNum, 500));

				} else {
					players.add(new Human(playerName, 500));

				}

			}catch(NoSuchElementException nsee){
				System.out.println("Enter a name.");
				i--;

			}catch(IllegalStateException ise){
				scan = new Scanner(System.in); //ISE is when scanner is closed, so make a new scanner.
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

		boolean winState;

		tableBet();
		System.out.println("\n\n");

		winState = tableDeal();
		System.out.println("\n\n");

		if(winState){
			System.out.println("Sorry, everybody loses.");
		} else {

			for(Player p : players) {
				if (p.isPlayable()) {
					System.out.println(p.getName() + ", it's your turn.");
					p.playTurn();

					System.out.println();   //new line
				}
			}

			dealer.playTurn();

			int dealerValue = dealer.getHand().getValue();
			System.out.println("The dealer's hand is worth " + dealerValue);

			for(Player p : players){
				if(p.getHand().getValue() > dealerValue && p.getHand().getValue() <= 21 && !p.getHand().isBlackjack()){
					p.payout(2);
					System.out.println(p.getName() + " beat the dealer. 1:1 payout.");
				} else if (p.getHand().getValue() == dealerValue && p.getHand().getValue() <= 21){
					p.payout(1);
					System.out.println(p.getName() + " has a tie. The bet was returned.");
				}
			}

		}

		for(Player p : players){    //reset after a round and check if DQ

			if(p.getMoney() < 0){
				System.out.println(p.getName() + " has no money left.");
				p.setPlayable(false);
			}

			if(p.getMoney() > 0) {
				p.setPlayable(true);
			}
			deck.add(p.dump());
		}

	}

	/**
	 * Method to allow for all players to bet
	 */
	public void tableBet(){
		for(Player p : players){
			if(!p.getClass().equals(Dealer.class) && p.isPlayable()) {
				int betAmount = p.bet();
				System.out.println(p.getName() + " has bet $" + betAmount);
			}
		}
	}

	/**
	 * Deal a hand to all players
	 */
	public boolean tableDeal(){
		for(Player p : players){
			ArrayList<Card> toDeal = deck.deal(2);

			for(Card card : toDeal){
				p.hit(card);
			}

			System.out.println(p.getName() + " was dealt " + toDeal.get(0) + ", " + toDeal.get(1));

			if(p.getHand().isBlackjack()){
				System.out.println("Blackjack! 3:2 payout.");
				p.payout(2.5);
				p.setPlayable(false);
			}
		}

		Card dealerFirst = deck.deal();
		dealer.hit(dealerFirst);

		Card dealerSecond = deck.deal();
		dealerSecond.setFaceUp(false);
		dealer.hit(dealerSecond);

		if(dealer.getHand().isBlackjack()){
			dealerSecond.setFaceUp(true);
			System.out.println("The dealer has a blackjack with cards " + dealerFirst.toString() + " and " + dealerSecond.toString());
			return true;
		} else {
			System.out.println("The dealer was dealt " + dealerFirst.toString() + " and " + dealerSecond.toString());
		}

		return false;
	}

	/**
	 * Method to print table state (players, money, cards)
	 */
	public void printTableState(){

	}

	/**
	 * Method that prints statistic after a round.
	 */
	public void printStats() {
		System.out.println("Round stats: Round " + roundNumber);
		for(Player p : players){
			System.out.format("%-15s %7s %n", p.getName(), "$" + p.getMoney());
		}
	}
}
