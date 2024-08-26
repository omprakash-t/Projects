public class Payment {
    String date;
    String time;
    int payment_id;
    int given_person_id;
    int get_person_id;
    int amount;

    public Payment(String date,String time,int payment_id,int given_person_id, int get_person_id,int amount) {
        this.amount=amount;
        this.given_person_id=given_person_id;
        this.payment_id=payment_id;
        this.get_person_id=get_person_id;
        this.time=time;
        this.date=date;
    }
}
