import java.util.ArrayList;

public class Driver {
	public static void main(String[] args){

		Card ace = new Card(0);
		Card ten = new Card(9);
		Card king = new Card(12);

		System.out.println(ace);
		System.out.println(ten);
		System.out.println(king);

		Hand hand = new Hand();

		hand.hit(ace);
		System.out.println("Hit ace: " + hand.getValue());

		hand.hit(ten);
		System.out.println("Hit ten: " + hand.getValue());

		hand.hit(ace);
		System.out.println("Hit ace: " + hand.getValue());

		System.out.println(hand.toString());
	}
}
