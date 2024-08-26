import java.util.List;
import java.util.ArrayList;
public class Person {
    public String Name;
    public List<Person_and_amount> person_to_person =new ArrayList<>();
    public List<String> person_names=new ArrayList<>();
    Person(String Name)
    {
        this.Name=Name;
    }
}
