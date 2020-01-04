/**
 * This program is a generator of random Storys
 * 
 * Its a KISS!!!!
 * 
 * @author: Victor Pereira
 * @version: 02/01/2020
 */

import edu.duke.FileResource;
import java.util.*;

public class RandomStory {
    private String story;
    private ArrayList<String> countrys;
    private ArrayList<String> verbs;
    private ArrayList<String> timeunity;
    private ArrayList<String> colors;
    private ArrayList<String> animals;
    private ArrayList<String> adjectives;
    private ArrayList<String> names;
    private ArrayList<String> fruits;
    private ArrayList<String> nouns;

    private Random random;


    public RandomStory(String source){
        this.random = new Random();
        this.story = getStory(source);
        this.countrys =  readIt(source + "/countrys.txt");
        this.verbs = readIt(source + "/verbs.txt");
        this.timeunity = readIt(source + "/timeunity.txt");
        this.colors = readIt(source + "/colors.txt");
        this.animals = readIt(source + "/animals.txt");
        this.adjectives = readIt(source + "/adjectives.txt");
        this.names = readIt(source + "/names.txt");
        this.fruits = readIt(source + "/fruits.txt");
        this.nouns = readIt(source + "/nouns.txt");
    }


    private String getStory(String source){
        String filename = "madtemplate" + (random.nextInt(2) + 1) + ".txt";
        String s = "";
        ArrayList<String> Strings = readIt(source + "/" +filename);

        for (String word : Strings){
            s += word + " ";
        }

        return s;
    }

    
    public ArrayList<String> readIt(String path){
        ArrayList<String> list = new ArrayList<String>();
        FileResource file = new FileResource(path);
        
        for (String word : file.words()){
            list.add(word);
        }

        return list;
    }


    public String randomFrom(ArrayList<String> list){
        int random_index = random.nextInt(list.size() - 1);
        return list.get(random_index);
    }


    public String getSubstitute(String label){
        if(label.equals("<countrys>")){
            return randomFrom(countrys);
        }

        if(label.equals("<fruits>")){
            return randomFrom(fruits);
        }
        
        if(label.equals("<timeunity>")){
            return randomFrom(timeunity);
        }
        
        if(label.equals("<colors>")){
            return randomFrom(colors);
        }
        
        if(label.equals("<animals>")){
            return randomFrom(animals);
        }
        
        if(label.equals("<adjectives>")){
            return randomFrom(adjectives);
        }

        if(label.equals("<nouns>")){
            return randomFrom(nouns);
        }

        if(label.equals("<names>")){
            return randomFrom(names);
        }

        if(label.equals("<verbs>")){
            return randomFrom(verbs);
        }
        if (label.equals("<numbers>")){
            return Integer.toString(random.nextInt(500)+1);
        }
        System.out.println(label);
        return "**UNKNOWN**";
    }
    

    public String makeStory() {
        ArrayList<String> c = new ArrayList<String>();
        String[] aux = this.story.split(" ");
        String new_story = "";

        for (int k = 0; k < aux.length; k++){
            c.add(aux[k]);
        }
        
        for(String word : c){
            if (word.startsWith("<")){
                new_story += " " +  getSubstitute(word.substring(word.indexOf("<"), word.indexOf(">") + 1));
            }
            else
                new_story += " " + word;
        }
        
        return new_story;
    }   


    public static void main(String[] args) {
        RandomStory ForTest = new RandomStory("/home/victor/Structured_Data/data_randomStories");


        System.out.println(ForTest.makeStory());
    }
}