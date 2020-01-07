/**
 * 
 * 
 * @author: Victor Pereira
 * @version: 06/01/2020
/ */
import edu.duke.*;
import java.lang.StringBuilder;
import java.util.ArrayList;

public class VigenereBreaker {
    
    private String message;
    private ArrayList<String> MySlices;
    private CaesarCracker cracker;

    public VigenereBreaker(String Message){
        this.message = Message;
        this.MySlices = new ArrayList<String>();
        this.cracker= new CaesarCracker();
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
        int[] keys = new int[klength];
        
        for(int i = 0; i < klength; i++){
            this.MySlices.add(sliceString(encrypted, i, klength));
            keys[i] = cracker.getKey(MySlices.get(i));
        }
        
        return keys;
    }

    public void breakVigenere(){
        int[] keys = tryKeyLength(this.message, 4, 'e');
        VigenereCipher breaker = new VigenereCipher(keys);
        System.out.println(breaker.decrypt(message));
    }

    public static void main(String[] args) {
        FileResource cc = new FileResource();
        VigenereBreaker c = new VigenereBreaker(cc.asString());
        c.breakVigenere();
    }
}