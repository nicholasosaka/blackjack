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
		//TODO implement AI for turn playing

		return false;
	}

	public int bet(){
		int bet = rand.nextInt(this.getMoney()/2);
		setBetAmount(bet);
		return bet;
	}

}
