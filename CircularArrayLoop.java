/**
 * 
 * Implementation of the "CircularArrayLoop" Problem solution
 * Exercise link: https://leetcode.com/problems/circular-array-loop/
 * 
 * @author: Victor Pereira
 * @version: 29/12/2019
 */

import java.util.Vector;


public class CircularArrayLoop {

    protected String direction;
    CircularArrayLoop(){
        this.direction = "";
    }

    public boolean IsCycle(Vector<Integer> nums){
        int j = 0;
        int previous_index = 0;
        Vector<Integer> steps = new Vector<Integer>(nums.capacity());

        nums.forEach(n -> steps.add(n));
    
        for (int i = 0; i < nums.capacity(); i++){
            j = i;
            int count_length = 0;

            while(count_length < nums.capacity() || (steps.get(j) + j) <= 0){
                j = FindNextPosition(steps, j);
                
                if (j == -1){
                    break;
                }
                if (j == i && count_length > 1){
                    return true;
                }
                
                previous_index = j;
                count_length++;
            }
        }
        return false;
    }

    public int FindNextPosition(Vector<Integer> steps, int position){
        if (direction.isEmpty()){
            
            if(steps.get(position) >= 0){
                direction += "FORWARD";
            } 
            else{
                direction += "BACKWARD";
            }
        }
        
        if((steps.get(position) < 0 && direction.equals("FORWARD")) || (steps.get(position) > 0 &&           direction.equals("BACKWARD"))){
            return -1;
        }

        if ((steps.get(position) + position) >= steps.capacity()){
            
            if (position == steps.capacity() - 1){
                position = 0 + steps.get(position);
            }
            else{
                position = 0 + (steps.get(position) - (steps.capacity() - position)); 
            }
        }
        
        else {
            position += steps.get(position);
        }
        
        if (position < 0){
            position = (steps.capacity() - 1) + position;
        }

        return position;
    }

    private void test_IsCycle(){

        assert (!IsCycle(new Vector<Integer>(2){{add(2);add(1);}})) : "Test 1 Failed";
        
        assert(!IsCycle(new Vector<Integer>(2){{add(2);add(-1);}})) : "Test 2 Failed";
        
        assert(IsCycle(new Vector<Integer>(5){{add(2);add(-1);add(1);add(2);add(2);}})) : "Test 3 Failed";
        

        assert(!IsCycle(new Vector<Integer>(5){{add(-2);add(1);add(-1);add(-2);add(-2);}})) : "Test 4 Failed";;

        assert(IsCycle(new Vector<Integer>(5){{add(2);add(3);add(1);add(1);add(2);}})) : "Test 5 Failed";
        
        System.out.println("Tests finish");
    }

    public static void main(String[] args) {
        
        CircularArrayLoop ForTest = new CircularArrayLoop();

        ForTest.test_IsCycle();
    }
}