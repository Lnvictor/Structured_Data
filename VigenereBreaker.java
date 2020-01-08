/**
 * This is a breaker for Vigénere Cipher
 * 
 * @author: Victor Pereira
 * @version: 06/01/2020
/ */
import edu.duke.*;
import java.io.File;
import java.lang.*;
import java.util.*;

public class VigenereBreaker {
    
    private String message;

    public VigenereBreaker(String Message){
        this.message = Message;
    }

    public String sliceString(String Message, int whichSlice, int totalSlices){
        StringBuilder slice = new StringBuilder();
        char[] message_chars = Message.toCharArray();
                
        for(int i = whichSlice; i < message_chars.length; i += totalSlices){
            slice.append(message_chars[i]);
        }

        return slice.toString();  
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon){
        CaesarCracker cracker = new CaesarCracker(mostCommon);
        ArrayList<String> MySlices = new ArrayList<String>();
        int[] keys = new int[klength];
        
        for(int i = 0; i < klength; i++){
            MySlices.add(sliceString(encrypted, i, klength));
            keys[i] = cracker.getKey(MySlices.get(i));
        }
        
        return keys;
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> freqs = new HashMap<Character, Integer>();
        char mostCommon = 'e';

        for(String word : dictionary){
            char[] word_array = word.toLowerCase().toCharArray();
            for(int k = 0; k < word_array.length; k++){
                if(!freqs.containsKey(word_array[k])){
                    freqs.put(word_array[k], 1);
                }
                else{
                    freqs.put(word_array[k], freqs.get(word_array[k]) + 1);
                }
                
                if(k == 0){
                    mostCommon = word_array[k];
                }
                else if(freqs.get(word_array[k]) > freqs.get(mostCommon)){
                    mostCommon = word_array[k];
                }
            }
        }



        return mostCommon;
    }

    public void breakVigenere(){        
        String file_path = "/home/victor/Structured_Data/dictionaries/English";
        
        boolean isRigth;
        String decrypted_message = "";
        FileResource file = new FileResource(file_path);
        
        ArrayList<String> strs = new ArrayList<String>();
        HashSet<String> dictionary = new HashSet<String>();

        int[] count = new int[96];
        int max = 0;
        
        for(String word : file.words()){
            dictionary.add(word);
        }

        for(int k = 1; k < 96; k++){
            isRigth = true;
            
            int[] key = tryKeyLength(this.message, k, 'e');
            VigenereCipher c = new VigenereCipher(key);
            decrypted_message = c.decrypt(this.message).toLowerCase();
            strs.add(decrypted_message);
            String[] l = decrypted_message.split("\\W");

            for(String word : l){
                if(!word.isEmpty()){
                    String str = word.substring(0, 1) + word.substring(1);
                    if(dictionary.contains(word) && dictionary.contains(str)){
                        count[k-1]++;
                    }

                }
            }
        }

        for(int k = 1; k < 26; k++){
            if(count[k] > count[max]){
                max = k;
            }
        }

        System.out.println(strs.get(max));

    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        boolean isRigth;
        String decrypted_message = "";        
        ArrayList<String> strs = new ArrayList<String>();

        int[] count = new int[96];
        int max = 0;
        

        for(int k = 1; k < 96; k++){
            isRigth = true;
            
            int[] key = tryKeyLength(this.message, k, mostCommonCharIn(dictionary));
            VigenereCipher c = new VigenereCipher(key);
            decrypted_message = c.decrypt(this.message).toLowerCase();
            strs.add(decrypted_message);
            String[] l = decrypted_message.split("\\W");

            for(String word : l){
                if(!word.isEmpty()){
                    String str = word.substring(0, 1) + word.substring(1);
                    if(dictionary.contains(word) && dictionary.contains(str)){
                        count[k-1]++;
                    }

                }
            }
        }

        for(int k = 1; k < 26; k++){
            if(count[k] > count[max]){
                max = k;
            }
        }

        return strs.get(max);
    }

    public void breakforAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        
        HashSet<String> freqs = new HashSet<String>();
        
        for(HashSet<String> dictionary : languages.values()){
            freqs.add(breakForLanguage(encrypted, dictionary));
        }

        for(String el : freqs){
            System.out.println(el);
        }
    }

    
    public void test_Break() {
        DirectoryResource directory = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        
        for(File file : directory.selectedFiles()){
            FileResource file_ = new FileResource(file);
            HashSet<String> a = new HashSet<String>();
            
            for(String word : file_.words()){
                if(!a.contains(word))
                a.add(word);
            }
            
            languages.put(file.getName(), a);
        }
        
        
        breakforAllLangs(message, languages);

    }      

    public static void main(String[] args) {
        FileResource file = new FileResource("/home/victor/Structured_Data/messages/secretmessage1.txt");
        VigenereBreaker breaker = new VigenereBreaker(file.asString());
        breaker.test_Break();
    }

}