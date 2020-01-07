/**
 * 
 * It's a Log Analyzer
 * 
 * @author: Victor Pereira
 * @version: 06/01/2020
 */

import edu.duke.*;
import java.util.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    private HashMap<String, Integer> countVisits;
    
    public LogAnalyzer(){
        this.records = new ArrayList<LogEntry>();
        this.countVisits = new HashMap<String, Integer>();
    }

    public void ReadFile(String urls){
        URLResource url = new URLResource(urls);
        
        for (String line : url.lines()){
            LogEntry record = WebLogParser.parseEntry(line);
            records.add(record);   
        }
    }

    @Override
    public String toString(){
        return this.records.toString();
    }

    public void printAll(){
        for (LogEntry line : this.records){
            System.out.println(line);
        }
    }

    public int getUniqueValues(){
        ArrayList<String> uniqueValues = new ArrayList<String>();

        for (LogEntry value : this.records){
            if (!uniqueValues.contains(value.getIpAdress())){
                uniqueValues.add(value.getIpAdress());
            }
        }
        return uniqueValues.size();
    }

    public HashMap<String, Integer> CountVisitsAll(){
        
        for (LogEntry value : this.records){
            if(!this.countVisits.containsKey(value.getIpAdress())){
                this.countVisits.put(value.getIpAdress(), 1);
            }
            else{
                this.countVisits.put(value.getIpAdress(), this.countVisits.get(value.getIpAdress()) + 1);
            }
        }

        return this.countVisits;
    }
}