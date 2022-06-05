package jdbc_again;

import java.sql.*;
import java.util.Scanner;

public class JdbcMethods {

    static String url;
    static String userName;
    static String password;
    static Scanner scan = new Scanner(System.in);

    protected static Connection connection() throws SQLException {
        System.out.println("Enter URL");
        url = scan.nextLine();
        System.out.println("Enter User Name");
        userName = scan.nextLine();
        System.out.println("Enter Password");
        password = scan.nextLine();

        Connection connection = DriverManager.getConnection(url,userName,password);

            if (connection.isClosed()){
                System.out.println("Connection is not stable, Connection is failed!");
            }else {
                System.out.println("Connection has been made successfully");
            }
          return connection;
    }

    private static String name;
    private static String email;
    private static String city;
    private static int phone;

    protected static void getStudentDetail(){
        System.out.println("Enter Student Name");
        name = scan.nextLine();
        System.out.println("Enter Student Email");
        email = scan.nextLine();
        System.out.println("Enter Student City");
        city = scan.nextLine();
        System.out.println("Enter Student Phone");
        phone = scan.nextInt();
        scan.nextLine();
    }

    protected static void insertData() throws SQLException {
        getStudentDetail();
        Connection con = connection();

        String sql = "insert into student(name,email,city,phone) values (?,?,?,?);";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(2,email);
        stmt.setString(3,city);
        stmt.setInt(4,phone);

        int i = stmt.executeUpdate();
        if (i>0){
            System.out.println("Data is successfully inserted");
        }else {
            System.out.println("There might be a problem, please check database manually");
        }
        con.close();
    }

    protected static void updateData() throws SQLException {
        Connection con = connection();
        int choice = 0;

        System.out.println("Enter student email to update his/her Record");
        String emailInput = scan.nextLine();

        do {
            System.out.println("Please select the data that you want to change");
            System.out.println(" Press 1 to change name\n Press 2 to change email\n Press 3 to change city\n Press 4 to change phone\n " +
                    "Press 5 to Exit");
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice){
                case 1 : System.out.println("Enter New Name");
                         String newName = scan.nextLine();
                    String sqlName = "update student set name =? where email=?";
                    PreparedStatement stmt = con.prepareStatement(sqlName);
                    stmt.setString(1,newName);
                    stmt.setString(2,emailInput);
                    int i = stmt.executeUpdate();
                    if (i>0){
                        System.out.println("Student Record Has Been Updated Successfully!");
                    } else {
                        System.out.println("No Email found into the database record!");
                    }
                    break;
                case 2 : System.out.println("Enter New email");
                         String newEmail = scan.nextLine();
                         String sqlEmail = "update student set email =? where email =?";
                         stmt = con.prepareStatement(sqlEmail);
                         stmt.setString(1,newEmail);
                         stmt.setString(2,emailInput);
                    int j = stmt.executeUpdate();
                    if (j>0){
                        System.out.println("Student Record Has Been Updated Successfully!");
                    } else {
                        System.out.println("No Email found into the database record!");
                    }
                         break;
                case 3 : System.out.println("Enter New City");
                         String newCity = scan.nextLine();
                         String sqlCity = "update student set city =? where email =?";
                         stmt = con.prepareStatement(sqlCity);
                         stmt.setString(1,newCity);
                         stmt.setString(2,emailInput);
                    int k = stmt.executeUpdate();
                    if (k>0){
                        System.out.println("Student Record Has Been Updated Successfully!");
                    } else {
                        System.out.println("No Email found into the database record!");
                    }
                         break;
                case 4 : System.out.println("Enter New Phone Number");
                         int newPhone = scan.nextInt();
                         String sqlPhone = "update student set phone =? where email =?";
                         stmt = con.prepareStatement(sqlPhone);
                         stmt.setInt(1,newPhone);
                         stmt.setString(2,emailInput);
                    int m = stmt.executeUpdate();
                    if (m>0){
                        System.out.println("Student Record Has Been Updated Successfully!");
                    } else {
                        System.out.println("No Email found into the database record!");
                    }
                    break;
                case 5 :
                    break;
                default:
                    System.out.println("Please Enter Correct Choice!!!");
            }
        }while (choice!=5);
        con.close();
    }

    protected static void searchData() throws SQLException {

        Connection con = connection();

        System.out.println("Enter email address to get all record");
        String emailInput = scan.nextLine();

        String sql = "select * from student where email =?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1,emailInput);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()){
            System.out.println("id = "+rs.getInt(1)+"\nName = "+rs.getString(2)+"\nEmail = "+rs.getString(3)+
                    "\nCity = "+rs.getString(4)+"\nPhone number = "+rs.getInt(5));
        }else {
            System.out.println("This email can not be found in database! Please check again...");
        }
        con.close();
    }

    protected static void deleteData() throws SQLException {
        Connection con = connection();

        System.out.println("Enter email to delete his/her record");
        String emailInput = scan.nextLine();

        String sql = "delete from student where email =?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1,emailInput);

        int i = stmt.executeUpdate();

        if (i>0){
            System.out.println("Data Has Been Deleted Successfully!");
        } else {
            System.out.println("No Email found into the database record!");
        }
        con.close();
    }

}
