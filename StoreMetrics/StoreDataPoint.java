package test;

public class StoreDataPoint {
	private String name;
	private String category;
	private String month;
	private int units;
	
	public StoreDataPoint(String name, String category, String month, int units){
		this.name = name;
		this.category = category; 
		this.month = month;
		this.units = units;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getMonth() {
		return month;
	}
	
	public int getUnits() {
		return units;
	}
}