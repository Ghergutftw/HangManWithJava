package com.advanced.FinalPractice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameService {
    private static final String WORDS_PATH = "complete-java-course-master/src/com/advanced/FinalPractice/words.txt";

    public void start() throws FileNotFoundException {
        Scanner userInput= new Scanner(System.in);
        //random Word va fi cuvantul
        String randomWord=getRandomWord();
        //Transforma cuvantul in litere separate
        char[] randomWordArray=randomWord.toCharArray();
        //Formeaza un cuvant de ghicit de marimea egalal cu cel random
        char[] guessingArray= new char[randomWord.length()];
        //umple cuvantul de ghici cu_
        Arrays.fill(guessingArray,'_');
        //pregateste numarul de sanse
        int numberOfChances=randomWord.length();

        System.out.println("Starting the game");
        System.out.println("The lenght of the word and number of chances: " + numberOfChances);
        System.out.println("The word: " + new String(guessingArray));

        while(true){
            System.out.println("----------------------------");
            System.out.println("Please type in a letter");
            //inputul, si ca sa evitam errori vom lua prima valoarea, ori ca am primi litera ori cuvant
            String inputLine= userInput.nextLine().toLowerCase();
            char letter= inputLine.charAt(0);
            System.out.println(letter);
            boolean isGuessingCorrect = false;
            for (int i = 0; i < randomWord.length(); i++) {
                //daca se gasesc litera va fi inlocui _
                if (letter == randomWordArray[i]) {
                    guessingArray[i] = letter;
                    isGuessingCorrect = true;
                }
            }
            System.out.println("The word: " + new String(guessingArray));

            if(isGuessingCorrect){
                {
                    System.out.println("Correct");
                    if(isGameFinished(guessingArray)){
                        break;
                    }
                }

            }else{
                numberOfChances--;
                System.out.println("Wrong, your chances: " + numberOfChances);
                System.out.println("Try another letter ");
                if(numberOfChances==0){
                    System.out.println("You lost");
                    System.out.println("The Word is " + randomWord);
                    break;

                }
            }


        }

    }

    private boolean isGameFinished(char[] guessingArray) {
        for (int i = 0; i < guessingArray.length; i++) {
            if(guessingArray[i]=='_')return false;
        }
        System.out.println("Congratulations ");
        System.out.println("The Word is " + guessingArray);
        return true;
    }

    //Tipul returnat este List<String> deoarece getTheWordsFromFile va fi o lista de cuvinte
    private List<String> getTheWordsFromTheFile() throws FileNotFoundException {
        //Se va forma o lista de Cuvinte, un array list
        List<String> wordFromTheFile=new ArrayList<>();
        //Cuvintele citite vor fii din fisier
        File wordsFile= new File(WORDS_PATH);
        //Preagatim Scanner-ul pentru citire, spunandu-i ca citim din fisier
        Scanner wordScanner=new Scanner(wordsFile);
        //Cat timp avem cuvinte in fisier le vom adauga la lista de cuvinte
        while(wordScanner.hasNextLine()){
            wordFromTheFile.add(wordScanner.nextLine());
        }
        return  wordFromTheFile;
    }

    //Va returna un cuvant
    private String getRandomWord() throws FileNotFoundException {
        //randomNumber va returna un numar random din range-ul 0--Numarul de cuvinte
        Random randomNumber=new Random();
        //words va fi Lista de cuvinte care este pasata de metoda getTheWords
        List<String> words=getTheWordsFromTheFile();
        //indexul random generat
        int randomIndex= randomNumber.nextInt(words.size());
        //Returnam cuvantul de pe pozitia random
        return words.get(randomIndex);

    }




}
