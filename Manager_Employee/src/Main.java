import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {
    public static Scanner sc=new Scanner(System.in);
    public static Connection con=null;
    static void installize(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Manager_Employee",
                    "root", "Mysql@123");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        installize();
        int flag=1;
        while (flag!=0)
        {
            System.out.println("Enter your choice");
            flag=sc.nextInt();
            switch (flag)
            {
                case 1:
                    System.out.println("Enter the Name,Phone number and Address");
                    Employee.addEmployee(sc.next(), sc.next(), sc.next());
                    break;
                case 2:
                    System.out.println("Enter the EmployeeId and ManagerId");
                    Employee.addManagerToEmployee(sc.nextInt(), sc.nextInt());
                    break;
                case 3:
                    System.out.println("Enter the ManagerId to display the reporting Employee");
                    Employee.displayManagerEmployee(sc.nextInt());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + flag);
            }
        }
    }
}