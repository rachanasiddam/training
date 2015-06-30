package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku {
    
    static void printGrid(int[][] Grid){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(" "+Grid[i][j]);
            }
            System.out.println("");
        }
    }
    
    static boolean checkRowConflict(int[][] Grid, int row){
        boolean[] set={false,false,false,false,false,false,false,false,false};
        for(int i=0;i<9;i++){
            if(Grid[row][i]!=0){
                if(set[Grid[row][i]-1]){
                    return true;
                }
                else{
                    set[Grid[row][i]-1] = true;
                }
            }
        }
        return false;
    }
    
    static boolean checkColumnConflict(int[][] Grid,int column){
        boolean[] set={false,false,false,false,false,false,false,false,false};
        for(int i=0;i<9;i++){
            if(Grid[i][column]!=0){
                if(set[Grid[i][column]-1]){
                    return true;
                }
                else{
                    set[Grid[i][column]-1]=true;
                }
            }
        }
        return false;
    }
    
    static int getStartRow(int row){
        int k;
        if(0<=row && row<=2){
            k=0;
        }
        else if(3<=row && row<=5){
            k=3;
        }
        else{
            k=6;
        }
        return k;
    }
    
    static int getStartColumn(int column){
        int l;
        if(0<=column && column<=2){
            l=0;
        }
        else if(3<=column && column<=5){
            l=3;
        }
        else{
            l=6;
        }
        return l;
    }
    
    static boolean checkSquareConflict(int[][] Grid,int row,int column){
        int k,l;
        k = getStartRow(row);
        l = getStartColumn(column);
        boolean[] set={false,false,false,false,false,false,false,false,false};
        for(int i=k;i<=k+2;i++){
            for(int j=l;j<=l+2;j++){
                if(Grid[i][j]!=0){
                    if(set[Grid[i][j]-1]){
                        return true;
                    }
                    else{
                        set[Grid[i][j]-1] = true;
                    }
                }
            }
        }
        return false;
    }
    
    static boolean solveRecursion(int[][] Grid){
        boolean set = false;
        int i,j = 0;
        for(i=0;i<9;i++){
            for(j=0;j<9;j++){
                if(Grid[i][j]==0){
                    set = true;
                    break;
                }
            }
            if(set){
                break;
            }
        }
        if(!set){
            return true;
        }
        
        for(int k=1;k<=9;k++){
            Grid[i][j]=k;
            if(checkRowConflict(Grid,i) || checkColumnConflict(Grid,j) || checkSquareConflict(Grid,i,j)){
                //do nothing because of conflict
            }
            else{
                if(solveRecursion(Grid)){
                    return true;
                }
            }
        }
        Grid[i][j]=0;
        return false;
    }
    
    
    public static void main(String[] args) throws FileNotFoundException {
        int[][] Grid = new int[9][9];
        File file = new File("sudoku.txt");
        Scanner in = new Scanner(file);
        int sum = 0;
        for(int k=0;k<50;k++){
            in.nextLine();
            for(int i=0;i<9;i++){
                String str = in.nextLine();
                for(int j=0;j<9;j++){
                    Grid[i][j] = Integer.parseInt(""+str.charAt(j));
                }
            }
           if(!solveRecursion(Grid)){
//                System.out.println("No Solution");
           }
           else{
               sum+=Grid[0][0]*100 + Grid[0][1]*10 + Grid[0][2];
//                System.out.println("Done");
//                printGrid(Grid);
           }
        }
        in.close();
        System.out.println(sum);
    }
    
}
