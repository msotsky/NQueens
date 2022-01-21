/*
Assignment 3 part  1
Maxime Sotsky 0270251
COMP 1731
2019-03-30
*/
import java.io.*;
import java.util.*; //potential usefull stuff
public class HotMail
{
    public static void main(String[] args)
    {
        
        System.out.println("TEST: person@hopper.mta.ca (true)");
        System.out.println(isValidEmail("person@hopper.mta.ca"));//true

        System.out.println("TEST: ME.2@yOU.3 (true)");
        System.out.println(isValidEmail("ME.2@yOU.3"));//true

        System.out.println("TEST: dil@bert.com. (false)");       
        System.out.println(isValidEmail("dil@bert.com."));//false

        System.out.println("TEST: first.last@net (false)");   
        System.out.println(isValidEmail("first.last@net"));//false 

        System.out.println("TEST: .me@gmail.gee (false)");     
        System.out.println(isValidEmail(".me@gmail.gee"));//false

        System.out.println("TEST: me..you@jotmail.org (false)");   
        System.out.println(isValidEmail("me..you@jotmail.org"));//false 

        System.out.println("TEST: i.J.k@W.X.y.z (true)");       
        System.out.println(isValidEmail("i.J.k@W.X.y.z"));//true 

        System.out.println("TEST: john@DOE@gmail.com (false)");       
        System.out.println(isValidEmail("john@DOE@gmail.com"));//false  

        System.out.println("TEST: surfing.safari.info (false)");
        System.out.println(isValidEmail("surfing.safari.info"));//false

        System.out.println("TEST: bill.@gates.tv (false)");
        System.out.println(isValidEmail("bill.@gates.tv"));//false   

        System.out.println("TEST: warren@.buffett.money (false)");
        System.out.println(isValidEmail("warren@.buffett.money"));//false

        System.out.println("TEST: mark zuckerberg@facebook.com (false)");
        System.out.println(isValidEmail("mark zuckerberg@facebook.com"));//false  

        System.out.println("TEST: bill$$$gates@microsoft.com (false)");  
        System.out.println(isValidEmail("bill$$$gates@microsoft.com"));//false
        
        System.out.println("TEST: mxm2000@hotmail.ca (true)");
        System.out.println(isValidEmail("mxm2000@hotmail.ca"));//true testing my private email

        System.out.println("TEST: msotsky@mta.ca (true)");
        System.out.println(isValidEmail("msotsky@mta.ca"));//true testiing my mta email
    }

    public static boolean isValidEmail(String test)//testing if email is valid
    {
        boolean isValid = true;
        int extract = test.indexOf(".");
        String theSub;
        theSub = test.substring(0 , extract);
        if (periodPlacement(test) == false)//checks if periods are places correctly
        {
            return false;
        }
        else if ((countAtSymbol(test) > 1) || (countAtSymbol(test) == 0))//checks if the email has 1 at symbol
        {
            return false;
        }
        int extract2 = test.indexOf("@");
        String theSub2;
        theSub2 = test.substring(extract2, test.length());
        if (checkForEnameAfterAt(theSub2) == 0)
        {
            return false;
        }
        else if (eName(theSub,test) == false)
        {
            return false;
        }
        else{
            return true;
        }
    }
    //stepwise refinement
    public static int countAtSymbol(String test)//counts amt of @ symbols
    {
        int count = 0;
        for (int i = 0; i != (test.length()); i++)
        {
            if (test.charAt(i) == '@')
            {
                count++;
            }
        }
        return count;
    }
    public static int checkForEnameAfterAt(String test)//checks if theres an email after @
    {
        int count = 0;
        char[] extCharArr = test.toCharArray();
        for(int i = 0; i != extCharArr.length; i++)
        {
            if (extCharArr[i] == '.')
            {
                count++;
            }
            else{
                continue;
            }
        }
        return count;
    }
    public static boolean periodPlacement(String test)//checks the period placements are in the valid positions
    {
        boolean valid = true;
        char[] entireEmail = test.toCharArray();
        for(int i = 0; i != test.length();i++)//entire email scan
        {
            if (i + 1 == test.length())
            {
                return valid;
            }
            if (entireEmail[i] == '.' && entireEmail[i+1] == '.')//double period possibility
            {
                valid = false;
                return valid;
            }
            else if ((entireEmail[i] == '.' && entireEmail[i +1] == '@') || (entireEmail[i] == '@' &  entireEmail[i+1] == '.')) //period next to at possiblity
            {
                valid = false;
                return valid;
            }
        }
        return valid;
    }
    public static boolean eName(String test, String test2)//checks entire email and then checks ename if valid
    {
        //scannes for illegal characters in string seperated by periods
        //arr contains illegal characters for a valid email
        //arrForEname contains illegal characters for an ename specifically
        char[] extCharArr = test.toCharArray();
        char[] entireEmail = test2.toCharArray();
        char arr[] = {'(',  ')',    ',',    '_',    '-',    '*',    '&',    '^',    '%',    '$',    '#',    '!',    '>',    '?',    '`',    '~',    '<',    ',',    '}',    '{',    ']',    '=',    '+',    ':',    '/',    '|',    ';',    '"', ' '};
        char arrForEname[] = {'(',  ')',    ',',    '_',    '-',    '*',    '&',    '^',    '%',    '$',    '#',    '!',    '>',    '?',    '`',    '~',    '<',    ',',    '}',    '{',    ']',    '=',    '+',    ':',    '/',    '|',    ';',    '"', ' ', '.'};
        boolean valid = true;
        if ((entireEmail[entireEmail.length -1] == '.') || (entireEmail[0] == '.') || (entireEmail[entireEmail.length -1] == '@') || (entireEmail[0] == '@'))
        {
            return false;
        }
        for(int i = 0; i != test2.length();i++)//entire email scan using illegal char array
        {
            for(int j = 0; j != entireEmail.length;j++)
            {
                if (entireEmail[i] == arr[j])
                {
                    valid = false;
                }
                else
                {
                    continue;
                }
            }
        }
        if (valid == true)
        {
            for(int i = 0; i != test.length(); i++)//scans ename looking for illegal chars using illegal char array
            {
                for(int j = 0; j != arrForEname.length;j++)
                {
                    if (extCharArr[i] == arrForEname[j])
                    {
                        valid = false;
                    }
                    else
                    {
                        continue;
                    }
                }
            }
        }
        return valid;
    }
}