package jdbc_again;

import java.sql.*;
import java.util.Scanner;

public class JdbcAgain {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            int choice = 0;
            // Calling the student class and making the object
            // We are making the student class object to call that class functions/methods
            student s = new student();

            do{
                System.out.println(" 1. Press 1 for New Student Record\n 2. Press 2 for Getting the Student Data\n " +
                        "3. Press 3 for Updating the Student Data\n 4. Press 4 for deleting the student data\n 5. Press 5 for exit the Menu\n");
                Scanner choose = new Scanner(System.in);
                choice = choose.nextInt();
                switch (choice){
                    case 1 :
                        s.getStudentDetail();
                        s.insertStudentData();
                        break;
                    case 2 :
                        s.searchStudent();
                        break;
                    case 3 :
                        s.updateStudent();
                        break;
                    case 4 :
                        s.deleteStudent();
                        break;
                    case 5 :
                        break;
                    default:
                        System.out.println("Please Enter Correct Choice!!!");
                }

            } while (choice!=5);{
                System.out.println("Thanks for using the Application");
            }

        } catch (Exception e){

        }
    }
}
//-------------------------------------------------------------------------------------------------------------------
//-------------------------------------------Student Class-----------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------
// Making the students class that contains every functionality
class student{
    private String name;
    private String email;
    private String city;
    private int phone;

    // Function that takes input from student
    public void getStudentDetail(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your Name");
        name = input.nextLine();
        System.out.println("Enter your Email");
        email = input.nextLine();
        System.out.println("Enter your City");
        city = input.nextLine();
        System.out.println("Enter your Phone");
        phone = input.nextInt();
    }

    // now making a function that connects the user input and pass it to the database table column
    public void insertStudentData() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        dbmsconnection dbmsconnection= new dbmsconnection("jdbc:mysql://localhost:3306/jdbc_again?SSL=false","root","Nfo4243*");
        // Here I am calling the function/method that is present in my dbmsconnection class
        Connection con = dbmsconnection.getConnection();

        // making the mysql query
        String sql = "insert into student(name,email,city,phone) values (?,?,?,?);";
        // now preparing my above statement
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(2,email);
        stmt.setString(3,city);
        stmt.setInt(4,phone);

        // Finally, execute my preparedStatements
        int i = stmt.executeUpdate();
        System.out.println("The Record Has Been Saved Successfully :)");
    }

    // Function for specific Student Record
    public void searchStudent() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        dbmsconnection dbmsconnection= new dbmsconnection("jdbc:mysql://localhost:3306/jdbc_again?SSL=false","root","Nfo4243*");
        // Here i am calling the function/method that is present in my dbmsconnection class
        Connection con = dbmsconnection.getConnection();
        System.out.println("Enter student email to get all Record");
        Scanner input = new Scanner(System.in);
        String emailInput = input.nextLine();

        // Making the mysql query for fetching the student data
        String sql = "select * from student where email = ?";
        //now preparing my above statement
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1,emailInput);

        //getting the result set
        ResultSet rs = stmt.executeQuery();

        //showing the error if the email is not present into the database
        if(rs.next() == false){
            System.out.println("No Email found into the database record!");
        }else{
            System.out.println("id= "+rs.getInt(1)+" /name= "+ rs.getString(2)+" /email= "+rs.getString(3)+" /city= "+rs.getString(4)+" /phone= "+rs.getInt(5));
        }
    }

    public void deleteStudent () throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //first we need to call the database connection
        dbmsconnection dbmsconnection= new dbmsconnection("jdbc:mysql://localhost:3306/jdbc_again?SSL=false","root","Nfo4243*");
        // Here I am calling the function/method that is present in my dbmsconnection class
        Connection con = dbmsconnection.getConnection();
        System.out.println("Enter student email to delete his/her Record");
        Scanner input = new Scanner(System.in);
        String emailInput = input.nextLine();
        //making the mysql query for deleting the student data
        String sql = "delete from student where email=?";
        //now preparing my above statement
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1,emailInput);
        int i = stmt.executeUpdate();

        //if student record deleted successfully!
        if(i>0){
            System.out.println("Student Record Has Been Deleted Successfully!");
        }else{
            System.out.println("No Email found into the database record!");
        }
    }

    public void updateStudent() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //first we need to call the database connection
        dbmsconnection dbmsconnection= new dbmsconnection("jdbc:mysql://localhost:3306/jdbc_again?SSL=false","root","Nfo4243*");
        // Here I am calling the function/method that is present in my dbmsconnection class
        Connection con = dbmsconnection.getConnection();
        System.out.println("Enter student email to update his/her Record");
        Scanner input = new Scanner(System.in);
        String emailInput = input.nextLine();
        System.out.println("Enter New Name for Student");
        String newName = input.nextLine();
        // making the mysql query for updating the student data
        String sql = "update student set name =? where email=?";
        // Prepare statement
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1,newName);
        stmt.setString(2,emailInput);
        int i = stmt.executeUpdate();

        // If student record was updated successfully
        if (i>0){
            System.out.println("Student Record Has Been Updated Successfully!");
        } else {
            System.out.println("No Email found into the database record!");
        }

    }
}
//---------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------DBMS Connection Class-----------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------
//making the JDBC class in which we have just mysql connection
class dbmsconnection{
    String url;
    String username;
    String password;

    // I am going to make Setter for mysql connection
    public dbmsconnection (String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // make a function that contain core code of jdbc connection
        public Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url,username,password);
            // Print this if our connection got successfully
            System.out.println("Your connection established successfully");
            return con;
       }
    
}
