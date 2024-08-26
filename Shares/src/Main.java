import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static int transaction_id=0;
    public static int person_id=0;
    public static Scanner sc=new Scanner(System.in);
    public static List<Person> list_person=new ArrayList<>();
    public static List<Transactions> list_transaction=new ArrayList<>();
    public static List<Type> list_transactionstypes=new ArrayList<>();
    public static List<String> list_transactioncategory=new ArrayList<>();
    public static void main(String[] args) {
        Method.adding_persons();
        int flag=1;
        Method.display();
        while(flag!=0)
        {

            System.out.println("Enter your choice");
            int temp=sc.nextInt();
            flag=temp;
            switch(flag)
            {
                case 0:
                    break;
                case 1:
                    System.out.println("Enter the type and category of the transaction");
                    new_transaction(sc.next(),sc.next());
                    break;
                case 2:
                    System.out.println("Enter the person_name");
                    String person_name=sc.next();
                    Method.show_person_details(person_name);
                    break;
                case 3:
                    show_transactions();
                    break;
                case 4:
                    System.out.println("Enter the given person name , amount and get person");
                    String Given_person= sc.next();
                    int amount= sc.nextInt();
                    String Get_person= sc.next();
                    updatepayment(Given_person,amount,Get_person);
                    break;
                case 5:
                    System.out.println("Enter the Category of transaction");
                    type_travese(sc.next());
                    break;
                case 6:
                    System.out.println("Enter the transaction_id to be deleted");
                    Method.transaction_modify(sc.nextInt()-1);
                    break;
                case 7:
                    System.out.println("Enter the transaction_id to be deleted");
                    Method.transaction_modify(sc.nextInt()-1);
                    System.out.println("Enter new transaction details");
                    System.out.println("Enter the type and category of the transaction");
                    new_transaction(sc.next(),sc.next());
                    break;
            }
        }
    }


    static void new_transaction(String type,String category)
    {

        int tot_amount=0;
        List<Person_and_amount> amount_given_persons=new ArrayList<>();
        List<Person_and_amount> persons_contribution=new ArrayList<>();
        List<String> list_names=new ArrayList<>();
        List<Person_and_amount> given_pendings=new ArrayList<>();
        List<Person_and_amount> cotribution_pending=new ArrayList<>();

        System.out.println("Enter the number of given persons ");
        int number_given=sc.nextInt();
        System.out.println("Enter person_wise given amount");


        for(int trav=0;trav<number_given;trav++)
        {
            String temp_person_name= sc.next();
            int temp_amount=sc.nextInt();
            tot_amount=tot_amount+temp_amount;
            amount_given_persons.add(new Person_and_amount(temp_person_name,temp_amount));
            given_pendings.add(new Person_and_amount(temp_person_name,temp_amount));
            list_names.add(temp_person_name);
        }


        int tot_cont_amount=0;
        System.out.println("Enter the number of contributed persons");
        int number_contributed_persons= sc.nextInt();
        System.out.println("Enter person_wise contribution and amount");

        for(int trav=0;trav<number_contributed_persons;trav++)
        {
            String temp_person_name= sc.next();

            int temp_amount=sc.nextInt();
            tot_cont_amount+=temp_amount;
            persons_contribution.add(new Person_and_amount(temp_person_name,temp_amount));
            if(list_names.contains(temp_person_name))
            {
                for(Person_and_amount temp_trav: given_pendings)
                {
                    if(temp_trav.Name.equals(temp_person_name))
                    {
                        if(temp_trav.amount>=temp_amount)
                        {
                            temp_trav.amount-=temp_amount;
                        }
                        else
                        {
                            cotribution_pending.add(new Person_and_amount(temp_person_name,temp_amount-temp_trav.amount));
                            temp_trav.amount=0;
                        }
                    }
                }
            }
            else
            {
                cotribution_pending.add(new Person_and_amount(temp_person_name,temp_amount));
            }
        }
        if(!list_transactioncategory.contains(category))
        {
            list_transactioncategory.add(category);
        }

        Type temp_typ=new Type(type,category);

        if(!list_transactionstypes.contains(temp_typ))
        {
            list_transactionstypes.add(temp_typ);
        }
        Transactions temp_trans=new Transactions(transaction_id,temp_typ,tot_amount,amount_given_persons,persons_contribution);
        transaction_id++;
        if(tot_amount==tot_cont_amount)
        list_transaction.add(temp_trans);
        else
            System.out.println("Total amount given is not equal to contributed amount");

        updation_transaction(given_pendings,cotribution_pending);
    }

    static void show_transactions()
    {
        System.out.println("List of transactions");

        for(Transactions temp:list_transaction)
        {
            if(temp.types.category.equals("Transaction deleted"))
            {
                System.out.println("Transaction " + (temp.transaction_id + 1) + " Transaction deleted" );
            }
            else {
                System.out.println("Transaction " + (temp.transaction_id + 1) + " amount :" + temp.Total_amount);
                System.out.println("Transaction type" + temp.types.type_name + " Category" + temp.types.category);
                System.out.println("Given person details");
                for (Person_and_amount temp_per_and_amt : temp.amount_given_persons) {
                    System.out.println(temp_per_and_amt.Name + ": " + temp_per_and_amt.amount);
                }
                System.out.println("Contribution person details");
                for (Person_and_amount temp_per_and_amt : temp.persons_contribution) {
                    System.out.println(temp_per_and_amt.Name + ": " + temp_per_and_amt.amount);
                }
            }

        }
    }
    static void updation_transaction(List<Person_and_amount> amount_given_persons,List<Person_and_amount>Clone_person_contribution)
    {

        for(Person_and_amount given_person:amount_given_persons)
        {
            int temp_amount= given_person.amount;
            while(temp_amount>0)
            {
                for(Person_and_amount get_person:Clone_person_contribution)
                {
                    if(get_person.amount!=0) {
                        if (given_person.Name.equals(get_person.Name)) {
                            temp_amount -= get_person.amount;
                            get_person.amount = 0;
                        } else {
                            int add_amount= get_person.amount;
                            if(temp_amount>= get_person.amount)
                            {
                                temp_amount-= get_person.amount;
                                get_person.amount=0;
                            }
                            else {
                                add_amount=temp_amount;
                                get_person.amount-=temp_amount;
                                temp_amount=0;
                            }
                            updatepayment(given_person.Name,add_amount,get_person.Name);
                        }
                    }
                }
            }
        }
    }


    static void updatepayment(String Given_person,int amount,String Get_Person)
    {
        for(Person temp_person: list_person)
        {
            if(temp_person.Name.equals(Given_person))
            {
                if(!temp_person.person_names.contains(Get_Person))
                {
                    temp_person.person_names.add(Get_Person);
                    temp_person.person_to_person.add(new Person_and_amount(Get_Person,amount));
                }
                else {
                    for(Person_and_amount temp_person_amt: temp_person.person_to_person)
                    {
                        if(temp_person_amt.Name.equals(Get_Person))
                        {
                            temp_person_amt.amount+=amount;
                        }
                    }
                }
            }
        }
        for(Person temp_person: list_person)
        {
            if(temp_person.Name.equals(Get_Person))
            {
                if(!temp_person.person_names.contains(Given_person))
                {
                    temp_person.person_names.add(Given_person);
                    temp_person.person_to_person.add(new Person_and_amount(Given_person,amount*-1));
                }
                else {
                    for(Person_and_amount temp_person_amt: temp_person.person_to_person)
                    {
                        if(temp_person_amt.Name.equals(Given_person))
                        {
                            temp_person_amt.amount-=amount;
                        }
                    }
                }
            }
        }
    }

    static void type_travese(String Category)
    {
        for(Transactions temp:list_transaction)
        {
            if(temp.types.category.equals(Category))
            {
                System.out.println(temp.types.type_name+":  "+temp.Total_amount);
            }
        }
    }

}