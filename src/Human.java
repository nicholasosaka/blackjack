import java.util.Scanner;

public class Human extends Player{

	Human(String name){
		super(name);
	}

	Human(String name, int money){
		super(name, money);
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
