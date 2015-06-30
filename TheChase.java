package test;

import java.math.BigDecimal;

public class TheChase {
	/**
	 Created by RachanaSiddam on 22-Jun-2015
	 */
	public static int playTheGame(int n){
		int steps = 0;
		int dice1 = 1, dice2 = (n/2) + 1;
		
		while(dice1 != dice2){
			steps++;
			int diceValue1 = 1 + (int) (Math.random() * (6));
			int diceValue2 = 1 + (int) (Math.random() * (6));
			//System.out.println("Dice1: " + diceValue1 + " Dice2: " + diceValue2);
			if(diceValue2 == 1){
				dice2 = dice2 +1;
				if(dice2 > 100)
					dice2 = 1;
			}
			
			if(diceValue2 == 6){
				dice2 = dice2 -1 ;
				if(dice2 < 1)
					dice2 = 100;
			}
	
			if(diceValue1 == 1){
				dice1 = (dice1 + 1);
				if(dice1 > 100)
					dice1 = 1;
			}

			if(diceValue1 == 6){
				dice1 = (dice1 - 1);
				if(dice1 < 1)
					dice1 = 100;
			}		
			//System.out.println("Dice1 at: "+ dice1 +" Dice2 at:" + dice2);
		}
		return steps;
	}
	
	public static void main(String[] args){
		double total = 0;
		int iterations = 1000000;
		int numberOfPeople = 100;
		for(int i = 0; i < iterations; i++)
			total = total + playTheGame(numberOfPeople);
		
		BigDecimal bd1 = new BigDecimal(total).setScale(6);
		BigDecimal bd2 = new BigDecimal(iterations).setScale(6);
		BigDecimal bd3 = bd1.divide(bd2).setScale(6);
		System.out.println("Expected Number of Turns Game Lasts is: " + bd3);
	}
}