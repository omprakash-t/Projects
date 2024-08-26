
import com.mysql.cj.protocol.Resultset;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class Employee {

    public static void addEmployee(String employeeName,String employeePhoneNumber,String employeeAddress) {

        try {
            PreparedStatement statement = Main.con.prepareStatement("insert Employee(employeeName,employeePhoneNumber,employeeAddress,employeeId) values(?,?,?,?)");
            statement.setString(1,employeeName);
            statement.setString(2,employeePhoneNumber);
            statement.setString(3,employeeAddress);
            statement.setInt(4,Main.getId());
            statement.execute();

        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static void deleteEmployee(int employeeId) {

        try{
            PreparedStatement statement=Main.con.prepareStatement("delete from Employee where employeeId=?");
            statement.setInt(1,employeeId);
            statement.execute();
        }catch(Exception e){
            System.out.println(e);
        }

    }

    public static void modifyEmployee(int employeeId) {
        System.out.println("Enter the employee details");
        String employeeName=Main.sc.next();
        String employeePhoneNumber=Main.sc.next();
        String employeeAddress=Main.sc.next();
        try {
            PreparedStatement statement = Main.con.prepareStatement("update Employee set employeeName=?,employeePhoneNumber=?," +
                    "employeeAddress=? where employeeId=?");
            statement.setString(1,employeeName);
            statement.setString(2,employeePhoneNumber);
            statement.setString(3,employeeAddress);
            statement.setInt(4,employeeId);
            statement.execute();
        }catch(Exception e){
            System.out.println(e);
        }
    }



}
