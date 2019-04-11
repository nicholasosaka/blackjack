public class Human extends Player{

	Human(String name){
		super(name);
	}

	Human(int money){
		super(money);
	}

	/**
	 * Human player turn logic.
	 * @return boolean declaring whether a winstate has been reached.
	 */
	@Override
	public boolean playTurn(){
		//TODO implement turn logic

		return false;
	}
}
