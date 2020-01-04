/**
 * 
 * 
 * @author: Victor Pereira
 * @version: 02/02/2020
 */

import edu.duke.FileResource;
import java.util.ArrayList;

class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void FindUnique(){
        FileResource resource = new FileResource();
    
        for (String word : resource.words()){
            int index = myWords.indexOf(word.toLowerCase());

            if (index == -1){
                myWords.add(word.toLowerCase());
                myFreqs.add(1);
            }
            else{
                this.myFreqs.set(index, myFreqs.get(index) + 1);
            }
        }
    }

    public ArrayList<String> getmyWords(){
        FindUnique();
        return this.myWords;
    }

    public void tester(){
        FindUnique();
        int sum = 0;

        for (int number : this.myFreqs){
            sum += number;
        }

        System.out.println("Total Words: "+ sum);
        System.out.println("Unique words: "+ myFreqs.size());

        for (String word : this.myWords){
            System.out.println("Word: " + word +"\tOcurrences: " + this.myFreqs.get(myWords.indexOf(word)));
        }
    }

    public static void main(String[] args) {
        WordFrequencies cc = new WordFrequencies();
        cc.tester();

    }
}