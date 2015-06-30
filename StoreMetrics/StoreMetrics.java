package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StoreMetrics {
	private ArrayList<StoreDataPoint> sm;

	public StoreMetrics(ArrayList<StoreDataPoint> sdp) {
		this.sm = sdp;
	}

	public ArrayList<StoreDataPoint> getSm() {
		return sm;
	}

	public String highestCategorySales(String category, String month) {
		String storeName = new String();
		int maxSales = 0;
		for (StoreDataPoint data : sm) {
			if ((category.equalsIgnoreCase(data.getCategory()))
					&& (month.equalsIgnoreCase(data.getMonth()))) {
				if (data.getUnits() > maxSales) {
					maxSales = data.getUnits();
					storeName = data.getName();
				}
			}
		}
		return storeName;
	}

	public String highestSalesStore(String month) {
		String storeName = new String();
		int maxSales = 0;
		int num = 0;
		int[] sales = new int[4];
		for (StoreDataPoint data : sm) {
			if (month.equalsIgnoreCase(data.getMonth())) {
				if ("reliance".equalsIgnoreCase(data.getName())) {
					sales[0] = sales[0] + data.getUnits();
				}
				if ("heritage".equalsIgnoreCase(data.getName())) {
					sales[1] = sales[1] + data.getUnits();
				}
				if ("more".equalsIgnoreCase(data.getName())) {
					sales[2] = sales[2] + data.getUnits();
				}
				if ("walmart".equalsIgnoreCase(data.getName())) {
					sales[3] = sales[3] + data.getUnits();
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			if (sales[i] > maxSales) {
				maxSales = sales[i];
				num = i;
			}
		}
		switch (num) {
		case 0:
			storeName = "Reliance";
			break;
		case 1:
			storeName = "Heritage";
			break;
		case 2:
			storeName = "More";
			break;
		case 3:
			storeName = "Walmart";
			break;
		}
		return storeName;
	}

	public double totalMonthlyAverage(String category, String month) {
		double average = 0;
		int count = 0;
		for (StoreDataPoint data : sm) {
			if ((category.equalsIgnoreCase(data.getCategory()))
					&& (month.equalsIgnoreCase(data.getMonth()))) {
				average = average + data.getUnits();
				count++;
			}
		}
		if (count != 0)
			average = average / count;
		return average;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<StoreDataPoint> sdp = new ArrayList<StoreDataPoint>();
		try (BufferedReader br = new BufferedReader(new FileReader(
				"store_data.csv"));) {
			String line = br.readLine();
			String splitBy = ",";
			while ((line = br.readLine()) != null) {
				String[] data = line.split(splitBy);
				StoreDataPoint newSDP = new StoreDataPoint(data[0], data[1],
						data[2], Integer.parseInt(data[3]));
				sdp.add(newSDP);
			}
			// StoreMetrics sm = new StoreMetrics(sdp);
			// System.out.println(sm.highestSalesStore("feb"));
			// System.out.println(sm.totalMonthlyAverage("noodles","feb"));
		} catch (FileNotFoundException e) {
			System.err.println("Can't find the file ");
			System.exit(1);
		}
	}
}