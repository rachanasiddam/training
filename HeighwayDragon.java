package test;

public class HeighwayDragon {
	/**
	 * Created by RachanaSiddam on 20-Jun-2015
	 */

	public static int[] dragonPosition(double steps) {
		int[] c = new int[2];
		c[0] = 0;
		c[1] = 1;
		double length = 1;
		while (length < steps) {
			int temp;
			temp = c[0] + c[1];
			c[1] = c[1] - c[0];
			c[0] = temp;
			length = length * 2;
		}
		if (length == steps)
			return c;
		double m = length - steps;
		int[] c2 = dragonPosition(m);
		c[0] = c[0] - c2[1];
		c[1] = c[1] + c2[0];
		return c;
	}

	public static void main(String[] args) {
		int[] coordinates = dragonPosition(Math.pow(10,12));
		System.out.println("Coordinates are : (" + coordinates[0] + "," + coordinates[1] + ")");
	}
}