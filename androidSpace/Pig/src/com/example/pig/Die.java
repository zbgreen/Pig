package com.example.pig;

import java.util.Random;

/**
 * Class for the die roll button.
 * @author Zachary Green
 * @version 11/23/14
 */
public class Die 
{
	private final static int SIDES = 6;
	private int value;
	
	/**
	 * Sets die value to zero.
	 */
	public Die()
	{
		value = 0;
	}
	
	/**
	 * Gets die roll
	 * @return die value
	 */
	public int getValue()
	{
		return this.value;
	}

	/**
	 * Rolls the die and sets a value between 1-6.
	 */
	public void roll()
	{
		Random random = new Random();
		this.value = random.nextInt(SIDES) + 1;
		MainActivity.dieValue(this.value);
	}
}
