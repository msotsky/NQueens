/*
Assignment 3 part  2
Maxime Sotsky 0270251
COMP 1731
2019-04-01
*/
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.File;
public class TestNQueensVerifier
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the full path name of the game board file.");//configuration

        String pathname = sc.nextLine();//getting path name
        sc.close();

        //System.out.println(pathname);
        
        File boardfile = new File(pathname);//File object containing .txt (board)
        
        try
        {
            Scanner sc2 = new Scanner(boardfile);
            int size = sc2.nextInt();//finding the top number that is the size of the board
            char[][] chessBoard = new char[size][size];//creating the board 2d array

            for(int i = 0; i < size; i++)//filling the board
            {
                String r = sc2.next();
                for (int j = 0; j < size; j++)
                {
                    //System.out.println(chessBoard[i][j]); //for testing
                    chessBoard[i][j] = r.charAt(j);
                }
            }
            sc2.close();
            NQueensVerifier newgame = new NQueensVerifier(size, chessBoard); //creating NQueens object
            Boolean result = newgame.isValidSolution(); //checking if board is valid

            if (result == false)
            {
                System.out.println("Board configuration are invalid");//invalid nqueens board
            }
            else if (result == true)
            {
                System.out.println("Board configuraton is valid");//valid nqueens board
            }
        }
        catch(FileNotFoundException exception)
        {
           System.out.println("Sorry, that file cannot be found.");//incase user file is incorrect or DNE
        }
    }
}