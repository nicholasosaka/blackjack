import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	private static Scanner scan = new Scanner(System.in);
	private static Deck deck = new Deck();
	private static Hand hand = new Hand();
	private static Table table = new Table();

	public static void main(String[] args){

		initialize();
		playGame();

	}

	private static void playGame() {
		boolean continuePlay = true;
		do{

		}while(continuePlay);
	}


	public static void initialize(){
		System.out.println("Welcome to Blackjack");

		System.out.print("How many players are at the table? ");
		int numPlayers;
		while(true) {
			try {
				numPlayers = Integer.parseInt(scan.nextLine());
				if(numPlayers <= 0){
					throw new ArithmeticException("Please enter a positive number.");
				}
				break;
			}catch(ArithmeticException ae){
				System.out.println(ae.getMessage());

			}catch(NumberFormatException nfe){
				System.out.println("Please enter a valid number.");

			}catch(Exception e){
				System.out.println("Please enter a number.");
			}
		}

		table.addPlayers(numPlayers);
	}


}
