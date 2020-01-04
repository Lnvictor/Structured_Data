import edu.duke.FileResource;
import java.util.HashMap;

public class CountWordsMap {
    private HashMap<String,Integer> map;

    CountWordsMap(){
        map = new HashMap<String, Integer>();
    }

    public void FindUniqueWords(FileResource file){

        for(String word : file.words()){
            if(!map.containsKey(word)){
                map.put(word, 1);
            }
            else{
                map.put(word, map.get(word) + 1);
            }
        }

        System.out.println(this.map);
    }

    public static void main(String[] args) {
        CountWordsMap e = new CountWordsMap();
        FileResource file = new FileResource("data/small.txt");
        e.FindUniqueWords(file);
    }
}