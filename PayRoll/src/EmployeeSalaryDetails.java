import java.sql.PreparedStatement;

public class EmployeeSalaryDetails {


    public static void addEmployeeSalaryDetails(int employeeSalaryId,int componentId,String startDate,float componentAmount){
        try{
            PreparedStatement statement=Main.con.prepareStatement("insert into EmployeeSalaryDetails(" +
                    "employeeSalaryDetailId,employeeSalaryId,componentId,startDate,componentAmount)values(?,?,?,?,?)");
            statement.setInt(1,Main.getId());
            statement.setInt(2,employeeSalaryId);
            statement.setInt(3,componentId);
            statement.setString(4,startDate);
            statement.setFloat(5,componentAmount);
            statement.execute();
        }catch (Exception e){
            System.out.println("Enter Valid Component Id"+e);
        }
    }

    public static void deleteEmployeeSalaryDetails(int employeeSalaryDetailId)
    {
        try {
            PreparedStatement statement=Main.con.prepareStatement("delete  from EmployeeSalaryDetails where employeeSalaryDetailId=?");
            statement.setInt(1,employeeSalaryDetailId);
            statement.execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }


}
