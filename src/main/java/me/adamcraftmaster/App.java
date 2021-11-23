package me.adamcraftmaster;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class App 
{
    public static void main( String[] args )
    {
        int guesses = 0;
        boolean correct = false;

        //recive word from user
        System.out.println("Hello! Please give the keyboard to the person who wishes to input the guessed word.");
        System.out.println("Hello executioner! Please input a word to be guessed.");
        Scanner input = new Scanner(System.in);
        //convert the word to a char array
        char[] word = input.nextLine().toCharArray();

        //create a char array to hold the correct guesses
        char[] knownletters = new char[word.length];
        for (int i = 0; i < word.length; i++) {
            //set all the letters to _
            knownletters[i] = '_';
        }

        //create a char array to hold the incorrect guesses
        char[] incorrectletters = new char[6];
        int incorrectlettersLength = 0;

        System.out.println("Please give the keyboard to the person who wishes to guess the correct word.");
        while(guesses <= 5 && !correct) {
            //clear the screen
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            //print the hangman
            printHangman(guesses);

            //print the correctly guesed letters
            System.out.println("Correct letters: " );
            for(int i = 0; i < word.length; i++) {
                if(knownletters[i] == word[i]) {
                    System.out.print(word[i]);
                } else {
                    System.out.print(knownletters[i]);
                }
            }
            System.out.println("\n");

            //print the incorrectly guessed letters
            System.out.println("Incorrect letters: ");
            for(int i = 0; i < incorrectlettersLength; i++) {
                System.out.print(incorrectletters[i]);
            }
            System.out.println("\n");

            //print the number of guesses left
            System.out.println("Guesses left: " + (5 - guesses));
            System.out.println("Please input a letter.");
            //convert the first letter of the guess to a char
            String guess = input.next();
            char letter = guess.charAt(0);
            boolean found = false;

            System.out.println(guess);
            System.out.println(new String(word));
            if(guess == new String(word)) {
                correct = true;
                System.out.println("Correct!");
                System.out.println("You have guessed the word! The word was " + new String(word));
                break;
            }

            //check if the letter is in the word
            for (int i = 0; i < word.length; i++) {
                if (word[i] == letter) {
                    knownletters[i] = letter;
                    found = true;
                }
            }
            if (found) {
                System.out.println("Correct!");
            } else {
                //add the letter to the incorrect letters array
                incorrectletters[incorrectlettersLength] = letter;
                System.out.println("Incorrect!");
                incorrectlettersLength++;
                guesses++;
            }

            //if the word fails to be guessed, print the word
            if (guesses >= 5) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\n");
                printHangman(guesses);
                System.out.println("You have failed to guess the word. The word was " + new String(word));
            }

            //if the word is guessed, print the word
            if (new String(knownletters).equals(new String(word))) {
                System.out.println("You have guessed the word! The word was " + new String(word));
                correct = true;
            }
        }
        //no more input needed
        input.close();
    }

    
    /** 
     * print the hangman based on how many guesses have been made
     * @param  guesses how many guesses have been made
     */
    public static void printHangman(int guesses) {
        //nothing
        if (guesses == 0) {
            System.out.println("  _________");
            System.out.println("  |       |");
            System.out.println("  |");
            System.out.println("  |");
        }
        //head
        if (guesses == 1) {
            System.out.println("  _________");
            System.out.println("  |       |");
            System.out.println("  |       O");
            System.out.println("  |");
        }
        //body
        if (guesses == 2) {
            System.out.println("  _________");
            System.out.println("  |       |");
            System.out.println("  |       O");
            System.out.println("  |       |");
            System.out.println("  |       |");
        }
        //arms
        if (guesses == 3) {
            System.out.println("  _________");
            System.out.println("  |       |");
            System.out.println("  |       O");
            System.out.println("  |      /|\\");
            System.out.println("  |       |");
            System.out.println("  |       |");
        }
        //left leg
        if (guesses == 4) {
            System.out.println("  _________");
            System.out.println("  |       |");
            System.out.println("  |       O");
            System.out.println("  |      /|\\");
            System.out.println("  |       |");
            System.out.println("  |      /");
        }
        //right leg 
        if (guesses >= 5) {
            System.out.println("  _________");
            System.out.println("  |       |");
            System.out.println("  |       O");
            System.out.println("  |      /|\\");
            System.out.println("  |       |");
            System.out.println("  |      / \\");
        }
    }

}
