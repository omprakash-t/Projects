
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Scanner;


public class Main {
    public static Scanner sc=new Scanner(System.in);
    static Connection con=null;
    static void installize() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PayRoll",
                    "root", "Mysql@123");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static int getId(){
        try{
            PreparedStatement statement=con.prepareStatement("select * from TableId");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                int id=rs.getInt(1);
                statement=con.prepareStatement("update TableId set id=? where id=?");
                statement.setInt(1,id+1);
                statement.setInt(2,id);
                statement.execute();
                return id;
            }
        }catch (Exception e){
            System.out.println("Expection at main 33"+e);
        }
        return 0;
    }
    public static void main(String[] args) {
        installize();
        int flag= 1;
        while(flag!=0)
        {
            System.out.println("Enter the choice");
            flag= sc.nextInt();
            switch(flag)
            {
                case 1:
                    System.out.println("Enter name phonenumber employeeAddress");
                    Employee.addEmployee(sc.next(), sc.next(), sc.next());
                    break;
                case 2:
                    System.out.println("Enter the employeeId and curr_Date");
                    EmployeeSalary.addEmployeeSalary(sc.nextInt(), sc.next());
                    break;
                case 3:
                    System.out.println("Enter EmployeeId and enter 1 for ctc updation and 2 for component updation");
                    EmployeeSalary.modifyEmployeeSalary(sc.nextInt(), sc.nextInt());
                    break;
                case 4:
                    System.out.println("Enter the component Name");
                    SalaryComponents.addSalaryComponent(sc.next());
                    break;
                case 5:
                    System.out.println("Enter the componentId");
                    SalaryComponents.modifySalaryComponent(sc.nextInt());
                    break;

            }
        }




    }
}