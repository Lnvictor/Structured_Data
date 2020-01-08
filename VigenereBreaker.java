/**
 * This is a breaker for Vigénere Cipher
 * 
 * @author: Victor Pereira
 * @version: 06/01/2020
/ */
import edu.duke.FileResource;
import java.lang.*;
import java.util.*;
public class VigenereBreaker {
    
    private char mostCommon;
    private String message;
    private CaesarCracker cracker;

    public VigenereBreaker(String Message, char mostCommon){
        this.message = Message;
        this.mostCommon = mostCommon;
        this.cracker= new CaesarCracker(mostCommon);
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
        ArrayList<String> MySlices = new ArrayList<String>();
        int[] keys = new int[klength];
        
        for(int i = 0; i < klength; i++){
            MySlices.add(sliceString(encrypted, i, klength));
            keys[i] = cracker.getKey(MySlices.get(i));
        }
        
        return keys;
    }

    public void breakVigenere(){        
        String file_path = "/home/victor/Structured_Data/dictionaries";

        if(this.mostCommon == 'a'){
            file_path += "/Portuguese";
        }
        else if(this.mostCommon == 'e'){
            file_path += "/English";
        }
        
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

        // int[] key = tryKeyLength(this.message, 4, 'e');
        // VigenereCipher c = new VigenereCipher(key);
        // System.out.println(c.decrypt(this.message));

        for(int k = 1; k < 96; k++){
            isRigth = true;
            
            int[] key = tryKeyLength(this.message, k, this.mostCommon);
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

    public void breakVigenere(int keylength){
        String file_path = "/home/victor/Structured_Data/dictionaries";

        if(this.mostCommon == 'a'){
            file_path += "/Portuguese";
        }
        else if(this.mostCommon == 'e'){
            file_path += "/English";
        }
        int[] key = tryKeyLength(this.message, keylength, this.mostCommon);
        VigenereCipher c = new VigenereCipher(key);
        String decrypted_message = c.decrypt(this.message).toLowerCase();
        System.out.println(decrypted_message);
    }
    
    public static void main(String[] args) {
        FileResource file = new FileResource("/home/victor/Structured_Data/messages/secretmessage1.txt");
        FileResource file_2 = new FileResource("/home/victor/Structured_Data/messages/osLusiadas.txt");
        
        VigenereBreaker c = new VigenereBreaker(file.asString(), 'e');
        VigenereBreaker d = new VigenereBreaker(file_2.asString(), 'a');
        
        c.breakVigenere();
        d.breakVigenere();
    }       
}