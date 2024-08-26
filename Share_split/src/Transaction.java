import java.util.ArrayList;
import java.util.List;

public class Transaction {
    int total_amount;
    String date;
    String time;
    int transaction_id;
    List<Person_and_amount> given_persons;
    List<Person_and_amount> contribution_per_person;
    Type type_transaction;

    Transaction(int tot_amount,String date,String time,int transaction_id,List<Person_and_amount> given_persons,List<Person_and_amount> contribution_per_person,Type type_transaction)
    {
        this.total_amount=tot_amount;
        this.date=date;
        this.given_persons=given_persons;
        this.time=time;
        this.contribution_per_person=contribution_per_person;
        this.transaction_id=transaction_id;
        this.type_transaction=type_transaction;

    }
}
