package com.example.pig;

import java.util.concurrent.TimeUnit;

/**
 * Implements a computer of type Player
 * @author Zach Green
 * @version  11/28/14
 */
public class Computer implements Player
{
	private static final int WIN = 100;
	private static final int FLAG = -1;
	private static final int DELAY = 500;
	
	private int roundPoints;
	private int totalPoints;
	private boolean turn;
	
	private Die die;
	private Human human;
	
	/**
	 * Constructor that initializes variables and updates total points to 0
	 */
	public Computer()
	{
		die = new Die();
		roundPoints = 0;
		totalPoints = 0;
		turn = false;
		
		MainActivity.compTotalScore(totalPoints);
	}
	
	/**
	 * When called gives control to computer player.
	 */
	public void control() 
	{
		turn = true;
		while(turn)
		{
			die.roll();
			checkRoll();
		}
		human.control();
	}
	
	/**
	 * Gets the human object
	 */
	public void getPlayer(Player player)
	{
		human = (Human) player;
	}
	
	/**
	 * @return  computer's current round points
	 */
	public int getRoundPoints() 
	{
		return roundPoints;
	}

	/**
	 * @return  computer's current total points
	 */
	public int getTotalPoints() 
	{
		return totalPoints;
	}

	/**
	 * @return  true = turn, false = human turn
	 */
	public boolean getTurn() 
	{
		return turn;
	}

	/**
	 * Adds round points to total and updates TextViews
	 */
	public void hold() 
	{
		turn = true;
		totalPoints += roundPoints;
		roundPoints = 0;
		try {
			TimeUnit.MILLISECONDS.sleep(DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainActivity.roundScore(roundPoints);
		MainActivity.compTotalScore(totalPoints);
	}

	/**
	 * Checks for a 1 or a winning score
	 */
	public void checkRoll() 
	{
		if (die.getValue() == 1)
		{
			rollOfOne();
		}
		else 
		{	
			roundPoints += die.getValue();
			try {
				TimeUnit.MILLISECONDS.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			MainActivity.roundScore(roundPoints);
			if(roundPoints + totalPoints >= WIN)
			{
				MainActivity.compTotalScore(FLAG);
				turn = false;
			}
			checkHold();
		}
	}

	/**
	 * Checks if the computer should hold or not.
	 */
	private void checkHold() 
	{
		if (roundPoints + totalPoints < 71)
		{
			if (roundPoints >= (21 + ((human.getTotalPoints() - (roundPoints + totalPoints)) / 8)))
			{
				hold();
				turn = false;
			}
		}
	}
	
	/**
	 * Takes away points if a 1 is rolled.
	 */
	public void rollOfOne() 
	{
		roundPoints = 0;
		try {
			TimeUnit.MILLISECONDS.sleep(DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainActivity.roundScore(roundPoints);
		turn = false;
	}

	/**
	 * Resets everything to 0
	 */
	@Override
	public void reset() 
	{
		roundPoints = 0;
		totalPoints = 0;
		turn = false;
	}
}
