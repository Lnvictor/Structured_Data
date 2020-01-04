/**
 * 
 * @author:  Victor Pereira
 * @version: 27/12/2019
 /*/

class CharDemo {
    public void Char_is(String str){
        for (char c : str.toCharArray()){
            if (Character.isDigit(c)){
                System.out.println(c + " id digit");
            }
            else if (Character.isAlphabetic(c)){
                System.out.println(c + " id alphabetic");

            }
        }
    }


    public static void main(String[] args) {
        CharDemo ForTest = new CharDemo();
        ForTest.Char_is("V1cto5 H5NR1qu5");   
    }
}