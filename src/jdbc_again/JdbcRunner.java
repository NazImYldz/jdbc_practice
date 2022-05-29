package jdbc_again;

import java.sql.SQLException;
import java.util.Scanner;

public class JdbcRunner extends JdbcMethods{

    public static void main(String[] args) throws SQLException {

        Scanner scan = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("Welcome :) What Do You Wan to Do? Please Select From Menu");
            System.out.println(" Press 1 for inserting new data\n Press 2 for updating data\n Press 3 for searching data\n" +
                    " Press 4 for deleting data\n Press 5 for quiting\n Enter Here");
            choice = scan.nextInt();
            switch (choice){
                case 1 :
                    getStudentDetail();
                    insertData();
                    break;
                case 2 :
                    updateData();
                    break;
                case 3 :
                    searchData();
                    break;
                case 4 :
                    deleteData();
                    break;
                case 5 :
                    break;
                default:
                    System.out.println("Please Enter Correct Choice!!!");
            }
        }while (choice!=5);

            System.out.println("Thanks for choosing us, see you!!!");

    }
}
