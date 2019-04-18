/**
 * Dealer class which runs dealer logic.
 */
public class Dealer extends AIPlayer{

	/**
	 * Default Constructor, sets same to Dealer.
	 */
	Dealer(){
		super("Dealer");
	}

	/**
	 * Dealer turn logic.
	 * @return boolean declaring whether a winstate has been reached.
	 */
	@Override
	public boolean playTurn(){
		Card toDeal;

		this.getHand().revealCards();   //reveal the hidden card
		System.out.println("The Dealer's hand is " + this.getHand().toString());

		while(this.isPlayable()){
			if(this.getHand().getValue() < 17){
				toDeal = Table.deck.deal();
				hit(toDeal);
				System.out.println("The Dealer drew a " + toDeal.toString() + " making their hand " + this.getHand().toString());
			} else {
				this.setPlayable(false);
			}
		}

		return false;
	}
}
