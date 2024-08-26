import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Employee {
    public static void addEmployee(String employeeName,String employeePhoneNumber,String employeeAddress){
        try {
            PreparedStatement statement = Main.con.prepareStatement("insert into Employee(employeeName," +
                    "employeePhoneNumber,employeeAddress)values(?,?,?)");
            statement.setString(1,employeeName);
            statement.setString(2,employeePhoneNumber);
            statement.setString(3,employeeAddress);
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

    public static void deleteEmployee(int employeeId) {

        try{
            PreparedStatement statement=Main.con.prepareStatement("delete from Employee where employeeId=?");
            statement.setInt(1,employeeId);
            statement.execute();
        }catch(Exception e){
            System.out.println(e);
        }

    }

    public static  void addManagerToEmployee(int employeeId,int managerId){
        if(employeeId==managerId)
            System.out.println("Employee and Manager are same person");
        else{
            try {
                PreparedStatement statement = Main.con.prepareStatement("update Employee set managerId=? " +
                        "where employeeId=?");
                statement.setInt(1,managerId);
                statement.setInt(2,employeeId);
                statement.execute();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }


    public static void displayManagerEmployee(int managaerId)
    {
        ManagerToEmployee head=createManagerEmployee(managaerId);

        int height=managerEmployeeHeight(head);
        System.out.println("Manager :"+getNameById(head.managerId));
        for(int level=1;level<height;level++){
            displayManagertoLevel(head,level);
        }
    }

    public static ManagerToEmployee createManagerEmployee(int managerId){
        ManagerToEmployee head=new ManagerToEmployee(managerId);
        try {
            PreparedStatement statement = Main.con.prepareStatement("select employeeId from Employee " +
                    "where managerId=?");
            statement.setInt(1,managerId);
            ResultSet rs=statement.executeQuery();
            List<ManagerToEmployee> reportingEmployees=new ArrayList<>();
            while (rs.next()){
                int id=rs.getInt(1);
                reportingEmployees.add(createManagerEmployee(id));
            }
            head.reportingEmployees=reportingEmployees;
        }catch (Exception e){
            System.out.println(e);
        }
        return head;
    }


    public static void  displayManagertoLevel(ManagerToEmployee head,int height)
    {
        if(height==0){
            System.out.println(getNameById(head.managerId));
        }
        else {
            for(ManagerToEmployee managerToEmployee: head.reportingEmployees){
                displayManagertoLevel(managerToEmployee,height-1);
            }
        }
    }


    public static int managerEmployeeHeight(ManagerToEmployee head){
        if(head.reportingEmployees.isEmpty()) {
            return 1;
        }
        else {

            int height=0;
            for(ManagerToEmployee managerToEmployee:head.reportingEmployees)
            {
                int temp_height=managerEmployeeHeight(managerToEmployee);
                if(temp_height>height)
                    height=temp_height;
            }
            return height+1;
        }

    }



    public static String getNameById(int employeeId){
        try{
            PreparedStatement statement=Main.con.prepareStatement("select employeeName from Employee where employeeId=?");
            statement.setInt(1,employeeId);
            ResultSet rs=statement.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        }catch (Exception e){
            System.out.println("141 "+e);
        }
        return"";
    }
}
