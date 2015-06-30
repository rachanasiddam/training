package test;

public final class ZDigit{
	
	private final char character;

	public ZDigit(char c){

		if (isZDigit(c)){
			//System.out.println("Entered digit is a ZDigit: "+ c);
			this.character = c;
		} 
	    else{
     	 	throw new IllegalArgumentException("Entered value is not a ZDigit");
     	}
	}
    public char getZDigit() {
        return character;
    }

    public boolean isZDigit(char c){
    	int n = (int) c;
    	if ((n == 48) || (n > 64 && n < 91)){
			return true;
		} 
		else{
     	 	return false;
     	}
    }
	public static void main(String[] args) {
		//Scanner in = new Scanner(System.in);
		//char c  = in.next(".").charAt(0);;
        ZDigit zd = new ZDigit('A');
        System.out.println(zd);
	}
}