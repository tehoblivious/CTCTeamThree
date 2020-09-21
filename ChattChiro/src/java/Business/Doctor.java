package Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * CIST 2931 - Team 3
 */

/**
 * A data model representing the Doctor data type.
 * @author Timothy
 */
public class Doctor {
    private String doctId;
    private String password;
    private String name;
    private String email;
    private int officeNum;
    
    public Doctor(){
        this("", "", "", "", 0);
    }
    
    /**
     * Creates a new doctor object.
     * @param idIn A string representing the doctor's ID.
     * @param pwdIn A string representing the doctor's password.
     * @param nameIn A string representing the doctor's name.
     * @param emailIn A string representing the doctor's email address.
     * @param officeIn A string representing the doctor's office number.
     */
    public Doctor(String idIn, String pwdIn, String nameIn, String emailIn, int officeIn){
        doctId = idIn;
        password = pwdIn;
        name = nameIn;
        email = emailIn;
        officeNum = officeIn;
    }
    
    /**
     * A method which displays all of the fields belonging to this object via {@code System.out.println}
     */
    public void display(){
        System.out.println("---doctor Info---");
        System.out.println("DoctID    : " + doctId);
        System.out.println("Password  : " + password); 
        System.out.println("Name: " + name);
        System.out.println("Email     : " + email);
        System.out.println("Office Num: " + officeNum);
    }
    
    /**
     * Pulls data out of database and populates object with said data.
     * @param id String representing the doctor's ID.
     * @throws Exception 
     */
    public void selectDB(String id) throws Exception{
        try {
            //this line must be added to work with glassfish
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://ChiropractorOfficeMDB.mdb");
            
            System.out.println("Connected to DB.");
            
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM Doctors where id = '" + id + "'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            doctId = rs.getString(3);
            password = rs.getString(4);
            name = rs.getString(1);
            email = rs.getString(2);
            officeNum = rs.getInt(5);

            con.close();
        } catch (Exception e){
            if (e.toString().contains("ResultSet is empty")) {
                throw e;
            } else {
                System.out.println(e);
            }
        }
    }
    
    /**
     * Creates a new row in the database table.
     * @param IDIn A string representing the doctor's ID.
     * @param pwdIn A string representing the doctor's password.
     * @param nameIn A string representing the doctor's name.
     * @param emailIn A string representing the doctor's email address.
     * @param officeNumIn A string representing the doctor's office number.
     */
    public void insertDB(String IDIn, String pwdIn, String nameIn, String emailIn, int officeNumIn){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://ChiropractorOfficeMDB.mdb");
            
            System.out.println("Connected to DB.");
            
            Statement statement = con.createStatement();
            String sql = String.format("INSERT INTO Doctors VALUES ('%s', '%s', '%s', '%s', '%d');", IDIn, pwdIn, nameIn, emailIn, officeNumIn);
            System.out.println("SQL String: " + sql);
            statement.execute(sql);  
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }  
    }
    
    /**
     * Deletes the row which corresponds with this object from the database.
     */
    public void deleteDB(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://ChiropractorOfficeMDB.mdb");
            
            System.out.println("Connected to DB.");
            
            Statement statement = con.createStatement();
            String sql = String.format("DELETE FROM Doctors WHERE id = '%s';", doctId);
            System.out.println("SQL String: " + sql);
            statement.execute(sql);  
            con.close();
            doctId = "";
            password = "";
            name = "";
            email = "";
            officeNum = 0;
        } catch (Exception e){
            System.out.println(e);
        } 
    }
    /**
     * Writes the data currently contained in the fields of the object to the database.
     */
        public void updateDB(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://ChiropractorOfficeMDB.mdb");
            
            System.out.println("Connected to DB. Updating doctor...");
            
            Statement statement = con.createStatement();
            String sql = String.format("UPDATE Doctors SET passwd = '%s', name = '%s', email = '%s', office = '%d' WHERE id = '%s';", password, name, email, officeNum, doctId);
            System.out.println("SQL String: " + sql);
            statement.execute(sql);  
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }  
    }
    
        
    /**
     * Gets doctor's ID.
     * @return A string representing the doctor's ID.
     */
    public String getID(){
        return doctId;
    }
    
    /**
     * Sets doctor's ID.
     * @param input A string representing the doctor's ID.
     */
    public void setID(String input){
        doctId = input;
    }
    
    /**
     * Gets doctor's password.
     * @return A string representing the doctor's password.
     */
    public String getPwd(){
        return password;
    }
    
    /**
     * Sets doctor's password.
     * @param input A string representing the doctor's password.
     */
    public void setPwd(String input){
        password = input;
    }
    
    /**
     * Gets doctor's name
     * @return A string representing the doctor's name.
     */
    public String getName(){
        return name;
    }
    
    /**
     * Sets doctor's name
     * @param input A string representing the doctor's name.
     */
    public void setName(String input){
        name = input;
    }
    
    /**
     * Gets doctor's email address.
     * @return A string representing the doctor's email address.
     */
    public String getEmail(){
        return email;
    }
    
    /**
     * Sets doctor's email address.
     * @param input A string representing the doctor's email address.
     */
    public void setEmail(String input){
        email = input;
    }
    
    /**
     * Gets doctor's office number.
     * @return A string representing the doctor's office number.
     */
    public int getOfficeNum(){
        return officeNum;
    }
    
    /**
     * Sets doctor's office number.
     * @param input A string representing the doctor's office number.
     */
    public void setOfficeNum(int input){
        officeNum = input;
    }
    
    public static void main(String[] args) throws Exception{
        Doctor d1 = new Doctor();
        d1.selectDB("D201");
	d1.display();
    }
}
