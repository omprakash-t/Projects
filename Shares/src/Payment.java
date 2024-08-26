public class Payment {
    Person Given_person;
    int amount;
    Person Get_person;

    Payment(Person Given_person,Person Get_person,int amount)
    {
        this.amount=amount;
        this.Given_person=Given_person;
        this.Get_person=Get_person;
    }
}
