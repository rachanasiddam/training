package test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SelectiveAmnesia {
	/**
	 * Created by RachanaSiddam on 19-Jun-2015
	 */
	private static int updateLarry(String type, List<Integer> list,
			int calledNumber, int score) {
		if (list.contains(calledNumber)) {
			score++;
			int pos = 0;
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j) == calledNumber)
					pos = j;
			}
			Collections.rotate(list.subList(pos, list.size()), -1);
		} else if (list.size() < 5) {
			list.add(calledNumber);
		} else {
			list.remove(0);
			list.add(calledNumber);
		}
		return score;
	}
	
	private static int updateRobin(String type, List<Integer> list,
			int calledNumber, int score) {
		if (list.contains(calledNumber))
			score++;
		else if (list.size() < 5) {
			list.add(calledNumber);
		} else {
			list.remove(0);
			list.add(calledNumber);
		}
		return score;
	}

	public static void main(String[] args) {
		List<Integer> larryMemory = new LinkedList<Integer>();
		List<Integer> robinMemory = new LinkedList<Integer>();

		int min = 1, max = 10, ans = 0, loop = 0;

		for (loop = 1; loop < 100000001; loop++) {
			
			int score1 = 0, score2 = 0;
			larryMemory.clear();
			robinMemory.clear();
			
			for (int turn = 1; turn < 51; turn++) {
				int calledNumber = min
						+ (int) (Math.random() * ((max - min) + 1));
				score1 = updateLarry("larry", larryMemory, calledNumber, score1);
				score2 = updateRobin("robin", robinMemory, calledNumber, score2);
				/*System.out.println("Turn: "+ turn+ " Called number is " + calledNumber);
				System.out.print("Larry's Memory: ");
				int j = 0;
				for (j = 0; j < larryMemory.size(); j++) {
					System.out.print(larryMemory.get(j) + " ");
				}
				System.out.println("");
				System.out.print("Robin's Memory: ");
				for (j = 0; j < robinMemory.size(); j++) {
					System.out.print(robinMemory.get(j) + " ");
				}
				System.out.println("");
				System.out.println("Larry Score: " + score1);
				System.out.println("Robin Score: " + score2);*/
			}
			if (score1 > score2)
				ans = score1 - score2 + ans;
			else
				ans = score2 - score1 + ans;
			//System.out.println("loop: "+ loop + " ans: 1.76868663 " + ans);
		}
		BigDecimal bd1 = new BigDecimal(ans).setScale(8);
		BigDecimal bd2 = new BigDecimal(loop-1).setScale(8);
		BigDecimal bd3 = bd1.divide(bd2).setScale(8);
		System.out.println("Expected Value of |L-R| is " + bd3);
	}
}