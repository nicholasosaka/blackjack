import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Table {
	Scanner scan = new Scanner(System.in);

	private int roundNumber;
	public ArrayList<Player> players;
	private Deck deck;

	Table() {
		roundNumber = 0;

		deck = new Deck();
		deck.populate(52);

		players = new ArrayList<>();
	}


	public void addPlayers(int numPlayers){
		for(int i = 0; i < numPlayers; i++){
			System.out.print("Please enter player " + (i+1) + "'s name: ");
			String playerName;

			try{
				playerName = scan.nextLine();
				players.add(new Human(playerName));

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

	public void playRound() {
		System.out.println("Round number " + (++roundNumber));


	}

	public void printStats() {
		System.out.println("Round stats: Round " + roundNumber);
		for(Player p : players){
			System.out.format("%-15s %7s %n", p.getName(), "$" + p.getMoney());
		}
	}
}
