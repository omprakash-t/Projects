import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        int y=sc.nextInt();

        double totalDistance=((x*60)+y);

        double speed=(totalDistance*6)/(12*60);

        System.out.println("long hand :"+(double)(speed));
        System.out.println("Small hand speed :"+(double)(speed/12));
    }
}