package com.example.pig;

import com.example.pig.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity class for the Pig Game android app.
 * @author Zach Green
 *@version 11/18/14
 */
public class MainActivity extends Activity 
{
	private Button roll;
	private Button hold;
	private Button newGame;
	private Human human;
	private Computer computer;
	
	private static TextView roundLabel;
	private static TextView roundScore;
	private static TextView dieLabel;
	private static TextView dieValue;
	private static TextView humanLabel;
	private static TextView humanTotalScore;
	private static TextView compLabel;
	private static TextView compTotalScore;

	/**
	 * Initializes TextView and Buttons for layout and starts the human and 
	 * computer players.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		roundLabel = (TextView) findViewById(R.id.Round_Label);
		roundScore = (TextView) findViewById(R.id.Round_Score);
		dieLabel = (TextView) findViewById(R.id.Die_Label);
		dieValue = (TextView) findViewById(R.id.Die);
		humanLabel = (TextView) findViewById(R.id.Human_Label);
		humanTotalScore = (TextView) findViewById(R.id.Human_Score);
		compLabel = (TextView) findViewById(R.id.Comp_Label);
		compTotalScore = (TextView) findViewById(R.id.Comp_Score);
		roll = (Button) findViewById(R.id.Roll);
		hold = (Button) findViewById(R.id.Hold);
		newGame = (Button) findViewById(R.id.New_Game);
		
		human = new Human();
		computer = new Computer();
		human.getPlayer(computer);
		computer.getPlayer(human);
		
		addOnButtonListener();
		human.control();
		dieValue(0);
	}

	/**
	 * Inflate the menu; this adds items to the action bar if it is present.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Handle action bar item clicks here. The action bar will
	 * automatically handle clicks on the Home/Up button, so long
	 * as you specify a parent activity in AndroidManifest.xml.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//private method
	//Adds button listeners for Roll, Hold and NewGame
	private void addOnButtonListener() 
	{
		roll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				human.humanRoll();
			}
		});
		
		hold.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				human.hold();
			}
		});
		
		newGame.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				roundScore(0);
				humanTotalScore(0);
				compTotalScore(0);
				dieValue(0);
				human.reset();
				computer.reset();
				human.control();
			}
		});
	}

	/**
	 * Updates round score TextView
	 * @param score  current round score
	 */
	public static void roundScore(int score)
	{
		String value = Integer.toString(score);
		roundScore.setText(value);
	}
	
	/**
	 * Updates die TextView
	 * @param value  number from die
	 */
	public static void dieValue(int value) 
	{
		String number = Integer.toString(value);
		dieValue.setText(number);
	}
	
	/**
	 * Updates human's total score
	 * @param score  current human score
	 */
	public static void humanTotalScore(int score)
	{
		if (score != -1)
		{
			String value = Integer.toString(score);
			humanTotalScore.setText(value);
		}
		else humanTotalScore.setText("Win!");
	}
	
	/**
	 * Updates computer's total score
	 * @param score  current human score
	 */
	public static void compTotalScore(int score)
	{
		if (score != -1)
		{
			String value = Integer.toString(score);
			compTotalScore.setText(value);
		}
		else compTotalScore.setText("Win!");
	}
}