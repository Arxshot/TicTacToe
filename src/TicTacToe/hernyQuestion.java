package TicTacToe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class hernyQuestion {

    public static void main(String[] args)  {
        int n = 5;
        int s = 4;
        ArrayList<int[]> numbers = getNum(n,s);
        for (int[] arr : numbers) {
            for(int i = 0;i < arr.length;i++){
                System.out.print(arr[i]+",");
            }
            System.out.println();
        }
        System.out.println("length: " + numbers.size());
    }

    public static ArrayList<int[]> getNum(int n , int s)  {
        ArrayList<int[]> numbers = new ArrayList<int[]>();
        ArrayList<int[]> num = new ArrayList<int[]>();
        int[] arr = new int[s];
        numbers.addAll(getNum2(n,s ,0, arr.clone()));
        return numbers;
    }

    public static ArrayList<int[]> getNum2(int n ,int s ,int d, int[] arr) {
        if (d == s) {
            return null;
        }
        int t = 0;
        for (int i = 0; i < s;i++) {
            t += arr[i];
        }
        ArrayList<int[]> numbers = new ArrayList<int[]>();
        if (d == s-1) {
            arr[s-1] = n-t;
            numbers.add(arr);
            return numbers;
        }
        for (int x = 0; x <= n - t; x++) {
            arr[d] = x;
            numbers.addAll(getNum2(n,s,d+1,arr.clone()));
        }
        return numbers;
    }

}