class CaesarCipher {

    private String ALPHABET;

    CaesarCipher(){
        ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";        
    }

    
    public String encrypt(String message, int key){
        
        StringBuilder ENCRYPTED_ALPHABET = new StringBuilder();
        ENCRYPTED_ALPHABET.append(ALPHABET.substring(key) + ALPHABET.substring(0, key));
        
        message = message.toUpperCase();
        String encrypted_message = "";

        for (char a : message.toCharArray()){
            if (ALPHABET.indexOf(a) != -1)
                encrypted_message += ENCRYPTED_ALPHABET.charAt(ALPHABET.indexOf(a));
            else
                encrypted_message += a;

        }

        return encrypted_message;
    
    }

    public void test_encrypt(){
        if(!encrypt("ij v", 17).equals("ZA M")){
            System.out.println("Test 1 Failed");
        }
        

        if(!encrypt("abracadabra", 15).equals("PQGPRPSPQGP")){
            System.out.println("Test 2 Failed");
        }

        System.out.println("Tests passed");
    }


    public static void main(String[] args) {
        CaesarCipher a = new CaesarCipher();
        a.test_encrypt();
        
    }
}