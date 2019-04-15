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
	private Deck deck;
	private Dealer dealer;

	/**
	 * Default Constructor
	 * Sets round number to 0, initializes deck with 52 cards
	 * initializes players to an empty list.
	 */
	Table() {
		roundNumber = 0;

		deck = new Deck(true, 52);
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
		int playerIndex = 0;

		tableBet();
		System.out.println("\n\n");
		winState = tableDeal();
		System.out.println("\n\n");

		while(!winState){

			Player currentPlayer = players.get(playerIndex % players.size());
			System.out.println(currentPlayer.getName() + ", it's your turn.");
			winState = currentPlayer.playTurn();

			playerIndex++;

		}



	}

	/**
	 * Method to allow for all players to bet
	 */
	public void tableBet(){
		for(Player p : players){
			if(!p.getClass().equals(Dealer.class)) {
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
				p.payout(1.5);
			}
		}

		Card dealerFirst = deck.deal();
		dealer.hit(dealerFirst);
		System.out.println("The dealer was dealt " + dealerFirst.toString());

		Card dealerSecond = deck.deal();
		dealerSecond.setFaceUp(false);
		dealer.hit(dealerSecond);

		if(dealer.getHand().isBlackjack()){
			dealerSecond.setFaceUp(true);
			System.out.println("The dealer has a blackjack with cards " + dealerFirst.toString() + " and " + dealerSecond.toString());
			return true;
		} else {
			System.out.println("The dealer was dealt " + dealerSecond.toString());
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
