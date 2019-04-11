public class AIPlayer extends Player {

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
