import java.util.ArrayList;
import java.util.List;

public class Transactions {
    public int transaction_id;
    public int Total_amount;
    public List<Person_and_amount> amount_given_persons=new ArrayList<>();
    public List<Person_and_amount> persons_contribution=new ArrayList<>();
    Type types;
    public Transactions(int transaction_id,Type types,int total_amount,List<Person_and_amount> agp,List<Person_and_amount> pc) {
        Total_amount = total_amount;
        this.amount_given_persons=agp;
        this.persons_contribution=pc;
        this.types=types;
        this.transaction_id=transaction_id;
    }
}
