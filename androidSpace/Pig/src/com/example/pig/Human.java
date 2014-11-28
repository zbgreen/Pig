package com.example.pig;

/**
 * Implements human of type Player
 * @author Zach Green
 * @version 11/28/14
 */
public class Human implements Player
{
	private final int WIN = 100;
	private final int FLAG = -1;

	private int roundPoints;
	private int totalPoints;
	private boolean turn;

	private Die die;
	private Computer computer;
	
	/**
	 * Initializes variables and updates TextViews
	 */
	public Human()
	{
		die = new Die();
		roundPoints = 0;
		totalPoints = 0;
		turn = true;
		
		MainActivity.roundScore(roundPoints);
		MainActivity.humanTotalScore(totalPoints);
	}
	
	/**
	 * When called gives turn to human.
	 */
	public void control() 
	{
		turn = true;
	}

	/**
	 * Gets computer object.
	 */
	public void getPlayer(Player player) 
	{
		computer = (Computer) player;
	}
	
	/**
	 * @return  value of die roll.
	 */
	public int getDieRoll()
	{
		return die.getValue();
	}
	
	/**
	 * @return  current human round points.
	 */
	public int getRoundPoints()
	{
		return roundPoints;
	}
	
	/**
	 * @return  true = turn, false = computer turn.
	 */
	public boolean getTurn()
	{
		return turn;
	}

	/**
	 * @return  current human total points
	 */
	public int getTotalPoints() 
	{
		return totalPoints;
	}

	/**
	 * Removes round points when a 1 is rolled.
	 */
	public void rollOfOne() 
	{
		roundPoints = 0;
		MainActivity.roundScore(roundPoints);
		turn = false;
		computer.control();
	}

	/**
	 * Checks roll for a 1 or win.
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
			MainActivity.roundScore(roundPoints);
			turn = true;
			if(roundPoints + totalPoints >= WIN)
			{
				MainActivity.humanTotalScore(FLAG);
				turn = false;
			}
		}
	}
	
	/**
	 * Adds round points to total points
	 */
	public void hold()
	{
		if (!turn)
		{
			return;
		}
		totalPoints += roundPoints;
		roundPoints = 0;
		MainActivity.roundScore(roundPoints);
		MainActivity.humanTotalScore(totalPoints);
		turn = false;
		computer.control();
	}

	/**
	 * Rolls the die and checks value.
	 */
	public void humanRoll()
	{
		if (!turn)
		{
			return;
		}
		die.roll();
		checkRoll();
	}

	/**
	 * Resets all variables to 0 or false.
	 */
	public void reset() 
	{
		roundPoints = 0;
		totalPoints = 0;
		turn = false;
	}
}
