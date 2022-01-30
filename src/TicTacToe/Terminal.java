package TicTacToe;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Terminal {
    int x;
    int y;
    public Terminal(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args)  { // For testing the TicTacToe class // not used

        Scanner scan = new Scanner(System.in);

        System.out.println("lenght to win: ");
        int lenght = scan.nextInt();

        System.out.println("X of borad: ");
        int x = scan.nextInt();

        System.out.println("Y of borad: ");
        int y = scan.nextInt();


        TicTacToe borad = new TicTacToe(new HashMap(),x,y,lenght);
        borad.setTurnX();

        Terminal ter = new Terminal(x,y);



        while(true){
            while(true) {
                ter.printArr(borad.getGrid());
                System.out.println("X Turn");

                System.out.println("X:");
                x = scan.nextInt();
                System.out.println("Y:");
                y = scan.nextInt();

                if(borad.addX(x, y)){
                    break;
                }
                System.out.println("\n\n\n\n\nTry Again invaled:");
            }
            if (borad.checkWin('x')) {
                System.out.println("X winner");
                break;
            }
            while(true) {
                ter.printArr(borad.getGrid());
                System.out.println("O  Turn");

                System.out.println("X:");
                x = scan.nextInt();
                System.out.println("Y:");
                y = scan.nextInt();

                if(borad.addO(x, y)){
                    break;
                }
                System.out.println("\n\n\n\n\nTry Again invaled:");
            }
            if(borad.checkWin('o')){
                System.out.println("O winner");
                break;
            }
        }
        ter.printArr(borad.getGrid());
    }

    public void printArr(char[][] arr){
        int i = 1;
        for(int x = 0 ; x < this.x ; x++){
            for(int y = 0 ; y < this.y ; y++){
                if(arr[x][y] ==' '){
                    System.out.print(i + "\t");
                } else {
                    System.out.print(arr[x][y] + "\t");
                }
                i++;
            }
            System.out.print("\n");
        }

    }

}
