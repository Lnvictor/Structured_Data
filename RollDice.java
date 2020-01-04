import java.util.*;

public class RollDice {
    public static void RollaDice(int rolls){
        Random random = new Random();
        int[] counts = new int[13];

        for (int i = 0; i < rolls; i++){
            int dice_1 = random.nextInt(6) + 1;
            int dice_2 = random.nextInt(6) + 1;
            System.out.println("Roll is: " + dice_1 + " + " + dice_2 + " = " + (dice_1 + dice_2));

            counts[dice_1 + dice_2]++;
        }
        
        for (int i = 2; i < 13; i++){
            System.out.println(i+" :"+counts[i]);
        }

    }

    public static void main(String[] args) {
        RollaDice(2);
    }
}