import java.util.ArrayList;

public abstract class Player {

	private String name;
	private int bank;
	private Hand hand;

	Player(String name){
		this.name = name;
		bank = 500;
	}

	Player(int money){
		this.bank = money;
	}

	Player(String name, int money){
		this.name = name;
		this.bank = money;
	}

	public String getName() {
		return name;
	}

	public int getMoney() {
		return bank;
	}

	public void addMoney(int money) {
		this.bank += money;
	}

	public void removeMoney(int money){
		this.bank -= money;
	}

	public void hit(Card card){
		hand.hit(card);
	}

	public ArrayList<Card> dump(){
		return hand.dump();
	}

	public abstract boolean playTurn();


}
