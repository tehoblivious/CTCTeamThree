package Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * CIST 2931 - Team 3
 */

/**
     * The {@code Patient} class is a data model for patients. 
     * 
     * The class {@code Patient} includes methods for setting/getting the various fields, updating database entries, and adding new {@code Patient} objects to the database.
     */
public class Patient {
    private String patId;
    private String password;
    private String name;
    private String address;
    private String email;
    private String insCo;
    
    public Patient(){
        this("", "", "", "", "", "");
    }
    /**
     * Creates a new patient object.
     * @param idIn A string representing the patient's ID.
     * @param pwdIn A string representing the patient's password.
     * @param nameIn A string representing the patient's name.
     * @param addressIn A string representing the patient's address.
     * @param emailIn A string representing the patient's email address.
     * @param insCoIn A string representing the patient's insurance company. 
     */
    public Patient(String idIn, String pwdIn, String nameIn, String addressIn, String emailIn, String insCoIn){
        patId = idIn;
        password = pwdIn;
        name = nameIn;
        address = addressIn;
        email = emailIn;
        insCo = insCoIn;
    }
    
    /**
     * Displays the data from the object using {@code System.out.println}
     */
    public void display(){
        System.out.println("---Patient Info---");
        System.out.println("PatID    : " + patId);
        System.out.println("Password  : " + password); 
        System.out.println("Name: " + name);
        System.out.println("Address   : " + address);
        System.out.println("Email     : " + email);
        System.out.println("Insurance     : " + insCo);
    }
    
    /**
     * Populates the object with data from the database.
     * @param id A string representing the patient's ID.
     * @throws Exception 
     */
    public void selectDB(String id) throws Exception{
        try {
            //this line must be added to work with glassfish
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://ChiropractorOfficeMDB.mdb");
            
            System.out.println("Connected to DB.");
            
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM Patients where patId = '" + id + "'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            patId = rs.getString(6);
            password = rs.getString(7);
            name = rs.getString(1);
            address = rs.getString(2);
            email = rs.getString(3);
            insCo = rs.getString(4);
            rs = null;
            
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
     * Inserts a row into the database with the provided information.
     * @param IDIn A string representing the patient's ID.
     * @param pwdIn A string representing the patient's password.
     * @param nameIn A string representing the patient's name.
     * @param addressIn A string representing the patient's address.
     * @param emailIn  A string representing the patient's email address.
     * @param insCoIn  A string representing the patient's insurance company.
     */
    public void insertDB(int IDIn, String pwdIn, String nameIn, String addressIn, String emailIn, String insCoIn){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://ChiropractorOfficeMDB.mdb");
            
            System.out.println("Connected to DB.");
            
            Statement statement = con.createStatement();
            String sql = String.format("INSERT INTO Patients VALUES ('%d', '%s', '%s', '%s', '%s', '%s');", IDIn, pwdIn, nameIn, addressIn, emailIn, insCoIn);
            System.out.println("SQL String: " + sql);
            statement.execute(sql);  
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }  
    }
    
    /**
     * Deletes the row associated with the current object from the database.
     */
    public void deleteDB(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://ChiropractorOfficeMDB.mdb");
            
            System.out.println("Connected to DB.");
            
            Statement statement = con.createStatement();
            String sql = String.format("DELETE FROM Patients WHERE patId = '%s';", patId);
            System.out.println("SQL String: " + sql);
            statement.execute(sql);  
            con.close();
            patId = "";
            password = "";
            name = "";
            address = "";
            email = "";
            insCo = "";
        } catch (Exception e){
            System.out.println(e);
        } 
    }
    
    /**
     * Updates the database row associated with the current object.
     */
    public void updateDB(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://ChiropractorOfficeMDB.mdb");
            
            System.out.println("Attempting to update patient record in database.");
            
            Statement statement = con.createStatement();
            String sql = String.format("UPDATE Patients SET passwd = '%s', firstName = '%s', addr = '%s', email = '%s', insCo = '%s' WHERE patId = '%s';", password, name, address, email, insCo, patId);
            System.out.println("SQL String: " + sql);
            statement.execute(sql);  
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }  
    }
    
    /**
     * Gets the ID of the current patient.
     * @return  A string representing the patient's ID.
     */
    public String getID(){
        return patId;
    }
    
    /**
     * Sets the ID of the current patient.
     * @param input  A string representing the patient's ID.
     */
    public void setID(String input){
        patId = input;
    }
    
    /**
     * Gets the password of the current patient.
     * @return  A string representing the patient's password.
     */
    public String getPwd(){
        return password;
    }
    
    /**
     * Sets the password of the current patient.
     * @param input  A string representing the patient's password.
     */
    public void setPwd(String input){
        password = input;
    }
    
    /**
     * Gets the first name of the current patient.
     * @return  A string representing the patient's first name.
     */
    public String getName(){
        return name;
    }
    
    /**
     * Sets the first name of the current patient.
     * @param input A string representing the patient's first name. 
     */
    public void setName(String input){
        name = input;
    }
    
    /**
     * Gets the email address of the current patient.
     * @return  A string representing the patient's email address.
     */
    public String getEmail(){
        return email;
    }
    
    /**
     * Sets the email address of the current patient.
     * @param input  A string representing the patient's email address.
     */
    public void setEmail(String input){
        email = input;
    }
    
    /**
     * Gets the address of the current patient.
     * @return  A string representing the patient's address.
     */
    public String getAddress(){
        return address;
    }
    
    /**
     * Sets the address of the current patient.
     * @param input  A string representing the patient's address.
     */
    public void setAddress(String input){
        address = input;
    }
    
    /**
     * Gets the insurance provider of the current patient.
     * @return  A string representing the patient's insurance provider.
     */
    public String getInsCo(){
        return insCo;
    }
    
    /**
     * Sets the insurance provider of the current patient.
     * @param input  A string representing the patient's insurance provider.
     */
    public void setInsCo(String input){
        insCo = input;
    }
    
    public static void main(String[] args) throws Exception{
        Patient c1 = new Patient();
        c1.selectDB("A900");
	c1.display();
    }
}
