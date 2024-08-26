import com.mysql.cj.protocol.Resultset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeSalary {

    static int employeeSalaryId;
    public static void addEmployeeSalary(int employeeId,String startDate)
    {

        try{
            PreparedStatement statement=Main.con.prepareStatement("insert into EmployeeSalary(employeeSalaryId," +
                    "employeeId,startDate) values(?,?,?)");
            employeeSalaryId=Main.getId();
            statement.setInt(1,employeeSalaryId);
            statement.setInt(2,employeeId);
            statement.setString(3,startDate);
            statement.execute();

        }catch (Exception e){
            System.out.println(e);
        }

        int CTC=0;
        PreparedStatement statement = null;
        System.out.println("Enter the number of salary details");
        int numberOfSalaryDetails=Main.sc.nextInt();
        while(numberOfSalaryDetails>0){
            System.out.println("Enter Component Id and amount");
            int compnentId=Main.sc.nextInt();
            int amount=Main.sc.nextInt();
            CTC+=amount;

            EmployeeSalaryDetails.addEmployeeSalaryDetails(employeeSalaryId,compnentId, startDate,amount);
            numberOfSalaryDetails--;
        }

        try{
            statement=Main.con.prepareStatement("update EmployeeSalary set CTC=? where employeeSalaryId=?");
            statement.setInt(1,CTC);
            statement.setInt(2,employeeSalaryId);
            statement.execute();
        }catch(Exception e){
            System.out.println("add employeeSalary   "+e);
        }

    }

    public static void deleteEmployeeSalary(int employeeSalaryId)
    {
        try {
            PreparedStatement statement=Main.con.prepareStatement("delete  from EmployeeSalary where employeeSalaryId=?");
            statement.setInt(1,employeeSalaryId);
            statement.execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void modifyEmployeeSalary(int employeeId,int flag)
    {
        PreparedStatement statement = null;
        int employeeSalaryId = 0;
        try {

            statement = Main.con.prepareStatement("select employeeSalaryId from EmployeeSalary where employeeId=? and isnull(endDate)");
            statement.setInt(1, employeeId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                employeeSalaryId = rs.getInt(1);
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        if(flag==1) {
            System.out.println("Enter the current date");
            String currentDate=Main.sc.next();
            addEmployeeSalary(employeeId,currentDate);
            try {
                statement = Main.con.prepareStatement("update EmployeeSalary set endDate=? where employeeSalaryId=?");
                statement.setString(1, currentDate);
                statement.setInt(2, employeeSalaryId);
                statement.execute();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else{
            System.out.println("Enter the componentId to change the component,amount and currentdate");
            int componentId=  Main.sc.nextInt();
            int amount=Main.sc.nextInt();
            String currentDate=Main.sc.next();
            int employeeSalaryDetailId=0;
            try {
                statement = Main.con.prepareStatement("select employeeSalaryDetailId from EmployeeSalaryDetails where " +
                        " employeeSalaryId=? and componentId=? and isnull(endDate)");
                statement.setInt(1,employeeSalaryId);
                statement.setInt(2,componentId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                     employeeSalaryDetailId= rs.getInt(1);

                }
                statement=Main.con.prepareStatement("update EmployeeSalaryDetails set endDate=? where employeeSalaryDetailId=?");
                statement.setString(1,currentDate);
                statement.setInt(2,employeeSalaryDetailId);
                EmployeeSalaryDetails.addEmployeeSalaryDetails(employeeSalaryId,componentId,currentDate,amount);
                statement.execute();
            }catch(Exception e){
                System.out.println( "update error "+e);
            }

        }

    }
}
