package test;

import java.util.ArrayList;
//import java.util.Scanner;
public final class ZNumber{
    
    private double zNum;
    private String zStr;

    public ZNumber(char...digits){
        StringBuilder sb = new StringBuilder(); 
        for(char value:digits){
            ZDigit zd = new ZDigit(value);
            sb.append(value);
        }
        this.zStr = sb.toString();
        this.zNum = toDecimal();
    }
    public ZNumber(String s){
        StringBuilder sb = new StringBuilder(); 
        for(int i = 0; i< s.length(); i++){
            ZDigit zd = new ZDigit(s.charAt(i));
            sb.append(s.charAt(i));
        }
        this.zStr = sb.toString();
        this.zNum = toDecimal();
    }

    public ZNumber(ArrayList<Character> li){
        StringBuilder sb = new StringBuilder(); 
        for (Character value: li){
            ZDigit zd = new ZDigit(value);
            sb.append(value);
        }
        this.zStr = sb.toString();
        this.zNum = toDecimal();
    }

    @Override
    public String toString(){
        return zStr;
    }

    public double toDecimal(){ 
        zNum = 0;
        int count = 0;
        for(int i = zStr.length()-1; i > -1; i--){
            char c = zStr.charAt(i);
            int n = (int) c - 64;
            if(n<0) n = 0;
            zNum = zNum + (n* Math.pow(27,count));
            count++;
        }
        //System.out.println("Decimal value is: "+zNum);
        return zNum;
    }

    public ZDigit[] getDigits(){
       ZDigit[] zd = new ZDigit[zStr.length()];
       for(int i = 0; i< zStr.length(); i++){
            zd[i] = new ZDigit(zStr.charAt(i));
        }
        return zd;
    }

    public static void main(String[] args) {
        ZNumber zn = new ZNumber('A','B','C');
        System.out.println(zn.toDecimal());

        ZNumber zn1 = new ZNumber("ABCDEF");
        System.out.println(zn1);

        ArrayList<Character> li = new ArrayList<Character>();
        //ArrayList<Character> li = new ArrayList<Character>(Arrays.asList('A','B'));
        li.add('A');
        li.add('B');
        System.out.println(li);

        ZNumber zn2 = new ZNumber(li);
        System.out.println(zn2);
        
        ZDigit[] zd = zn2.getDigits();
        System.out.println(zd[0].getZDigit());       
	}
}