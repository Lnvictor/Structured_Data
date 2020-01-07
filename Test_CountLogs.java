import java.util.HashMap;
public class Test_CountLogs {
    public void test_counts(){
        LogAnalyzer cc = new LogAnalyzer();
        cc.ReadFile("http://www.dukelearntoprogram.com/java/weblog-short_log");
        HashMap<String, Integer> visits = cc.CountVisitsAll();

        
        assert (visits.get("84.189.158.117").equals(2)):"Test 1 Failed";
        assert (visits.get("61.15.121.171").equals(3)):"Test 2 Failed";
        assert (visits.get("84.133.195.161").equals(3)):"Test 1 Failed";
        assert (visits.get("177.4.40.87").equals(4)):"Test 1 Failed";

        System.out.println("Tests finish");

    }

    public static void main(String[] args) {
        Test_CountLogs cc = new Test_CountLogs();
        cc.test_counts();
    }
}