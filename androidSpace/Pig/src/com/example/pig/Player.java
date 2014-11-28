package com.example.pig;

/**
 * Interface for types of players for the Pig Game
 * @author Zach Green
 * @version 11/28/14
 */
public interface Player 
{
	int getRoundPoints();
	int getTotalPoints();
	boolean getTurn();
	void checkRoll();
	void hold();
	void getPlayer(Player player);
	void control();
	void rollOfOne();
	void reset();
}
