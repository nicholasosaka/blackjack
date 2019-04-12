/**
 * AI Player subclass
 */
public class AIPlayer extends Player {

	/**
	 * Constructor for AIPlayer
	 * @param AInum which AI player this is
	 * @param money starting wallet
	 */
	AIPlayer(int AInum, int money){
		super("AI " + AInum, money);
	}


	/**
	 * AI turn logic.
	 * @return boolean declaring if a winstate has been reached.
	 */
	@Override
	public boolean playTurn(){
		//TODO implement AI

		return false;
	}

}
