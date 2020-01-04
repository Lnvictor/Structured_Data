import java.util.ArrayList;
class Ex_ArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> example = new ArrayList<Integer>()
        {
            {add(3); add(4); add(5);}
        };

        for (int i : example){
            System.out.println(i);
        }
        
    }
}