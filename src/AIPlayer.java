import java.util.Random;

/**
 * AI Player subclass
 */
public class AIPlayer extends Player {
	Random rand = new Random(System.currentTimeMillis());

	/**
	 * Constructor for AIPlayer
	 * @param AInum which AI player this is
	 * @param money starting wallet
	 */
	AIPlayer(int AInum, int money){
		super("AI " + AInum, money);
	}

	AIPlayer(String name){
		super(name);
	}


	/**
	 * AI turn logic.
	 * @return boolean declaring if a winstate has been reached.
	 */
	@Override
	public boolean playTurn(){
		Card toDeal;


		while(this.isPlayable()){
			if(this.getHand().getValue() <= 10){ //double if hand is less than or equal to 10
				toDeal = Table.deck.deal();
				System.out.println(this.getName() + " doubled and was dealt " + toDeal.toString());

				hit(toDeal);

				int previousBetAmount = this.getBetAmount();
				this.addMoney(previousBetAmount);  //reset bet to allow for doubling bet amount
				this.setBetAmount(2*previousBetAmount);

				System.out.println(this.getName() + "'s current hand is " + this.getHand().toString());
				this.setPlayable(false);

			} else if (this.getHand().getValue() < 16){ //hit if less than 15
				toDeal = Table.deck.deal();
				System.out.println(this.getName() + " hit and was dealt " + toDeal.toString());

				hit(toDeal);

				System.out.println(this.getName() + "'s hand is now " + this.getHand().toString());

			} else if (this.getHand().getValue() <= 21){    //stand otherwise, provided it's not a bust

				System.out.println(this.getName() + " stood.");
				this.setPlayable(false);
			}

			if(this.getHand().getValue() > 21){
				this.setPlayable(false);
				System.out.println("Bust! No payout.");
			}
		}



		return false;
	}

	public int bet(){
		int bet = rand.nextInt(this.getMoney()/2);
		setBetAmount(bet);
		return bet;
	}

}
