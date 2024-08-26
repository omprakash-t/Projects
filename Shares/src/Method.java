import java.util.ArrayList;
import java.util.List;

public class Method {

    public static void display()
    {
        System.out.println("Choice - 1 : New transaction");
        System.out.println("Choice - 2 : Each person Report");
        System.out.println("Choice - 3 : Previous Transaction details");
        System.out.println("Choice - 4 : Update the payment");
        System.out.println("Choice - 5 : Category wise transaction");
        System.out.println("Choice - 6 : Transaction deletion");
        System.out.println("Choice - 7 : Transaction modification");
        System.out.println("Choice - 0 : To exit");
    }
    public static void adding_persons()
    {
        Main.list_person.add(new Person("A"));
        Main.list_person.add(new Person("B"));
        Main.list_person.add(new Person("C"));
        Main.list_person.add(new Person("D"));
    }

    public static void show_person_details(String name)
    {
        for(Person temp_person: Main.list_person)
        {
            if(temp_person.Name.equals(name))
            {
                for(Person_and_amount temp_person_amount:temp_person.person_to_person)
                {
                    System.out.println(temp_person_amount.Name+" "+temp_person_amount.amount);
                }
            }
        }
    }

    public static void transaction_modify(int transaction_id)
    {
        if(transaction_id>Main.transaction_id)
            System.out.println("Enter a valid transaction_id");
        List<Person_and_amount> given_pendings=new ArrayList<>();
        List<Person_and_amount> cotribution_pending=new ArrayList<>();
        List<String> list_names=new ArrayList<>();
        Transactions transaction_modify=Main.list_transaction.get(transaction_id);

        for(Person_and_amount given_person_and_amount :transaction_modify.amount_given_persons)
        {
            given_pendings.add(given_person_and_amount);
            list_names.add(given_person_and_amount.Name);
        }

        for(Person_and_amount contribution_person_and_amount: transaction_modify.persons_contribution)
        {
            if(list_names.contains(contribution_person_and_amount.Name))
            {
                for(Person_and_amount temp_trav: given_pendings)
                {
                    if(temp_trav.Name.equals(contribution_person_and_amount.Name))
                    {
                        if(temp_trav.amount>=contribution_person_and_amount.amount)
                        {
                            temp_trav.amount-=contribution_person_and_amount.amount;
                        }
                        else
                        {
                            cotribution_pending.add(new Person_and_amount(contribution_person_and_amount.Name,contribution_person_and_amount.amount-temp_trav.amount));
                            temp_trav.amount=0;
                        }
                    }
                }
            }
            else
            {
                cotribution_pending.add(new Person_and_amount(contribution_person_and_amount.Name,contribution_person_and_amount.amount));
            }
        }
        transaction_modify.types.category="Transaction deleted";
        Main.updation_transaction(cotribution_pending,given_pendings);

    }

    public static void simplified(String person_name)
    {

    }
}
