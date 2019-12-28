import java.util.*;
/**
 * 
 * Implementation of the "MedianOfTwoArrays" Problem solution
 * Link: leetcode.com/problems/median-of-two-sorted-arrays/
 * 
 * @author: Victor Pereira
 * @version: 1.0
 */

 
class MedianOfArrays{

    public double CalcMedianOfTwo(Vector<Integer> vector, Vector<Integer> vector_2){
        Vector<Integer> vector_result = new Vector<Integer>(vector.capacity() +                                                        vector_2.capacity());
        
        
        vector_result.addAll(vector);
        
        for(int i = 0; i < vector_2.capacity(); i++){
            if(!vector_result.contains(vector_2.get(i))){
                vector_result.add(vector_2.get(i));
            }
        }

        Collections.sort(vector_result);
        int middle = (vector_result.indexOf(vector_result.lastElement()) + 1) / 2;

        
        if ((vector_result.indexOf(vector_result.lastElement()) + 1) % 2 == 0){
            return ((double)vector_result.get(middle) +                                   (double)vector_result.get(middle - 1))/ 2;
        }
    
        return (double) vector_result.get(middle);
    }


    private void test_CalcMedianOfTwo(){
        Vector<Integer> vector = new Vector<Integer>(2);
        Vector<Integer> vector_2 = new Vector<Integer>(2);

        vector.add(1); vector.add(2);
        vector_2.add(3); vector_2.add(4);
        
        
        if(CalcMedianOfTwo(vector, vector_2) != 2.5){
            System.out.println("Test Failed");
        }

        System.out.println("Tests passed");
    }


    public static void main(String[] args) {
        MedianOfArrays Ex = new MedianOfArrays();

        Ex.test_CalcMedianOfTwo();
    }
}