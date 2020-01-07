import java.util.Date;

public class LogEntry {
    private String ipAdress;
    private Date acessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;

    public LogEntry(String ipAdress, Date acessTime, String request, int statusCode, int bytesReturned){
        this.ipAdress = ipAdress;
        this.acessTime = acessTime;
        this.request = request;
        this.statusCode = statusCode;
        this.bytesReturned = bytesReturned;
    }

    public String getIpAdress(){
        return this.ipAdress;
    }

    public Date getAcessTime(){
        return this.acessTime;
    }

    public String getRequest(){
        return this.request;
    }

    public int getStatusCode(){
        return this.statusCode;
    }

    public int getBytesReturned(){
        return this.bytesReturned;
    }
 
    @Override
    public String toString(){
        return this.ipAdress + " " + this.acessTime + " " + this.request + " " + this.statusCode  + " " + this.bytesReturned;
    }

    public static void main(String[] args) {
        LogEntry c = new LogEntry("1.7.8.800", new Date(), "example request", 200, 404);
        System.out.println(c);
    }
}