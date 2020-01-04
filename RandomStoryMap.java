/**
 * This is a improve of RandomStory using the HashMap concept
 * 
 * @author: Victor Pereira
 * @version: 04/01/2019
 */

import edu.duke.*;
import java.io.File;
import java.util.*;

 public class RandomStoryMap {
    private Random random;
    private String story;
    private HashMap<String, ArrayList<String>> myMap;

    RandomStoryMap(String source){
        this.random = new Random();
        this.myMap = new HashMap<String, ArrayList<String>>();
        this.story = getStory(source);
        this.readAll(myMap, source);
    }

    private String getStory(String source){
        String filename = "madtemplate" + (random.nextInt(2) + 1) + ".txt";
        String s = "";
        FileResource file = new FileResource(filename);
        ArrayList<String> Strings = new  ArrayList<String>();

        for (String word : file.words()){
            Strings.add(word);
        }

        for (String word : Strings){
            s += word + " ";
        }

        return s;
    }

    private void readAll(HashMap<String, ArrayList<String>> myMap, String source){
        DirectoryResource directory = new DirectoryResource();
        
        for (File file : directory.selectedFiles()){
            FileResource file_ = new FileResource(file);
            
            String filename = file.getName();
            String key = "<" + filename.substring(0, filename.indexOf(".")) + ">";
            
            myMap.put(key, new ArrayList<String>());
            
            for (String word : file_.words()){
                myMap.get(key).add(word);
            }
        }
    }

    public String getSubstitute(String label){
        if (this.myMap.containsKey(label))
            return this.myMap.get(label).get(random.nextInt(myMap.get(label).size()));
        return "**UNKNOWN**";
    }

   
    public String makeStory(){
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