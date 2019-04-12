import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Table {
	Scanner scan = new Scanner(System.in);

	private int roundNumber;
	public ArrayList<Player> players;
	private Deck deck;

	/**
	 * Default Constructor
	 * Sets round number to 0, initializes deck with 52 cards
	 * initializes players to an empty list.
	 */
	Table() {
		roundNumber = 0;

		deck = new Deck();
		deck.populate(52);

		players = new ArrayList<>();
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

		boolean winState = false;
		int playerIndex = 0;

		do{

			Player currentPlayer = players.get(playerIndex % players.size());
			System.out.println(currentPlayer.getName() + ", it's your turn.");
			winState = currentPlayer.playTurn();

			playerIndex++;

		}while(!winState);

		printStats();

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
