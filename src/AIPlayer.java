import java.util.Random;

/**
 * AI Player subclass
 */
public class AIPlayer extends Player {
	private Random rand = new Random(System.currentTimeMillis());

	/**
	 * Constructor for AIPlayer
	 * @param AInum which AI player this is
	 * @param money starting wallet
	 */
	AIPlayer(int AInum, int money){
		super("AI " + AInum, money);
	}

	/**
	 * Constructor for AIPlayer
	 * @param name a name for the player
	 */
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

			try{
				Thread.sleep(1000); //sleep for a second to allow user to absorb information
			}catch(InterruptedException ignored){

			}

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

			if(this.getHand().getValue() > 21){ //find if bust
				this.setPlayable(false);
				System.out.println("Bust! No payout.");
			}
		}



		return false;
	}

	/**
	 * Betting logic for AI player
	 * @return random int, up to half of the money the player has
	 */
	public int bet(){
		int bet;

		try{
			Thread.sleep(500); //sleep for half a second to allow user to absorb information
		}catch(InterruptedException ignored){

		}

		do {

			bet = rand.nextInt(this.getMoney() / 2);
			setBetAmount(bet);

		}while(bet == 0);

		return bet;
	}

}
