/*
Assignment 3 part  2
Maxime Sotsky 0270251
COMP 1731
2019-04-01
*/
import java.io.*;
import java.util.*;
public class NQueensVerifier
{
    public static final char QUEEN = 'Q'; //representing the queen chess piece

    public char getQueen()//access the piece for test class
    {
        return QUEEN;
    }

    public static final char BLANK = 'B';//representing a blank position

    public char getBlank()//access the blank position for test class
    {
        return BLANK;
    }
    private int size;//size of chess board
    private char[][] board = new char[size][size];//the chess board 2d array

    public NQueensVerifier(int inSize, char inBoard[][])//verifies board configuration
    {
        if (inSize <= 0)
        {
            throw new IllegalArgumentException("Only positive values are permited");//board can't have negative size
        }
        size = inSize;
        board = inBoard;

        if (checkBoardBasics(size, inBoard) == false)
        {
            throw new IllegalArgumentException("The configuration of the board is incorrect");//checks if all board configuration is correct
        }
    }

    private boolean checkBoardBasics(int inSize, char inBoard[][]) throws IllegalArgumentException 
    {
        if (inBoard == null)//board can't be null
        {
            //System.out.println("inBoard == null"); //for testing code
            return false;
        }
        else if(inBoard.length != inBoard[0].length)//board has to have all sides same length (always the case)
        {
            //System.out.println("inBoard.length != inBoard[0].length"); //for testing code
            return false;
        }
        
        for (int row = 0; row < inSize-1; row++)//each row of inBoard has length inSize
        {
            if (inBoard[row].length != inSize)
            {
                System.out.println("inBoard[row].length != inSize");
                return false;
            }
            else{
                continue;
            }
        }
        for(int row =0; row < inSize-1; row++) //illegal char check 'Q' || 'B' only
        {
            for(int col =0; col < inSize-1; col++)
            {
                if ((inBoard[row][col] != QUEEN) && (inBoard[row][col] != BLANK))
                {
                    System.out.println("illegal chars");
                    return false;
                }
                else{
                    continue;
                }
            }
        }
        return true;
    }

    private Boolean oneQueenPerRow()//a row cannot contain more than 1 queen
    { 
        for(int row = 0; row < size; row++)
        {
            int count = 0;
            for(int col = 0; col < size; col++)
            {
                if(board[row][col] == QUEEN)//adds 1 to counter if there's a queen
                {
                    count++;
                    if(count > 1)
                    {
                        return false;
                    }
                }
            }
            if (count == 0 || count > 1)
            {
                return false;
            }
        }
        return true;
    }

    private Boolean oneQueenPerColumn()//a column cannot contain more than 1 queen
    {
        for(int col = 0; col < size; col++)
        {   
            int count = 0;
            for(int row = 0; row < size; row++)
            {
                if(board[row][col] == QUEEN)//adds 1 to counter if position contains a queen
                {
                    count++;
                    if(count > 1)
                    {
                        return false;
                    }
                }
            }
            if (count == 0 || count > 1)
            {
                return false;
            }
        }
        return true;
    }

    private boolean noDiagonalAttacks()//cannot have more than one queen per diagonal
    {
        int countQueens = 0;
        for(int count = 0; count < size;count++)
        {
            countQueens = 0;
            //System.out.println(count); //for testing
            for(int row = (size-1) -count; row > -1; row--)//green (on paper)
            {
                countQueens = 0;
                for(int col = 0; col < size - count; col++,row--)
                {
                    //System.out.println("____");
                    //System.out.println("col "+col);       //for testing
                    //System.out.println("row "+row);
                    if (board[row][col] == QUEEN)
                    {
                        countQueens++;
                        if(countQueens > 1)
                        {
                            return false;
                        }
                    }
                }
                if (countQueens > 1)
                {
                    return false;
                }
            }
        }

        for(int count = 0; count < size;count++)//blue (on paper)
        {
            countQueens = 0;
            //System.out.println(count); //for testing
            for(int row = (size -1); row > count -1;row--)
            {
                countQueens = 0;
                for(int col = count; col < size ;col++,row--)
                {
                    //System.out.println("____");
                    //System.out.println("col "+col);       //for testing
                    //System.out.println("row "+row);
                    if (board[row][col] == QUEEN)
                    {
                        countQueens++;
                        if(countQueens > 1)
                        {
                            return false;
                        }
                    }
                }
                if (countQueens > 1)
                {
                    return false;
                }
            }
        }
        for(int count = 0; count < size;count++)//purple (on paper)
        {
            //System.out.println(count); //for testing
            countQueens = 0;
            for(int row = size-1; row > count -1;row--)
            {
                countQueens = 0;
                for(int col = (size-1) - count;col > -1;col--,row--)
                {
                    //System.out.println("____");
                    //System.out.println("col "+col);       //for testing
                    //System.out.println("row "+row);
                    if (board[row][col] == QUEEN)
                    {
                        countQueens++;
                        if(countQueens > 1)
                        {
                            return false;
                        }
                    }
                }
                if (countQueens > 1)
                {
                    return false;
                }
            }
        }
        for(int count = 0; count < size;count++)//pink (on paper)
        {
            //System.out.println(count); //for testing
            countQueens = 0;
            for(int row = 0; row < size - count;)
            { 
                countQueens = 0;
                for(int col = count; col < size; col++,row++)
                {
                    //System.out.println("____");
                    //System.out.println("col "+col);       //for testing
                    //System.out.println("row "+row);
                    if (board[row][col] == QUEEN)
                    {
                        countQueens++;
                        if(countQueens > 1)
                        {
                            return false;
                        }
                    }
                }
                if (countQueens > 1)
                {
                    return false;
                }
            }
        }
        return true;
    } 
    public Boolean isValidSolution()//for test class testing everything by calling all methods
    {
        if (oneQueenPerRow() == false)
        {
            return false;
        }
        else if(oneQueenPerColumn() == false)
        {
            return false;
        }
        else if(noDiagonalAttacks() == false)
        {
            return false;
        }
        else{
            return true;
        }
        
    }
}