import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int transaction_id=0;

    public static Scanner sc=new Scanner(System.in);
    public static List<Person> list_person=new ArrayList<>();
    public static List<Transaction> list_transaction=new ArrayList<>();
    public static List<Payment> list_payment=new ArrayList<>();



    public static void main(String[] args) {
        add_persons();
        transaction();


        Method.person_wise_report(1);
        Method.person_wise_report(2);
        Method.person_wise_report(3);
        Method.person_wise_report(4);

        make_payment();
        Method.person_wise_report(3);
    }
    public static void make_payment()
    {
        String date="08/08/2024";
        String time="14:00";
        int payment_id=1;
        int given_person_id=3;
        int get_person_id=1;
        int amount=100;
        list_payment.add(new Payment(date,time,payment_id,given_person_id,get_person_id,amount));
    }

    public static void add_persons()
    {
        list_person.add(new Person(1,"A"));
        list_person.add(new Person(2,"B"));
        list_person.add(new Person(3,"C"));
        list_person.add(new Person(4,"D"));
    }
    public static void transaction()
    {

        List<Person_and_amount> amount_given_persons=new ArrayList<>();
        List<Person_and_amount> persons_contribution=new ArrayList<>();

        amount_given_persons.add(new Person_and_amount(1,100));
        amount_given_persons.add(new Person_and_amount(2,100));

        persons_contribution.add(new Person_and_amount(3,100));
        persons_contribution.add(new Person_and_amount(4,100));

        new_transaction(200,amount_given_persons,persons_contribution);

    }

    public static void new_transaction(int amount,List<Person_and_amount>given_persons,List<Person_and_amount> contribution_per_person)
    {
        list_transaction.add(new Transaction(amount,"08/08/2024","14:56",Main.transaction_id,given_persons,contribution_per_person,new Type("Food",Category.Necessary)));
        transaction_id++;
    }



    static  void show_transaction()
    {
        for(Transaction transaction: list_transaction)
        {
            System.out.println("Total amount :"+transaction.total_amount);
        }
    }
    static void show_payment()
    {
        for(Payment payment:list_payment)
        {
            System.out.println("payment_id :"+payment.payment_id+" amount :"+payment.amount);
        }
    }
}