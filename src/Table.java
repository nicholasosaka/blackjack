import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.NoSuchElementException;
=import java.util.Scanner;

public class Table {
	Scanner scan = new Scanner(System.in);

	private int roundNumber;
	private ArrayList<Human> players;
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

			}catch(IllegalStateException ise){

			}

		}
	}

}
