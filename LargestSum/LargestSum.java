package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LargestSum {

public static void main(String[] args) {

    File file = new File("triangle.txt");

    try {

        Scanner sc = new Scanner(file);
        int[][] data = new int[100][];
        int i, j;
        for(i = 0; i < 100; i++){
            data[i] = new int[i+1];
            for(j = 0; j < (i+1); j++)
            {
                data[i][j] = sc.nextInt();
            }
        }
        
        for(i = 98; i > -1; i--)
        	for(j = 0; j <(i+1); j++)
        		data[i][j] = data[i][j]+ Math.max(data[i+1][j],data[i+1][j+1]);

        System.out.println("Maximum total from top to bottom is: "+data[0][0]);
        sc.close();
    } 
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
 }
}