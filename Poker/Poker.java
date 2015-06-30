package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Poker {
	/**
	 * Created by RachanaSiddam on 19-Jun-2015
	 * 
	 */

	public static int getCardValue(String card) {
		char value = card.charAt(0);
		if (value == '2')
			return 2;
		if (value == '3')
			return 3;
		if (value == '4')
			return 4;
		if (value == '5')
			return 5;
		if (value == '6')
			return 6;
		if (value == '7')
			return 7;
		if (value == '8')
			return 8;
		if (value == '9')
			return 9;
		if (value == 'T')
			return 10;
		if (value == 'J')
			return 11;
		if (value == 'Q')
			return 12;
		if (value == 'K')
			return 13;
		if (value == 'A')
			return 14;
		return 0;
	}

	public static int getCardSuit(String card) {
		char suit = card.charAt(1);
		if (suit == 'D')
			return 4;
		if (suit == 'C')
			return 3;
		if (suit == 'H')
			return 2;
		if (suit == 'S')
			return 1;
		else
			return 0;
	}

	public static String[] sort(String[] player) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4 - i; j++) {
				if (getCardValue(player[j]) > getCardValue(player[j + 1])) {
					String temp = player[j];
					player[j] = player[j + 1];
					player[j + 1] = temp;
				}
			}
		}
		return player;
	}

	public static boolean playerOneWins(String[] data1, String[] data2) {
		String[] player1 = sort(data1);
		String[] player2 = sort(data2);

		if (didPlayerOneWin(player1, player2))
			return true;
		else
			return false;
	}

	public static boolean didPlayerOneWin(String[] player1, String[] player2) {
		if (isRoyalFlush(player1) != isRoyalFlush(player2))
			return isRoyalFlush(player1) > isRoyalFlush(player2);
		if (isStraightFlush(player1) != isStraightFlush(player2))
			return isStraightFlush(player1) > isStraightFlush(player2);
		if (isFourOfAKind(player1) != isFourOfAKind(player2))
			return isFourOfAKind(player1) > isFourOfAKind(player2);
		if (isFullHouse(player1, 1) != isFullHouse(player2, 1))
			return isFullHouse(player1, 1) > isFullHouse(player2, 1);
		if (isFullHouse(player1, 2) != isFullHouse(player2, 2))
			return isFullHouse(player1, 2) > isFullHouse(player2, 2);
		if (isFlush(player1) != isFlush(player2))
			return isFlush(player1) > isFlush(player2);
		if (isStraight(player1) != isStraight(player2))
			return isStraight(player1) > isStraight(player2);
		if (isThreeOfAKind(player1) != isThreeOfAKind(player2))
			return isThreeOfAKind(player1) > isThreeOfAKind(player2);
		if (isTwoPairs(player1, 1) != isTwoPairs(player2, 1))
			return isTwoPairs(player1, 1) > isTwoPairs(player2, 1);
		if (isTwoPairs(player1, 2) != isTwoPairs(player2, 2))
			return isTwoPairs(player1, 2) > isTwoPairs(player2, 2);
		if (isOnePair(player1) != isOnePair(player2))
			return isOnePair(player1) > isOnePair(player2);
		if (isHighCard(player1, 0) != isHighCard(player2, 0))
			return isHighCard(player1, 0) > isHighCard(player2, 0);
		if (isHighCard(player1, 1) != isHighCard(player2, 1))
			return isHighCard(player1, 1) > isHighCard(player2, 1);
		if (isHighCard(player1, 2) != isHighCard(player2, 2))
			return isHighCard(player1, 2) > isHighCard(player2, 2);
		if (isHighCard(player1, 3) != isHighCard(player2, 3))
			return isHighCard(player1, 3) > isHighCard(player2, 3);
		if (isHighCard(player1, 4) != isHighCard(player2, 4))
			return isHighCard(player1, 4) > isHighCard(player2, 4);

		return false;
	}

	public static int isRoyalFlush(String[] player) {
		if (isStraightFlush(player) > 0 && getCardValue(player[4]) == 14)
			return 14;
		else
			return -1;
	}

	public static int isStraightFlush(String[] player) {
		if (isFlush(player) > 0 && isStraight(player) > 0)
			return getCardValue(player[4]);
		else
			return -1;
	}

	public static int isFourOfAKind(String[] player) {
		for (int i = 4; i > 2; i--) {
			if ((getCardValue(player[i]) == getCardValue(player[i - 1]))
					&& (getCardValue(player[i - 1]) == getCardValue(player[i - 2]))
					&& (getCardValue(player[i - 2]) == getCardValue(player[i - 3])))
				return getCardValue(player[i]);
		}
		return -1;

	}

	public static int isFullHouse(String[] player, int set) {
		if (getCardValue(player[4]) == getCardValue(player[3])
				&& getCardValue(player[3]) == getCardValue(player[2])
				&& getCardValue(player[1]) == getCardValue(player[0])) {
			if (set == 1)
				return getCardValue(player[4]);
			if (set == 2)
				return getCardValue(player[0]);
		}
		if (getCardValue(player[4]) == getCardValue(player[3])
				&& getCardValue(player[2]) == getCardValue(player[1])
				&& getCardValue(player[1]) == getCardValue(player[0])) {
			if (set == 1)
				return getCardValue(player[0]);
			if (set == 2)
				return getCardValue(player[4]);
		}
		return -1;
	}

	public static int isFlush(String[] player) {
		for (int i = 4; i > 0; i--) {
			if (getCardSuit(player[i]) != getCardSuit(player[i - 1])) {
				return -1;
			}
		}
		return getCardValue(player[4]);
	}

	public static int isStraight(String[] player) {
		for (int i = 0; i < 4; i++) {
			if (getCardValue(player[i]) != (getCardValue(player[i + 1]) - 1)) {
				return -1;
			}
		}
		return getCardValue(player[4]);
	}

	public static int isThreeOfAKind(String[] player) {
		for (int i = 4; i > 1; i--) {
			if ((getCardValue(player[i]) == getCardValue(player[i - 1]))
					&& (getCardValue(player[i - 1]) == getCardValue(player[i - 2])))
				return getCardValue(player[i]);
		}
		return -1;
	}

	public static int isTwoPairs(String[] player, int set) {
		if (getCardValue(player[4]) == getCardValue(player[3])
				&& getCardValue(player[2]) == getCardValue(player[1])) {
			if (set == 1)
				return getCardValue(player[4]);
			if (set == 2)
				return getCardValue(player[1]);
		}
		if (getCardValue(player[4]) == getCardValue(player[3])
				&& getCardValue(player[1]) == getCardValue(player[0])) {
			if (set == 1)
				return getCardValue(player[4]);
			if (set == 2)
				return getCardValue(player[0]);
		}
		if (getCardValue(player[3]) == getCardValue(player[2])
				&& getCardValue(player[1]) == getCardValue(player[0])) {
			if (set == 1)
				return getCardValue(player[3]);
			if (set == 2)
				return getCardValue(player[0]);
		}
		return -1;
	}

	public static int isOnePair(String[] player) {
		for (int i = 4; i > 0; i--) {
			if (getCardValue(player[i]) == getCardValue(player[i - 1]))
				return getCardValue(player[i]);
		}
		return -1;
	}

	public static int isHighCard(String[] player, int cardset) {
		return getCardValue(player[4 - cardset]);
	}

	public static void main(String[] args) throws IOException {

		try (BufferedReader br = new BufferedReader(new FileReader("poker.txt"));) {
			String line;
			String splitBy = " ";
			String[] data1 = new String[5];
			String[] data2 = new String[5];
			int result = 0;
			
			while ((line = br.readLine()) != null) {
				String[] data = line.split(splitBy);
				System.arraycopy(data, 0, data1, 0, 5);
				System.arraycopy(data, 5, data2, 0, 5);
				if (playerOneWins(data1, data2)) {
					result = result + 1;
					data1 = sort(data1);
					data2 = sort(data2);
				}
			}
			System.out.println("Player1 won: " + result + " games");

		} catch (FileNotFoundException e) {
			System.err.println("Can't find the file ");
			System.exit(1);
		}
	}
}