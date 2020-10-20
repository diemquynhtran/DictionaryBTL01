package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
/*
    public Dictionary getDictionary() {
        return dictionary;
    }*/

    public void insertFromFile() {
        File file = new File("dictionaries.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word_target;
                String word_explain;
                String word_pronounce;
                String word_round;
                String line = scanner.nextLine();
                String[] parts = line.split("@");
                word_target = parts[0];
                word_round = parts[1];
                word_pronounce = parts[2];
                word_explain = parts[3];
                Word newWord = new Word(word_target, word_round,
                        word_pronounce, word_explain);
                addWordElement(newWord);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Word dictionaryLookup(String wordSearch) {

        for (Word word : wordArray) {
            if (word.word_target.trim().equals(wordSearch)) {
                return word;
            }
        }
        return new Word("", "", "", "");
    }


    public List dictionarySearcher(String search) {
        List<String> listSearch = new ArrayList<>();
        for (Word word : wordArray) {
            if (word.word_target.indexOf(search) == 0) {
                listSearch.add(word.word_target);
            }
        }
        //for(int i=0;i<listSearch.size()-1;i++) System.out.println(listSearch.get(i));
        return listSearch;

    } //tra cứu : g->go, good..

    public List wordTarget() {
        List<String> list = new ArrayList<>();
        for(int i=0;i<wordArray.size();i++){
            list.add(wordArray.get(i).word_target);
        }
        return list;
    }

    //public List wordTargett();

    public void exportToFile () throws IOException {
        try (FileOutputStream fout = new FileOutputStream("dictionaries.txt")) {
            for (Word word : wordArray) {
                String str = word.word_target.trim()
                        + "@" + word.word_round.trim()
                        + "@" + word.word_pronounce.trim()
                        + "@" + word.word_explain.trim() + "\n";
                byte[] b = str.getBytes();
                fout.write(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addWord(Word word) throws IOException {
        addWordElement(word);
        exportToFile();
    }

    public void removeWord(String word) throws IOException {

        List<Word> wordList = getWordArray();
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).word_target.trim().equals(word)) {
                removeWordElement(i);
            }
        }
        exportToFile();
    }

    public void editWord(String oldWord, String newWord) throws IOException {
        for (Word word : wordArray) {
            if (word.word_target.trim().equals(oldWord)) {
                word.setWord_target(newWord);
            }
        }
        exportToFile();
    }



}

