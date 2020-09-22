/*
 * CIST 2931 - Team 3 
 */

package Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Schedule {
    String date;
    String docId;
    
    public Schedule(){
        this("", "");
    }
    
    public Schedule(String inputDate, String inputDoct) {
        date = inputDate;
        docId = inputDoct;
    }
}
