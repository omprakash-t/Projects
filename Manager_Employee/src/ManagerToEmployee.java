import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManagerToEmployee {
    int managerId;
    List<ManagerToEmployee> reportingEmployees = new ArrayList<>();

    ManagerToEmployee(int managerId) {
        this.managerId = managerId;
        this.reportingEmployees = null;
    }
}


