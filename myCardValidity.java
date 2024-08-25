/* 
Author: Aubrie McIntyre
Date: 8/24/2024
Description: This program will accept user input via a card number and determine if the number provided is a valid card number.
This program only validates 16 digit card numbers due to ongoing technical difficulties. I apologize for any inconveniences.
*/
import java.util.Scanner;

public class myCardValidity {
    /** Tests the answer of all validity checks */
    public static String isValid(long num) {
        String answer;
        if (firstDigitValid(num)) {
            if (isLengthValid(num)) {
                if (isMathCorrect(num)) {
                    answer = "Card is valid";
                } else {
                    answer = "Card is invalid";
                }
            } else {
                answer = "Card is invalid";
            }
        } else {
            answer = "Card is invalid";
        }
        return answer;
    }
    /** Pulls digits from certain indexes of the entered number and returns the digit after converting it into a string */
    public static long intAt(long num, int index) {
	    String s = Long.toString(num);
	    long digit = Integer.parseInt(s.substring(index, index+1));
	    return digit;
    }
    /** Return true if first number of number is either 4, 5, 37, or 6 */
    public static boolean firstDigitValid(long num) {
        if (intAt(num, 0) == 3) {
            return (intAt(num, 1) == 7);
        } else {
            return intAt(num, 0) == 4 || intAt(num, 0) == 5 || intAt(num, 0) == 6;
        }
    }
    /** Determines whether the card number length is valid or not */
    public static boolean isLengthValid(long num) {
        String s = Long.toString(num);
        int cardNumLength = s.length();
        return cardNumLength == 16;
    }
    /** Only finds card number length without validating */
    public static int cardNumberLength(long num) {
        String s = Long.toString(num);
        int cardNumberLength = s.length();
        return cardNumberLength;
    }
    /** Stores the sum of every second digit from the right multiplied by itself */
    public static long sumOfMultiedDigits(long num) {
        long sum = 0;
        long multiplied;
        for (int i = 0; i < 16; i ++) {
            if (i == 0 || i % 2 == 0) {
                multiplied = intAt(num, i) * 2;
                if (multiplied > 9) {
                    for (int j = 0; j < 2; j++) {
                        sum += intAt(multiplied, j);
                    }
                    multiplied = 0;
                }
                sum += multiplied;
            }
        }
        return sum;
    }
    /** Stores the sum of every odd digit from the right of the card number */
    public static long sumOfOddDigits(long num) {
        long sum = 0;
        for (int i = 0; i < 16; i ++) {
            if (i % 2 != 0) {
                sum += intAt(num, i);
            }
        }
        return sum;
    }
    /** Stores the equation that determines if the math adds up for sumOfMultiedDigits and sumOfOddDigits */
    public static boolean isMathCorrect(long num) {
        long result = sumOfMultiedDigits(num) + sumOfOddDigits(num);
        return result % 10 == 0;
    }
    
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);// Create a Scanner object
        System.out.println("Enter card number as a long integer: ");// Prompts user input
        long cardNumber = myObj.nextLong();// Read user input
        System.out.println(isValid(cardNumber));// Prints validity
    }
}