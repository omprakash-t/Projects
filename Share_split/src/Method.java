import java.util.ArrayList;
import java.util.List;

public class Method {


    public static void person_wise_report(int person_id)
    {
        List<Person_and_amount> person_report=new ArrayList<>();
        List<Integer> person_ids=new ArrayList<>();

        List<Integer> list_ids=new ArrayList<>();
        List<Person_and_amount> given_pendings=new ArrayList<>();
        List<Person_and_amount> cotribution_pending=new ArrayList<>();
        for(Transaction transaction:Main.list_transaction)
        {
            for(Person_and_amount temp_person:transaction.given_persons)
            {
                given_pendings.add(new Person_and_amount(temp_person.person_id, temp_person.amount));
                list_ids.add(temp_person.person_id);
            }

            for(Person_and_amount temp_person:transaction.contribution_per_person)
            {
                if(list_ids.contains(temp_person.person_id))
                {
                    for(Person_and_amount temp_trav: given_pendings)
                    {
                        if(temp_trav.person_id==temp_person.person_id)
                        {
                            if(temp_trav.amount>= temp_person.amount)
                            {
                                temp_trav.amount-= temp_trav.amount;
                            }
                            else
                            {
                                cotribution_pending.add(new Person_and_amount(temp_person.person_id, temp_person.amount-temp_trav.amount));
                                temp_trav.amount=0;
                            }
                        }
                    }
                }
                else
                {
                    cotribution_pending.add(new Person_and_amount(temp_person.person_id, temp_person.amount));
                }
            }


            for(Person_and_amount given_person_amount:given_pendings)
            {
                if(given_person_amount.person_id==person_id)
                {
                    int temp_given_amount=given_person_amount.amount;
                    while(temp_given_amount>0)
                    {
                        for(Person_and_amount contributed_person: cotribution_pending)
                        {
                            int add_amount=0;
                            if(temp_given_amount>=contributed_person.amount)
                            {
                                add_amount= contributed_person.amount;
                                temp_given_amount-=contributed_person.amount;
                                contributed_person.amount=0;
                            }
                            else
                            {
                                add_amount=temp_given_amount;
                                contributed_person.amount-=add_amount;
                                temp_given_amount=0;
                            }
                            if(!person_ids.contains(contributed_person.person_id))
                            {
                                person_ids.add(contributed_person.person_id);
                                person_report.add(new Person_and_amount(contributed_person.person_id, add_amount));
                            }
                            else {
                                for(Person_and_amount temp_person_report:person_report)
                                {
                                    if(temp_person_report.person_id== contributed_person.person_id)
                                    {
                                        temp_person_report.amount+=add_amount;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for(Person_and_amount contributed_person :cotribution_pending)
            {
                if(contributed_person.person_id==person_id)
                {
                    int temp_given_amount=contributed_person.amount;
                    while(temp_given_amount>0)
                    {
                        for(Person_and_amount given_persons: given_pendings)
                        {
                            int add_amount=0;
                            if(temp_given_amount>=given_persons.amount)
                            {
                                add_amount= given_persons.amount;
                                temp_given_amount-=given_persons.amount;
                                
                            }
                            else
                            {
                                add_amount=temp_given_amount;
                                temp_given_amount=0;
                            }
                            if(!person_ids.contains(given_persons.person_id))
                            {
                                person_ids.add(given_persons.person_id);
                                person_report.add(new Person_and_amount(given_persons.person_id, add_amount*-1));
                            }
                            else {
                                for(Person_and_amount temp_person_report:person_report)
                                {
                                    if(temp_person_report.person_id== given_persons.person_id)
                                    {
                                        temp_person_report.amount-=add_amount;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for(Payment payment: Main.list_payment)
        {
            if(payment.given_person_id==person_id)
            {
                if(!person_ids.contains(payment.get_person_id))
                {
                    person_ids.add(payment.get_person_id);
                    person_report.add(new Person_and_amount(payment.get_person_id, payment.amount));
                }
                else {
                    for(Person_and_amount temp_person_report:person_report)
                    {
                        if(temp_person_report.person_id== payment.get_person_id)
                        {
                            temp_person_report.amount+= payment.amount;
                        }
                    }
                }
            }
            if(payment.get_person_id==person_id)
            {
                if(!person_ids.contains(payment.given_person_id))
                {
                    person_ids.add(payment.given_person_id);
                    person_report.add(new Person_and_amount(payment.given_person_id, payment.amount*-1));
                }
                else {
                    for(Person_and_amount temp_person_report:person_report)
                    {
                        if(temp_person_report.person_id== payment.given_person_id)
                        {
                            temp_person_report.amount-= payment.amount;
                        }
                    }
                }
            }
        }

        System.out.println("Report of person   "+get_person_name(person_id));
        for(Person_and_amount temp_person_report:person_report)
        {
            if(temp_person_report.amount!=0)
              System.out.println(get_person_name(temp_person_report.person_id)+"  "+temp_person_report.amount);
        }
    }


    public static String get_person_name(int person_id)
    {
        return Main.list_person.get(person_id-1).person_name;
    }

}
