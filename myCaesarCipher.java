/**
 * Caesar Cipher Implementation
 * 
 * For Decrypt, letter the most frequented need to be 'e'
 * 
 * @author: Victor Pereira
 * @version: 02/01/2020
 */

class myCaesarCipher {

    private String ALPHABET;
    private int key;

    public myCaesarCipher(int key){
        ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        this.key = key;
    }

    
    public String encrypt(String message){
        
        StringBuilder shifted_alphabet = new StringBuilder();
        shifted_alphabet.append(ALPHABET.substring(key) + ALPHABET.substring(0, key));
        
        message = message.toLowerCase();
        String encrypted_message = "";

        for (char a : message.toCharArray()){
            if (ALPHABET.indexOf(a) != -1)
                encrypted_message += shifted_alphabet.charAt(ALPHABET.indexOf(a));
            else
                encrypted_message += a;

        }

        return encrypted_message;
    
    }

    private int[] count_freq(String message, int[] count){
        
        message = message.toLowerCase();
        for(int k = 0; k < message.length(); k++){
            char c = message.charAt(k);
            if(ALPHABET.indexOf(c) != -1){
                count[ALPHABET.indexOf(c)]++;
            }
        }

        return count;
    }

    private int getMostFrequented(int[] count){
        int most_frequented = 0;

        for (int k = 1; k < count.length; k++){
            if (count[k] >= count[most_frequented]){
                most_frequented = k;
            }
        }
        return most_frequented;
    }

    public String decrypt(String message){
        int[] count = new int[26];
        count = count_freq(message, count);
        int max_index = getMostFrequented(count);

        int dkey = max_index - 4;

        if (max_index < 4){
            dkey = 26 - (4 - max_index);
        }

        return encrypt(message);
    }

    public static void main(String[] args) {
        
    }
}