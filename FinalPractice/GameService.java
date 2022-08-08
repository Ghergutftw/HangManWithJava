package com.advanced.FinalPractice;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.*;

public class GameService {

    private static final String WORDS_PATH = "complete-java-course-master/src/com/advanced/FinalPractice/words.txt";


    public void start() throws FileNotFoundException {
        Scanner input= new Scanner(System.in);
        String randomWord=getRandomWord();

        char[] randomWordArray= randomWord.toCharArray();
        char[] quessingArray= new char[randomWord.length()];
        Arrays.fill(quessingArray,'_');

        int numberOfChances=randomWord.length();

        System.out.println("Starting the game");
        System.out.println("The length of the word and the number of chances: "+ numberOfChances);
        System.out.println("the word: "+ new String(quessingArray));
        while(true){
            System.out.println("-------------------------");
            System.out.println("Please type in a letter");
            String inputLine=input.nextLine().toLowerCase();
            char letter=inputLine.charAt(0);

            boolean isLetterCorrect=false;
            for (int i = 0; i < randomWord.length(); i++) {
                if(letter==randomWordArray[i]){
                    quessingArray[i]=letter;
                    isLetterCorrect=true;

                }
            }


            System.out.println("the word: "+ new String(quessingArray));

            if(isLetterCorrect){
                System.out.println("It was a good guess");
                if(isGameFinished(quessingArray)){
                    break;
                }

            }else {
                numberOfChances--;
                if(numberOfChances==0){
                    System.out.println("GG EZ YOU LOST");
                    System.out.println("The word was " + randomWord);
                    break;
                }
                System.out.println("Not a good letter");
                System.out.println("Remaining chances are : "+ numberOfChances);
            }

        }

    }

    private boolean isGameFinished(char[] quessingArray) {
        for (int i = 0; i < quessingArray.length; i++) {
            if(quessingArray[i]=='_'){
                return false;
            }
        }

        System.out.println("You Win");
        System.out.println("The word was " +new String(quessingArray));
        return true;
    }

    private String getRandomWord() throws FileNotFoundException {
        List<String> words=getWords();
        Random randomNumber=new Random();
        int randomIndes= randomNumber.nextInt(words.size()+1);
        return words.get(randomIndes);
    }


   private List<String> getWords () throws FileNotFoundException {
       List<String> words=new ArrayList<>();
       File wordsFromFile=new File(WORDS_PATH);
       Scanner wordScanner= new Scanner(wordsFromFile);
       while(wordScanner.hasNextLine()){
           words.add(wordScanner.nextLine());
       }
       return  words;
   }

}
