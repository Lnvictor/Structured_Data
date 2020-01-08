/**
 * 
 * 
 * @author: Victor Pereira
 * @version: 29/12/2019
 */

import edu.duke.FileResource;


class CountCommonsWords {

    public String[] getCommon(){
        FileResource commons = new FileResource("data/common.txt");
        String[] result = new String[20];
        int index = 0;

        for(String word : commons.words()){
            result[index] = word;
            index++;
        }

        return result;
    }

    public void countWords(FileResource resource, String[] common, int[] counts){
        
        for (String word : resource.words()){
            for(int k = 0; k < common.length; k++){
                word = word.toLowerCase();
                if(common[k].equals(word)){
                    counts[k]++;
                }
            }
        }
    }

    public void CountShakspeare(){
        String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt", "small.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];

        for(int k = 0; k < plays.length; k++){
            FileResource resource = new FileResource("data/" + plays[k]);
            countWords(resource, common, counts);
            System.out.println("done with: "+ plays[k]);
        }

        for(int k = 0; k < common.length; k++){
            System.out.println(common[k] + "\t" + counts[k]);
        }
    }

    public static void main(String[] args) {
        CountCommonsWords f = new CountCommonsWords();
        f.CountShakspeare();
    }
}