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

		while(this.isPlayable()){   //as long as the dealer is still in play,

			if(this.getHand().getValue() < 17){ //if the hand value is less than 17, hit
				toDeal = Table.deck.deal();
				hit(toDeal);
				System.out.println("The Dealer drew a " + toDeal.toString() + " making their hand " + this.getHand().toString());
			} else {
				this.setPlayable(false);    //if it's 17 or above, stand.
			}
		}

		return false;
	}
}
