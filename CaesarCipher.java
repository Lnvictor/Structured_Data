/**
 * Caesar Cipher Implementation
 * 
 * For Decrypt, letter the most frequented need to be 'e'
 * 
 * @author: Victor Pereira
 * @version: 02/01/2020
 */

class CaesarCipher {

    private String ALPHABET;

    public CaesarCipher(){
        ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    }

    
    public String encrypt(String message, int key){
        
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

        return encrypt(message, 26 - dkey);
    }

    private void test_encrypt(){
        String string_1 = encrypt("O rato roeu a roupa do rei de roma", 16);

        
        String string_2 = encrypt("This is important to remember. Love isn't like pie. You don't need to divide it among all your friends and loved ones. No matter how much love you give", 13);   
        
        String string_3 = encrypt("No matter how much love you give", 7); 


        assert (string_1.equals("e hqje heuk q hekfq te huy tu hecq")): "Test 1 Failed";

        assert (string_2.equals("guvf vf vzcbegnag gb erzrzore. ybir vfa'g yvxr cvr. lbh qba'g arrq gb qvivqr vg nzbat nyy lbhe sevraqf naq ybirq barf. ab znggre ubj zhpu ybir lbh tvir")) : "Test 2 Failed";

        assert (string_3.equals("uv thaaly ovd tbjo svcl fvb npcl")):"Test 3 Failed";

        assert (!decrypt(string_1).equals("O rato roeu a roupa do rei de roma")) : "Test 4 Failed";

        assert (!decrypt(string_2).equals("this is important to remember. Love isn't like pie. You don't need to divide it among all your friends and loved ones. no matter how much love you give")) : "Test 5 Failed";

        assert (!decrypt(string_1).equals("no matter how much love you give")) : "Test 6 Failed";


        System.out.println("Tests passed");
    }


    public static void main(String[] args) {
        CaesarCipher a = new CaesarCipher();

        a.test_encrypt();
    }
}