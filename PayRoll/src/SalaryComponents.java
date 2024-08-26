import java.sql.PreparedStatement;

public class SalaryComponents {


    public static void addSalaryComponent(String componentName) {
        try {
            PreparedStatement statement = Main.con.prepareStatement("insert SalaryComponents(componentName,componentId) values(?,?)");
            statement.setString(1,componentName);
            statement.setInt(2,Main.getId());
            statement.execute();

        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static void deleteSalaryComponent(int componentId) {

        try{
            PreparedStatement statement=Main.con.prepareStatement("delete from SalaryComponents where componentId=?");
            statement.setInt(1,componentId);
            statement.execute();
        }catch(Exception e){
            System.out.println(e);
        }

    }

    public static void modifySalaryComponent(int componentId) {
        System.out.println("Enter the Component name");
        String componentName = Main.sc.next();
        try {
            PreparedStatement statement = Main.con.prepareStatement("update SalaryComponents set componentName=?" +
                    " where componentId=?");
            statement.setString(1, componentName);
            statement.setInt(2, componentId);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
