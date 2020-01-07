/**
 * Implementation of the "PatchingArray" problem solution
 * Exercise Link: https://leetcode.com/problems/patching-array/
 * 
 * @author: Victor Pereira
 * @version: 01/01/2020
 */

public class PatchingArray {
    public int minPatches(int[] array, int interval){
        
        int[] freqs = new int[interval];
        int num_patches = 0;
        
        while(checkPatches(array, freqs, interval)){
            for (int i = 0; i < freqs.length; i++){
                if(array[i] < interval){
                    freqs[array[i] - 1]++;
                }

                for (int k = 0; k < freqs.length; k++){
                    if (k == i){
                        continue;
                    }

                    if (k + i < interval){
                        freqs[array[i + k] - 1]++;
                    }
                }
            }
        }

        return
    }

    private boolean checkPatches(int[] array, int[] freqs, int interval){;
        
        for(int k = 0; k < freqs.length; k++){
            if(freqs[k] == 0){
                return false;
            }
            return true;
        }
        
    }
}